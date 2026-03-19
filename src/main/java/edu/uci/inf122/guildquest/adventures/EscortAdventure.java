package edu.uci.inf122.guildquest.adventures;

import edu.uci.inf122.guildquest.api.AdventureSnapshot;
import edu.uci.inf122.guildquest.api.state.GridCell;
import edu.uci.inf122.guildquest.api.win_conditions.TimeLimitCondition;
import edu.uci.inf122.guildquest.content.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
// import edu.uci.inf122.guildquest.ui;
import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.*;
import edu.uci.inf122.guildquest.entities.interfaces.CanHealOther;
import edu.uci.inf122.guildquest.entities.interfaces.CanHealSelf;
import edu.uci.inf122.guildquest.entities.npcs.*;
import edu.uci.inf122.guildquest.entities.playablecharacters.Assassin;
import edu.uci.inf122.guildquest.entities.playablecharacters.Cleric;
import edu.uci.inf122.guildquest.entities.playablecharacters.Move;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;
import edu.uci.inf122.guildquest.entities.nonlivings.Chest;
import edu.uci.inf122.guildquest.ui.Page;
import edu.uci.inf122.guildquest.ui.TerminalGrid;
import edu.uci.inf122.guildquest.ui.playablecharacteruis.AssassinUI;
import edu.uci.inf122.guildquest.ui.playablecharacteruis.ClericUI;
import edu.uci.inf122.guildquest.ui.playablecharacteruis.PlayableCharacterUI;

// The rule: 
// starting point: (0, 0) with an NPC that needs to be escorted to the destination that the NPC knows. (The player does not know the destination)
// The player can ask the NPC for hints about the destination, but the NPC will give clear hints only if the destination is close (EX: steps). 
// => Which is the actual Grid for the destination 
// NPC still give hints even if the destination is far, but the hints are more vague (EX: direction).
// the player can move in four directions (up, down, left, right) and can move 1-3 spaces at a time 
//
// EX) player1 can move up 2 spaces, then right 1 space. (2, 1)
// EX) player2 can move down 3 spaces. (-3, 0)
// EX) player1 can move left 2 spaces. (0, -2) 
//
// The initial information the players have: 
// - current grid state for players 
//
// more information for later: 
// - if the player succeed in attacking an enemy, they will know the enemy's location. 
// - if the player with no NPC reaches the destination first, they will know the location of the destination. 
// - if the player with the NPC is within 2 spaces of the destination, they will get clear hints about the destination (ex: steps).
//
//
// The maximum number of moves for each turn will be 3. (1, 2) or (2, 1) or (-2, 1), etc. 
//
// Enemies are able to attack the player if they are adjacent to the player with the NPC (2 space away in any direction). (NO MOVE)
// If the player who is with the NPC is at the same grid cell as an enemy, the player will lose and the adventure will end.
// 
// if the player wants to attack, they can only attack enemies (No ohter npcs) (2 space away in any direction).]
//
// Chests and Keys will be placed on the grid and can be picked up by the player if they are adjacent to them. (1 space away in any direction)
// Chests can be opened by any keys. (3 keys and 3 chests on the grid)
// 
// OTHER-NPCs on Grid: 
// there are other NPCs on the grid that can give the player some items or hints. 
// the player enters the grid, then they interact with the NPCs.
//
// Win Condition: The player with the NPC reaches the destination. 
// Lose Condition: The player with the NPC is on the same grid cell as an enemy.
// 
// Starting point should be the same for both players (maybe) 
// The distance between Starting point and Destination must be at least 5 spaces away. 
// 
// Nice to have: 
// - If the player with no NPC reaches the destination first, they will know the location of the destination.

/**
 * The type Escort adventure.
 */
public class EscortAdventure extends MiniAdventure { // extends MiniAdventure {
    // private GridUI gridUI;
    // save/serialize
    private final TerminalGrid gridState;
    private PlayableCharacter player1;
    private PlayableCharacter player2;
    private List<Entity> enemies;
    private List<Entity> items;
    private List<Entity> npcs;
    private final static Page page = Page.getPage();
    private PlayableCharacter currentPlayer;
    // nice to have: difficulty levels that change the # of enemies and the size of
    // the grid, etc.

