package edu.uci.inf122.guildquest.adventures;

import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.User;
import edu.uci.inf122.guildquest.entities.Entity;
import java.util.ArrayList;
import java.util.List;
import edu.uci.inf122.guildquest.adventures.WinCondition;
import edu.uci.inf122.guildquest.ui.GridUI;

public class EscortAdventure {
    private List<Realm> realms;
    private List<Entity> entities;
    private WinCondition winCondition;
    private GridUI gridUI;
    private List<User> players;
    // save/serialize

    public EscortAdventure(List<Realm> realms, List<Entity> entities, WinCondition winCondition) implements SerializableAdventure {
        this.realms = realms;
        this.entities = entities;
        this.winCondition = winCondition;
        this.gridUI = new GridUI(10, 10); // Example grid size
        this.players = new ArrayList<>(); // 2 players for escort adventure
        /// this.save();
        /// this.load(this.save());
    }

    public void play() {
        // game logic to run the adventure
    }

    public boolean acceptInput() {
        // handle player input
        return true; // placeholder
    }

    public void advanceCycle() {
        // advance the game state, move entities, check win conditions
    }

}
