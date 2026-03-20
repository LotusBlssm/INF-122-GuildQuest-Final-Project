package edu.uci.inf122.guildquest.adventures;

import edu.uci.inf122.guildquest.api.state.GridState;

public class RaidGridState extends GridState {

    public RaidGridState(int length, int width) {
        super(length, width);
    }

    @Override
    public void render() {
        // Rendering is handled by TimedRaidAdventure.renderGrid()
    }

    @Override
    public void changeState() {
        // No-op for now
    }
}