    /**
     * Instantiates a new Escort adventure.
     *
     * @param realms       the realms
     * @param entities     the entities
     * @param winCondition the win condition
     * @param players      the players
     */
    public EscortAdventure(List<Realm> realms, List<Entity> entities, List<WinCondition> winCondition,
            List<User> players) {
        super(realms, entities, winCondition, players);
        gridState = new TerminalGrid(6, 6);
        // pick a character and tools for the player: (PLACEHOLDER FOR NOW)
        // player1 = Assassin.getInstance(new Name("assassin")); // placeholder
        // player2 = Cleric.getInstance(new Name(players.get(1).getName())); //
        // placeholder
    }

    public void play() {
        // game logic to run the adventure
        // initialize the grid, place entities (randomly), and start the game loop
        // pick a character and tools for the player, initialize the grid with entities,
        // etc.

        initializeUser(); // set up who's with the NPC, etc. if we want to ask
        initializeGrid();
        gridState.render();
        while (true) {

            // accept player input
            playerTurns(); // will render
            allEnemyTurns();
            // advance the game state
            advanceCycle();
            // in the case the game should end after accepting input
            if (checkRunningCondition()) {
                break;
            }

            // check win conditions and update gameRunning accordingly

        }
    }

    /**
     * Accepts input and acts for player 1 and player 2
     * Can attack, move, or ask NPC for hints
     */
    public void playerTurns() {

        // before the player choose the move (move or attack), the NPC with them can
        // give them the fact if there ar enemies around them (2 spaces away in any
        // direction).

        // unsure what we mean by "the NPC with them"

        // String npcHint = nearbyNPC.speak();
        // String prompt = gridState.getGridStr()+npcHint+CHOICES_PROMPT
        boolean done = false;
        currentPlayer = player1;
        while (!done) {
            List<Move.ValidMoves> moves = currentPlayer.getMoves();
            String moveString = TerminalGrid.toOptions(moves);
            String moveRegex = TerminalGrid.toOptionsRegex(moves);
            String input = page.acceptStrUntil(currentPlayer.getName() + "'s turn!\n" + moveString, moveRegex);

            if (input.contains("move")) {
                if (!attemptMovePlayer(currentPlayer, input.charAt(input.length() - 1)))
                    continue;
            } else if (input.contains("attack")) {
                if (!attemptPlayerAttack(currentPlayer, input.charAt(input.length() - 1)))
                    continue;
            } else if (input.contains("heal self")) {
                if (!attemptPlayerHealSelf(currentPlayer))
                    continue;
            } else if (input.contains("heal")) {
                if (!attemptPlayerHealOther(currentPlayer, input.charAt(input.length() - 1)))
                    continue;
            } else if (input.equals("r")) {
                if (!makeHintRequest())
                    continue; // check to see if npc is nearby
            } else
                throw new IllegalStateException("Something went wrong with input, cannot parse");
            gridState.render();
            if (currentPlayer == player1)
                currentPlayer = player2;
            else
                done = true;
        }
        // if (player location is within 2 spaces of the destination) {
        // // give clear hints about the destination (ex: steps)
        // }
        // if (player location is closer to enemy) {
        // // let user know that they are close to an enemy.
        // }
        // // if the enemy attacks the player, the player will know the enemy's
        // location.

        // check if the player with the NPC is on the same grid cell as an enemy, if so,
        // end the game with a loss
        // if there is an NPC or Items, then interact with them (ex: pick up items, get
        // hints from NPCs, etc.)
        // if player with no NPC is on the same grid cell as the destination, then the
        // destination location is known.
        // if the player with the NPC is on the same grid cell as the destination, then
        // end the game with a win.

        // Enemies:
        // - attack player if adjacent to player with NPC
        // - if the player attacked them, the enemy can attack the player back during
        // their turn
    }

