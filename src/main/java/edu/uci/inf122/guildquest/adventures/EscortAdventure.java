package edu.uci.inf122.guildquest.adventures;

import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.User;
import java.util.ArrayList;
import java.util.List;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
// import edu.uci.inf122.guildquest.ui;
import edu.uci.inf122.guildquest.engine.MiniAdventure;
import edu.uci.inf122.guildquest.api.state.GridState;

// placeholder class for now
public class EscortAdventure { // extends MiniAdventure {
    // private GridUI gridUI;
    // save/serialize
    private GridState gridState;

    public EscortAdventure(List<Realm> realms, List<String> entities, List<WinCondition> winCondition) {
        // super(realms, entities, winCondition, new ArrayList<>());
        // this.gridUI = new GridUI(10, 10);
        gridState = new GridState(10, 10) {
            @Override
            public void render() {
                // render the grid state to the UI
            }

            @Override
            public void changeState() {
                // logic to change the grid state based on game events
            }
        };
    }

    public void play() {
        // game logic to run the adventure
        // initialize the grid, place entities (randomly), and start the game loop
        boolean gameRunning = true;
        while (gameRunning) {
            // render the grid state
            gridState.render();
            // accept player input
            acceptInput();
            // advance the game state
            advanceCycle();
            // check win conditions and update gameRunning accordingly
        }
    }

    public void acceptInput() {
        // handle player input
    }

    public void advanceCycle() {
        // advance the game state, move entities, check win conditions
    }

}
