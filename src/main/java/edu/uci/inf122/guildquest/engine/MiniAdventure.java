package edu.uci.inf122.guildquest.engine;

import edu.uci.inf122.guildquest.api.AdventureSnapshot;
import edu.uci.inf122.guildquest.api.state.State;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.User;
import edu.uci.inf122.guildquest.entities.Entity;

import java.util.List;

public abstract class MiniAdventure {
    private List<Realm> realms;
    private List<Entity> entities; // placeholder for now - will be replaced with actual Entity objects
    private List<WinCondition> winCondition;
    private List<User> players;
    private State state;
    private AdventureSnapshot previousSave = null;

    public abstract void play();

    public abstract void acceptInput();

    public abstract void advanceCycle();

    public abstract AdventureSnapshot saveSnapshot();

    public MiniAdventure(List<Realm> realms, List<Entity> entities, List<WinCondition> winCondition,
            List<User> players) {
        this.realms = realms;
        this.entities = entities;
        this.winCondition = winCondition;
        this.players = players;

    }

    public User getPlayer(int index) {
        return players.get(index);
    }
}