    private boolean attemptPlayerHealSelf(PlayableCharacter p) {
        if (p instanceof CanHealSelf healer) {
            if (p.isDead()) {
                page.print("You cannot heal yourself. You're dead!\n");
                return false;
            }
            if (p.getHealth().isFull()) {
                page.print("You're already at full health! Nothing to heal :)\n");
                return false;
            }
            if (p instanceof Cleric c) {
                c.heal(c.getHealingPower());
                page.print(c.getName() + " healed to " + c.getHealth() + '\n');
                return true;
            }
            throw new IllegalStateException("Error in PlayerHeal, something went wrong");
        } else {
            page.print("This character cannot heal\n");
            return false;
        }
    }

    private boolean attemptPlayerHealOther(PlayableCharacter p, char direction) {
        if (p instanceof CanHealOther healer) {
            if (p.isDead()) {
                page.print("You cannot heal others. You're dead!\n");
                return false;
            }
            if (gridState.isValidAdjacent(p, direction)) {
                GridCell targetCell = gridState.getCellAdjacent(p, direction);
                if (!targetCell.isEmpty()) {
                    Entity target = targetCell.getContent().get(0);
                    if (target instanceof PlayableCharacter otherPlayer) {
                        if (otherPlayer.getHealth().isFull()) {
                            page.print("Already at full health. Do something else.\n");
                            return false;
                        } else {
                            healer.heal(healer.getHealAmount(), otherPlayer);
                            return true;
                        }
                    } else {
                        page.print("can only heal other players for now.\n");
                        return false;
                    }
                } else {
                    page.print("Target cell is empty, cannot heal here\n");
                    return false;
                }
            } else {
                page.print("You cannot heal in that direction\n");
                return false;
            }
        } else {
            page.print("This character cannot others!\n");
            return false;
        }
    }

    public void acceptInput() {

    }

    private boolean attemptPlayerAttack(PlayableCharacter p, char direction) {
        GridCell attackTarget = gridState.getCellAdjacent(currentPlayer, direction);
        if (attackTarget.isEmpty() ||
                !(attackTarget.getContent().get(0) instanceof Hostile)) {
            page.print("Cannot attack that cell\n\n");
            return false;
        }
        int[] target = gridState.getLocationCords(gridState.getLocation(p));
        switch (direction) {
            case 'e' -> target[1] += 1;
            case 'w' -> target[1] -= 1;
            case 's' -> target[0] += 1;
            case 'n' -> target[0] -= 1;
            default -> throw new IllegalStateException("Unexpected direction: " + direction);
        }
        GridCell targetCell = gridState.getCell(target[0], target[1]);

        p.attack(targetCell.getContent().get(0)); // assume only on 1st layer
        if (targetCell.getContent().get(0) instanceof Goblin g) {
            if (g.isDead()) {
                gridState.removeEntity(g);
                enemies.remove(g);
                gridState.render();
            }
        }
        return true;
    }

    public void allEnemyTurns() {
        for (Entity h : enemies) {
            enemyTurn(h);
        }
    }

    /**
     * One enemy turn;
     *
     * @param enemy the enemy
     */
    public void enemyTurn(Entity enemy) {
        // checks if each enemy is near the players (within 2 spaces)
        // if so, the enemy will attack the players.
        // The enemy can attack one player per turn
        // but the priority is to attack the player with the NPC
        // or the closest player.

        // check if the enemy is within 2 spaces of the player with the NPC
        // if so, attack the players
        // else, skip the enemy's turn for now

        if (enemy instanceof Hostile h) {
            List<Entity> nearbyEntities = gridState.nearbyEntities(gridState.getLocation(enemy), 2);
            Entity target = h.prioritizeAttack(nearbyEntities);

            if (target == null)
                return;
            h.attack(target);
        }

        // // TODO: add more concrete logic for enemy attacks (ex: prioritize an enemy
        // who can attack many players)+

    }

    public void advanceCycle() {
    }

