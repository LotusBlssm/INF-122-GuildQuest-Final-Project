package edu.uci.inf122.guildquest.adventures;

import edu.uci.inf122.guildquest.api.AdventureSnapshot;
import edu.uci.inf122.guildquest.api.state.State;
import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.User;
import java.util.ArrayList;
import java.util.List;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
// import edu.uci.inf122.guildquest.ui;
import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.api.state.GridState;

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
// Enemies will be placed on the grid and will move randomly. (the steps they move will be max 2)
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

    }

    public void advanceCycle() {
        // advance the game state, move entities, check win conditions
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

    }

    public void initializeUser() {
        // logic to set up the user, which user is with the NPC, etc.
        User player1 = getPlayer(0);
        User player2 = getPlayer(1);

        // default is player1 is with the NPC, but maybe we can ask. (so, )
        // isNPCWithPlayer = 0;

    }

    public boolean checkRunningCondition() {
        // check if the game should continue running (win/lose conditions)
        return false; // placeholder
    }

}
