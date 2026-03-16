package edu.uci.inf122.guildquest.engine;

import edu.uci.inf122.guildquest.api.AdventureSnapshot;
import edu.uci.inf122.guildquest.api.state.State;
import edu.uci.inf122.guildquest.api.win_conditions.WinCondition;
import edu.uci.inf122.guildquest.content.Realm;
import edu.uci.inf122.guildquest.content.User;
import edu.uci.inf122.guildquest.entities.Entity;

import java.util.List;

public abstract class MiniAdventure {
    protected List<Realm> realms;
    protected List<Entity> entities;
    protected List<WinCondition> winConditions;
    protected List<User> players;
    protected State state;
    protected AdventureSnapshot previousSave;

    protected MiniAdventure(List<Realm> realms, List<Entity> entities,
                            List<WinCondition> winConditions, List<User> players,
                            State state) {
        this.realms = realms;
        this.entities = entities;
        this.winConditions = winConditions;
        this.players = players;
        this.state = state;
        this.previousSave = null;
    }

    public abstract String getName();
    public abstract String getDescription();

    public abstract void play();
    public abstract void acceptInput();
    public abstract void advanceCycle();
    public abstract AdventureSnapshot saveSnapshot();

    public List<Realm> getRealms() { return realms; }
    public List<Entity> getEntities() { return entities; }
    public List<WinCondition> getWinConditions() { return winConditions; }
    public List<User> getPlayers() { return players; }
    public State getState() { return state; }
    public AdventureSnapshot getPreviousSave() { return previousSave; }
}