    public AdventureSnapshot saveSnapshot() {
        // logic to save the current state of the adventure
        return null; // placeholder
    }

    public void initializeGrid() {
        // logic to initialize the grid with entities, place the NPC to be escorted,
        // items, tresures, and enemies

        // current default is: (12, 12).
        // Starting point: (0, 0) with an NPC that needs to be escorted to the

        // they are all placed randomly, but the distance between the starting point and
        // enemies should be at least 6 spaces away
        Princess princess_npc = new Princess(
                new Name("princess"), new Health(10), new Amount(2));

        Goblin enemy1 = new Goblin(new Name("Goblin 1"), new Health(10), new Level(1));
        Goblin enemy2 = new Goblin(new Name("Goblin 2"), new Health(12), new Level(1));
        Goblin enemy3 = new Goblin(new Name("Goblin 3"), new Health(15), new Level(2));
        enemies = new ArrayList<>(List.of(enemy1, enemy2, enemy3));

        Item item1 = ItemFactory.createWeapon("blue sword", 1, "a blue sword", 6);
        Item item2 = ItemFactory.createWeapon("red stick", 1, "a flimsy red stick", 1);
        Item item3 = ItemFactory.createTool("green pickaxe", 1, "a green pickaxe");

        Chest chest1 = new Chest(new Name("chest1"), item1, new Text("a chest with a blue sword"));
        Chest chest2 = new Chest(new Name("chest2"), item2, new Text("a chest with a red stick"));
        Chest chest3 = new Chest(new Name("chest3"), item3, new Text("a chest with a green pickaxe"));
        items = new ArrayList<>(List.of(item1, item2, item3));

        NPC npc1 = new Ferryman(new Name("John Ferryman"), new Place(new Name("somewhere")), new Amount(10));
        NPC npc2 = new Ferryman(new Name("Expensive Ferryman"), new Place(new Name("far far away")), new Amount(100));

        npcs = new ArrayList<>(List.of(npc1, npc2));

        // randomly place the entities on the grid
        // princess, player1, and player2 are placed at the same starting point.
        gridState.setCell(0, 0, princess_npc);
        gridState.setCell(0, 1, player1);
        gridState.setCell(0, 2, player2);

        gridState.initializeGrid(enemies);
        gridState.initializeGrid(items);
        gridState.initializeGrid(npcs);
    }

    void initializePlayerCharacter() {
        // logic to let the player choose their character and tools, etc.
    }

    /**
     * Initialize user to be either Assassin or Cleric
     */
    public void initializeUser() {
        // logic to set up the user, which user is with the NPC, etc.
        // ask UI which characters they would like to be.
        int p1Choice = page.acceptIntUntil("""
                Player 1, would you like to be an Assassin, or a Cleric?
                0 - Assassin
                1 - Cleric
                """, 1);
        PlayableCharacterUI p1UI;
        PlayableCharacterUI p2UI;
        Cleric c = Cleric.getInstance(new Name("Cleric"));
        Assassin a = Assassin.getInstance(new Name("Assassin"));
        if (p1Choice == 1) {
            player1 = c;
            player2 = a;
            p1UI = new ClericUI(c);
            p2UI = new AssassinUI(a);
            page.print("Player 1 is the Cleric, player 2 is the Assassin\n");
        } else {
            player1 = a;
            player2 = c;
            p1UI = new AssassinUI(a);
            p2UI = new ClericUI(c);
            page.print("Player 1 is the Assassin, player 2 is the Cleric\n");
        }
        p1UI.display();
        p2UI.display();
        currentPlayer = player1;

    }

