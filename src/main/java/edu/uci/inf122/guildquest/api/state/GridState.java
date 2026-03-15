package edu.uci.inf122.guildquest.api.state;

import java.util.ArrayList;
import java.util.List;

public abstract class GridState implements State {
    private int length;
    private int width;
    private List<List<GridCell>> grid;

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
}
    public GridState(int length, int width) {
        this.length = length;
        this.width = width;
        grid = new java.util.ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<GridCell> row = new java.util.ArrayList<>();
            for (int j = 0; j < width; j++) {
                row.add(new GridCell());
            }
            grid.add(row);
        }
    }
}
