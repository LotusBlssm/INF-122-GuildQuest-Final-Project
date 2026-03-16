package edu.uci.inf122.guildquest.api.state;

import edu.uci.inf122.guildquest.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class GridState implements State {
    private int length;
    private int width;
    protected List<List<GridCell>> grid;

    // Added constructor to initialize the grid structure
    public GridState(int length, int width) {
        this.length = length;
        this.width = width;
        this.grid = new ArrayList<>();

        for (int row = 0; row < length; row++) {
            List<GridCell> currentRow = new ArrayList<>();
            for (int col = 0; col < width; col++) {
                currentRow.add(new GridCell());
            }
            grid.add(currentRow);
        }
    }

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

    // Helper method to check if coordinates are inside the grid
    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < length && col >= 0 && col < width;
    }

    // Helper method to get a specific cell
    public GridCell getCell(int row, int col) {
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Invalid grid position: (" + row + ", " + col + ")");
        }
        return grid.get(row).get(col);
    }

    // Helper method to get a specific cell
    public void setCell(int row, int col, Entity e) {
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Invalid grid position: (" + row + ", " + col + ")");
        }
        grid.get(row).get(col).setContent(e);
    }

    public int getDistance(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) + Math.abs(col1 - col2);
    }

    public void initializeGrid(Entity[] entities) {
        // logic to initialize the grid with the entities in the adventure
        // for now, we will just randomly place the entities on the grid, but we can
        // make it more complex later (e.g. certain entities can only be placed in
        // certain areas, etc.)
        int placedAllEntities = 0;
        while (placedAllEntities < entities.length) {
            int row = (int) (Math.random() * getLength());
            int col = (int) (Math.random() * getWidth());
            if (getCell(row, col).isEmpty() && getDistance(0, 0, row, col) >= 6) {
                setCell(row, col, entities[placedAllEntities]);
                placedAllEntities += 1;
            }
        }
    }

    public GridCell getCellContent(int row, int col) {
        return getCell(row, col);
    }
}
