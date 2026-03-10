package edu.uci.inf122.guildquest.api.state;

import java.util.List;

public abstract class GridState implements State{
    private int length;
    private int width;
    private List<List<GridCell>> grid;

    @Override
    public abstract void render(); // different types of rendering for different things.

    @Override
    public abstract void changeState();

    public List<List<GridCell>> getGrid() {
        return grid;
    }
    public int getLength() {
        return length;
    }
    public int getWidth() {
        return width;
    }
}
