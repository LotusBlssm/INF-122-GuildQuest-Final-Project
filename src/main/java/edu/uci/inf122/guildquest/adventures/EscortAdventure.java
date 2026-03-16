package edu.uci.inf122.guildquest.adventures;

import edu.uci.inf122.guildquest.api.AdventureSnapshot;
import edu.uci.inf122.guildquest.api.state.State;
import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.GameCharacter;
import edu.uci.inf122.guildquest.content.User;
import java.util.ArrayList;
import java.util.List;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
// import edu.uci.inf122.guildquest.ui;
import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.api.state.GridState;
import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.PlayableCharacter;
import edu.uci.inf122.guildquest.entities.Assassin;
import edu.uci.inf122.guildquest.entities.Cleric;
import edu.uci.inf122.guildquest.entities.Kinght;
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
// The maximum number of moves for each turn will be 3. (1, 2) or (2, 1) or (-2, 1), etc. 
//
// Enemies are able to attack the player if they are adjacent to the player with the NPC (1 space away in any direction). (NO MOVE)
// If the player who is with the NPC is at the same grid cell as an enemy, the player will lose and the adventure will end.
// 
// if the player wants to attack an enemy, the can only attack an enemy. that is adjacent to them (2 space away in any direction).
// maybe: depending on the weapon, the player can attack in different styles (ex: a bow can attack an enemy that is 2 spaces away, but a sword can only attack an enemy that is 1 space away).
//
// Treasures and items will be placed on the grid and can be picked up by the player if they are adjacent to them. (1 space away in any direction)
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
    private GridState gridState;
    private int playerWithNPC;
    private PlayableCharacter player1;
    private PlayableCharacter player2;
    // nice to have: difficulty levels that change the # of enemies and the size of
    // the grid, etc.

    public EscortAdventure(List<Realm> realms, List<String> entities, List<WinCondition> winCondition,
            List<User> players) {
        // super(realms, entities, winCondition, new ArrayList<>());
        // this.gridUI = new GridUI(10, 10);
        super(realms, entities, winCondition, players);
        gridState = new GridState(12, 12) {
            @Override
            public void render() {
                // render the grid state to the UI
            }

            @Override
            public void changeState() {
                // logic to change the grid state based on game events
            }
        };
        // pick a character and tools for the player: (PLACEHOLDER FOR NOW)
        player1 = new Assasin(players.get(0).getName()); // placeholder
        player2 = new Cleric(players.get(1).getName()); // placeholder

        playerWithNPC = 0; // default is player1 is with the NPC, but maybe we can ask.
    }

    public void play() {
        // game logic to run the adventure
        // initialize the grid, place entities (randomly), and start the game loop
        // pick a character and tools for the player, initialize the grid with entities,
        // etc.

        initializeGrid();
        initializeUser(); // set up who's with the NPC, etc.
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

        if (player location is within 2 spaces of the destination) {
            // give clear hints about the destination (ex: steps) 
        } 
        if (player location is closer to enemy) {
            // let user know that they are close to an enemy. 
        }
        // if the enemy attacks the player, the player will know the enemy's location. 

        // move logic: psuedocode for now
        if (move) {
            // logic to accept input for each player character

            // moving logic: (take out later)
            int moveRow = 4; // placeholder for player input
            int moveCol = 4; // placeholder for player input
            while (Math.abs(moveRow) + Math.abs(moveCol) > 3 || Math.abs(moveRow) + Math.abs(moveCol) == 0) {
                // prompt player for valid input
                // update moveRow and moveCol based on player input

                // get move values from player input (for now, we will just use placeholders,
                // but we will need to implement actual input handling later)
                moveRow = 2;
                moveCol = 1;
                if (gridState.isValidPosition(moveRow, moveCol)) {
                    // move the player character on the grid
                    // for now, we will just set the new position of the player character on the
                    // grid
                    // but we will need to update the player's actual position in the game state
                    // later
                    gridState.setCell(moveRow, moveCol, character.getName());
                } else {
                    // prompt player for valid input
                }
            }
            // check if the player with the NPC is on the same grid cell as an enemy, if so,
            // end the game with a loss
            // if there is an NPC or Items, then interact with them (ex: pick up items, get hints from NPCs, etc.)
            // if player with no NPC is on the same grid cell as the destination, then the destination location is known.
            // if the player with the NPC is on the same grid cell as the destination, then end the game with a win.

            // for (Entity content : gridState.getCellContent(moveRow,
            // moveCol).getContent()) {
            // if (content.contains("Enemy") && playerWithNPC ==
            // playerCharacters.indexOf(character)) {
            // // end the game with a loss
            // System.out.println("Game Over! You were caught by an enemy.");
            // System.exit(0);
            // }
            // }
        }

        // attack logic: psuedocode for now
        if (attack) {
            // TODO: Identify which enemy the player wants to attack (only one enemy can be attacked per turn) 
            // ask the player for the direction (since they don't know the enemy's location, they only know there is an enemy near them (within 2 spaces)) 
            // if the user choice (direction) is correct, then the attack is successful and the enemies take damage 
            // player can choose: up, down, left, right
            // the attack is applied to all the enemies in that direction within 2 spaces 

            // collect the enemies in the chosen direction within 2 spaces (don't include the non-enemy entities) 
            for (enemy in enemies in that direction within 2 spaces) {
                 player.attack(enemy); 
                 if (enemy is defeated) {
                    // remove the enemy from the grid
                    // let the player know that they defeated an enemy. 
                 }
            }
        }


        // Enemies:
        // - attack player if adjacent to player with NPC
        // - if the player attacked them, the enemy can attack the player back during their turn

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

        // for now, we will just use String for the entities, but we will replace it
        // with actual Entity objects later.
        String princess_npc = "Princess NPC"; // placeholder for the NPC that needs to be escorted
        UUID user1 = UUID.randomUUID(); // placeholder for the destination that the NPC knows
        UUID user2 = UUID.randomUUID();
        // need to replace with actual playable characters later
        GameCharacter player1_char = new GameCharacter("Player 1", "Warrior", 1, user1); // placeholder for player 1's
        GameCharacter player2_char = new GameCharacter("Player 2", "Mage", 1, user2); // placeholder for player 2's
        // character

        String enemy1 = "Goblin Enemy1"; // placeholder for enemy 1
        String enemy2 = "Goblin Enemy2"; // placeholder for enemy 2
        String enemy3 = "Goblin Enemy3"; // placeholder for enemy 3
        String[] enemies = { enemy1, enemy2, enemy3 };

        String item1 = "Item1"; // placeholder for item 1
        String item2 = "Item2"; // placeholder for item 2
        String item3 = "Item3"; // placeholder for item 3
        String[] items = { item1, item2, item3 };

        String npc1 = "NPC1"; // placeholder for other NPC 1
        String npc2 = "NPC2"; // placeholder for other NPC 2
        String[] npcs = { npc1, npc2 };

        String destination = "Destination";

        // randomly place the entities on the grid
        // princess, player1, and player2 are placed at the same starting point.
        gridState.setCell(0, 0, princess_npc);
        gridState.setCell(0, 0, "Player 1");
        gridState.setCell(0, 0, "Player 2");

        gridState.initializeGrid(enemies);
        gridState.initializeGrid(items);
        gridState.initializeGrid(npcs);

    }

    void initializePlayerCharacter() {
        // logic to let the player choose their character and tools, etc.
    }

    public void initializeUser() {
        // logic to set up the user, which user is with the NPC, etc.
        // default is player1 is with the NPC, but maybe we can ask. (so, )
        // isNPCWithPlayer = 0;

    }

    public boolean checkRunningCondition() {
        // check if the game should continue running (win/lose conditions)
        return false; // placeholder
    }

}
