package edu.uci.inf122.guildquest.adventures;

import edu.uci.inf122.guildquest.api.AdventureSnapshot;
import edu.uci.inf122.guildquest.api.state.State;
import edu.uci.inf122.guildquest.api.win_conditions.TimeLimitCondition;
import edu.uci.inf122.guildquest.content.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
// import edu.uci.inf122.guildquest.ui;
import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.api.state.GridState;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.domain_primitives.*;
import edu.uci.inf122.guildquest.entities.npcs.*;
import edu.uci.inf122.guildquest.entities.playablecharacters.Assassin;
import edu.uci.inf122.guildquest.entities.playablecharacters.Cleric;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;
import edu.uci.inf122.guildquest.ui.Page;
import edu.uci.inf122.guildquest.ui.TerminalGrid;

import java.util.UUID;

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
    // nice to have: difficulty levels that change the # of enemies and the size of
    // the grid, etc.

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
        while (true) {
            // render the grid state
            gridState.render();

            // accept player input
            acceptInput();
            // advance the game state
            // in the case the game should end after accepting input
            if (checkRunningCondition()) {
                break;
            }
            advanceCycle();
            // check win conditions and update gameRunning accordingly
            break;

        }
    }

    public void acceptInput() {
        // handle player input: moving the player, attacking enemies

        // Players:
        // - attack
        // - move
        // - ask NPC for hints

        // before the player choose the move (move or attack), the NPC with them can
        // give them the fact if there ar enemies around them (2 spaces away in any
        // direction).

        int currentPlayer = 0;
        while (currentPlayer < 1) {
            int choice = 1; // placeholder for player input
            switch (choice) {
                case 1 -> {
                    // move logic
                    makeMove(currentPlayer);
                    currentPlayer += 1;
                }
                case 2 -> {
                    // attack logic
                    makeAttack(currentPlayer);
                    currentPlayer += 1;
                }
                case 3 -> {
                    // ask for hints
                    makeHintRequest();
                    currentPlayer += 1;
                }
                default -> {
                    // prompt player for valid input
                }
            }
        }
        // if (player location is within 2 spaces of the destination) {
        // // give clear hints about the destination (ex: steps)
        // }
        // if (player location is closer to enemy) {
        // // let user know that they are close to an enemy.
        // }
        // // if the enemy attacks the player, the player will know the enemy's
        // location.

        // // move logic: psuedocode for now
        // if (move) {
        // // logic to accept input for each player character

        // // moving logic: (take out later)
        // int moveRow = 4; // placeholder for player input
        // int moveCol = 4; // placeholder for player input
        // while (Math.abs(moveRow) + Math.abs(moveCol) > 3 || Math.abs(moveRow) +
        // Math.abs(moveCol) == 0) {
        // // prompt player for valid input
        // // update moveRow and moveCol based on player input

        // // get move values from player input (for now, we will just use placeholders,
        // // but we will need to implement actual input handling later)
        // moveRow = 2;
        // moveCol = 1;
        // if (gridState.isValidPosition(moveRow, moveCol)) {
        // // move the player character on the grid
        // // for now, we will just set the new position of the player character on the
        // // grid
        // // but we will need to update the player's actual position in the game state
        // // later
        // gridState.setCell(moveRow, moveCol, character.getName());
        // } else {
        // // prompt player for valid input
        // }
        // }
        // check if the player with the NPC is on the same grid cell as an enemy, if so,
        // end the game with a loss
        // if there is an NPC or Items, then interact with them (ex: pick up items, get
        // hints from NPCs, etc.)
        // if player with no NPC is on the same grid cell as the destination, then the
        // destination location is known.
        // if the player with the NPC is on the same grid cell as the destination, then
        // end the game with a win.

        // for (Entity content : gridState.getCellContent(moveRow,
        // moveCol).getContent()) {
        // if (content.contains("Enemy") && playerWithNPC ==
        // playerCharacters.indexOf(character)) {
        // // end the game with a loss
        // System.out.println("Game Over! You were caught by an enemy.");
        // System.exit(0);
        // }
        // }
        // }

        // attack logic: psuedocode for now
        // if (attack) {
        // TODO: Identify which enemy the player wants to attack
        // ask the player for the direction (since they don't know the enemy's location,
        // they only know there is an enemy near them (within 2 spaces))
        // if the user choice (direction) is correct, then the attack is successful and
        // the enemies take damage
        // player can choose: up, down, left, right
        // the attack is applied to all the enemies in that direction within 2 spaces

        // collect the enemies in the chosen direction within 2 spaces (don't include
        // the non-enemy entities)
        // for (enemy in enemies in that direction within 2 spaces) {
        // player.attack(enemy);
        // if (enemy is defeated) {
        // // remove the enemy from the grid
        // // let the player know that they defeated an enemy.
        // }
        // }
        // }

        // Enemies:
        // - attack player if adjacent to player with NPC
        // - if the player attacked them, the enemy can attack the player back during
        // their turn

    }

    // enemy turn logic: psuedocode for now
    public void enemyTurn() {
        // checks if each enemy is near the players (within 2 spaces)
        // if so, the enemy will attack the players.
        // The enemy can attack one player per turn
        // but the priority is to attack the player with the NPC
        // or the closest player.

        // for (enemey in enemies) {
        // if (enemy is within 2 spaces of player with NPC) {
        // enemy.attack(player with NPC);
        // } else if (enemy is within 2 spaces of the other player) {
        // enemy.attack(the other player);
        // }
        // }

        // check if the enemy is within 2 spaces of the player with the NPC
        // if so, attack the players
        // else, skip the enemy's turn for now
        int[] player1Position = gridState.getLocation(this.player1);
        int[] player2Position = gridState.getLocation(this.player2);
        for (Entity enemy : enemies) {
            if (enemy instanceof Goblin goblinEnemy) {
                int[] enemyPosition = gridState.getLocation(enemy);
                if (gridState.getDistance(player1Position, enemyPosition) <= 2) {
                    goblinEnemy.attack(player1);
                }
                if (gridState.getDistance(player2Position, enemyPosition) <= 2) {
                    goblinEnemy.attack(player2);
                }
                break; // only one enemy can attack per turn
                // TODO: add more concrete logic for enemy attacks (ex: prioritize an enemy who
                // can attack many players)
            }
        }

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
        // destination that

        // two NPCs on grid
        // 3 enemies on grid
        // 3 items on grid

        // they are all placed randomly, but the distance between the starting point and
        // enemies should be at least 6 spaces away
        Princess princess_npc = new Princess(
                new Name("princess"), new Health(10), new Amount(2));

        Goblin enemy1 = new Goblin(new Name("Goblin 1"), new Health(10), new Level(1));
        Goblin enemy2 = new Goblin(new Name("Goblin 2"), new Health(12), new Level(1));
        Goblin enemy3 = new Goblin(new Name("Goblin 3"), new Health(15), new Level(2));
        enemies = new ArrayList<>();
        enemies.add(enemy1);
        enemies.add(enemy2);
        enemies.add(enemy3);

        Item item1 = ItemFactory.createWeapon("blue sword", 1, "a blue sword", 6);
        Item item2 = ItemFactory.createWeapon("red stick", 1, "a flimsy red stick", 1);
        Item item3 = ItemFactory.createTool("green pickaxe", 1, "a green pickaxe");
        items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);

        NPC npc1 = new Ferryman(new Name("John Ferryman"), new Place(new Name("somewhere")), new Amount(10)); // placeholder
                                                                                                              // for
                                                                                                              // other
                                                                                                              // NPC 1
        NPC npc2 = new Ferryman(new Name("Expensive Ferryman"), new Place(new Name("far far away")), new Amount(100)); // placeholder
                                                                                                                       // for
                                                                                                                       // other
                                                                                                                       // NPC
                                                                                                                       // 1

        npcs = new ArrayList<>();
        npcs.add(npc1);
        npcs.add(npc2);

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

    public void initializeUser() {
        // logic to set up the user, which user is with the NPC, etc.
        // ask UI which characters they would like to be.
        int p1Choice = page.acceptIntUntil("""
                Player 1, would you like to be an Assassin, or a Cleric?
                0 - Assassin
                1 - Cleric
                """, 1);
        if (p1Choice == 1) {
            player1 = Cleric.getInstance(new Name("Player 1")); // placeholder
            player2 = Assassin.getInstance(new Name("Player 2"));
            page.print("Player 1 is the assassin, player 2 is the cleric\n");
        } else {
            player1 = Assassin.getInstance(new Name("Assassin")); // placeholder
            player2 = Cleric.getInstance(new Name("Cleric"));
            page.print("Player 1 is the assassin, player 2 is the cleric\n");
        }

    }

    // player action logic: move, attack, ask for hints
    public void makeMove(int playerNumber) {
        // logic to let the player make move choices
        PlayableCharacter currentPlayer = playerNumber == 0 ? player1 : player2;
        // get move values from player input
        int moveRow = 4; // placeholder for player input
        int moveCol = 4; // placeholder for player input
        while (!gridState.isValidPosition(moveRow, moveCol)
                || !gridState.checkDistance(moveRow, moveCol, 3)) {
            // prompt player for valid input
            moveRow = 2;
            moveCol = 1;
        }
        // move the player character on the grid
        gridState.setCell(moveRow, moveCol, currentPlayer);
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
        int[] playerPosition = gridState.getLocation(currentPlayer == 0 ? player1 : player2);
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
                    int[] enemyPosition = gridState.getLocation(goblinEnemy);
                    gridState.removeEntity(enemyPosition[0], enemyPosition[1], goblinEnemy);
                    page.print("You defeated an enemy!\n");
                }
            }
        }
        // edge cases:
        // if the player is at the edge of the grid
    }

    public void makeHintRequest() {
        // logic to let the player ask for hints

        // check the distance between the player with the NPC and the destination
        int[] playerPosition = gridState.getLocation(player1);
        if (gridState.checkDistance(playerPosition[0], playerPosition[1], 2)) {
            // give clear hints about the destination (ex: steps)
            // tell drirection
        } else {
            // give vague hints about the destination (ex: direction)
            // tell "Far....."
        }
    }

    public boolean checkRunningCondition() {
        // logic to check if the adventure should continue running
        // check win conditions and lose conditions
        return false; // placeholder
    }

    public static void main(String[] args) {
        List<WinCondition> winConditions = new ArrayList<>();
        winConditions.add(new TimeLimitCondition(new Time(2)));
        EscortAdventure adventure = new EscortAdventure(
                null,
                null,
                winConditions,
                null);

        adventure.play();
    }

}