    /**
     * Moves the player in the direction desired.
     *
     * @param p         the p
     * @param direction the direction
     */
    public boolean attemptMovePlayer(PlayableCharacter p, char direction) {
        if (!gridState.canMove(currentPlayer, direction)) {
            page.print("Cannot move in that direction\n");
            return false;
        }
        int[] target = gridState.getLocationCords(gridState.getLocation(p));
        gridState.removeEntity(p);
        switch (direction) {
            case 'e' -> target[1] += 1;
            case 'w' -> target[1] -= 1;
            case 's' -> target[0] += 1;
            case 'n' -> target[0] -= 1;
            default -> throw new IllegalStateException("Unexpected direction: " + direction);
        }
        GridCell targetCell = gridState.getCell(target[0], target[1]);
        if (targetCell.getContent().stream().anyMatch(e -> e instanceof Chest)) {
            Chest chest = (Chest) targetCell.getContent().stream().filter(e -> e instanceof Chest).findFirst().get();
            Item item = chest.take();
            if (item != null) {
                p.addToInventory(item);
            }
            gridState.removeEntity(chest);
        }
        if (!gridState.setCellWithChecking(targetCell, p)) {
            return false;
        }
        return true;
    }

    public void makeAttack(int currentPlayer) {
        // logic to let the player make attack choices

        // step1: pick the direction to attack (since they don't know the enemy's
        // location)
        // player can choose: up, down, left, right
        String direction = "up"; // placeholder for player input
        int x = 0; // placeholder for player input
        int y = 0;
        switch (direction) {
            case "up" -> {
                // logic to attack up
                y += 2;
                break;
            }
            case "down" -> {
                // logic to attack down
                y -= 2;
                break;
            }
            case "left" -> {
                // logic to attack left
                x -= 2;
                break;
            }
            case "right" -> {
                // logic to attack right
                x += 2;
                break;
            }
            default -> {
                // prompt player for valid input
            }
        }
        // step2: collect all the enemies in the chosen direction within 2 spaces
        int[] playerPosition = gridState.getLocationCords(currentPlayer == 0 ? player1 : player2);
        List<Entity> enemiesToAttack = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            int newRow = playerPosition[0] + (y == 0 ? 0 : i * (y / Math.abs(y)));
            int newCol = playerPosition[1] + (x == 0 ? 0 : i * (x / Math.abs(x)));
            if (gridState.isValidPosition(newRow, newCol)) {
                for (Entity content : gridState.getCellContent(newRow, newCol).getContent()) {
                    if (content instanceof Goblin) { // check if the entity is an enemy
                        enemiesToAttack.add(content);
                    }
                }
            }
        }
        // step3: apply the attack to all the enemies in that direction within 2 spaces
        for (Entity enemy : enemiesToAttack) {
            if (enemy instanceof Goblin goblinEnemy) {
                if (currentPlayer == 0) {
                    player1.attack(goblinEnemy);
                } else {
                    player2.attack(goblinEnemy);
                }
                // if the enemy is defeated, remove it from the grid and let the player know
                if (goblinEnemy.getHealth().getHealth() <= 0) { // chain..!
                    int[] enemyPosition = gridState.getLocationCords(goblinEnemy);
                    gridState.removeEntity(enemyPosition[0], enemyPosition[1], goblinEnemy);
                    page.print("You defeated an enemy!\n");
                }
            }
        }
        // edge cases:
        // if the player is at the edge of the grid
    }

    public boolean makeHintRequest() {
        // logic to let the player ask for hints

        // check the distance between the player with the NPC and the destination
        int[] playerPosition = gridState.getLocationCords(player1);
        if (gridState.checkDistance(playerPosition[0], playerPosition[1], 2)) {
            // give clear hints about the destination (ex: steps)
            // tell drirection
        } else {
            // give vague hints about the destination (ex: direction)
            // tell "Far....."
            page.print("destination is far...\n");
        }
        return true;
    }

    public boolean checkRunningCondition() {
        // learn to use actual WinConditoin object here.
        if (player1.isDead() || player2.isDead()) {
            page.print("You died! You lost.");
            return true;
        } else
            return false;
    }

    public static void main(String[] args) {
        List<WinCondition> winConditions = List.of(new TimeLimitCondition(new Time(2)));

        EscortAdventure adventure = new EscortAdventure(
                null,
                null,
                winConditions,
                null);

        adventure.play();
    }

}
