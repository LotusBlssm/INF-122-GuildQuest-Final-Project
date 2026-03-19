package edu.uci.inf122.guildquest.api.state;

import edu.uci.inf122.guildquest.entities.Entity;
import edu.uci.inf122.guildquest.entities.nonlivings.Chest;
import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

import java.lang.reflect.Array;
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

    /**
     * Moves an entity into a cell at row, col, IF it is empty.
     *
     * @param targetCell the targetcell
     * @param e          the entity
     * @return whether operation succeeded or not
     */
    // Helper method to get a specific cell
    public boolean setCellWithChecking(GridCell targetCell, Entity e) {
        int[] loc = getLocationCords(targetCell);
        int row = loc[0];
        int col = loc[1];
        if (!isValidPosition(row, col)) {
            throw new IllegalArgumentException("Invalid grid position: (" + row + ", " + col + ")");
        }
        if (grid.get(row).get(col).hasContent()) {
            if (!grid.get(row).get(col).getContent().stream().anyMatch(entity -> entity instanceof Chest)) {
                return false;
            }
        }
        grid.get(row).get(col).setContent(e);
        return true;
    }

    public boolean canMove(Entity e, char direction) {
        int[] target = getLocationCords(getLocation(e));
        switch (direction) {
            case 'e' -> target[1] += 1;
            case 'w' -> target[1] -= 1;
            case 's' -> target[0] += 1;
            case 'n' -> target[0] -= 1;
            default -> throw new IllegalStateException("Unexpected direction: " + direction);
        }
        return isValidPosition(target[0], target[1]) && getCell(target[0], target[1]).isEmpty();
    }

    public int getDistance(int row1, int col1, int row2, int col2) {
        return Math.abs(row1 - row2) + Math.abs(col1 - col2);
    }

    public void initializeGrid(List<Entity> entities) {
        // logic to initialize the grid with the entities in the adventure
        // for now, we will just randomly place the entities on the grid, but we can
        // make it more complex later (e.g. certain entities can only be placed in
        // certain areas, etc.)
        int placedAllEntities = 0;
        while (placedAllEntities < entities.size()) {
            int row = (int) (Math.random() * getLength());
            int col = (int) (Math.random() * getWidth());
            if (getCell(row, col).isEmpty() && getDistance(0, 0, row, col) >= 6) {
                setCell(row, col, entities.get(placedAllEntities));
                placedAllEntities += 1;
            }
        }
    }

    public GridCell getCellContent(int row, int col) {
        return getCell(row, col);
    }

    public GridCell getLocation(Entity entity) {
        for (int row = 0; row < getLength(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                GridCell cell = getCell(row, col);
                if (!cell.isEmpty() && cell.getContent().contains(entity)) {
                    return cell;
                }
            }
        }
        throw new IllegalArgumentException("Entity not found on the grid.");
    }

    public int[] getLocationCords(GridCell cellTarget) {
        for (int row = 0; row < getLength(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                GridCell cell = getCell(row, col);
                if (cell == cellTarget) {
                    return new int[] { row, col };
                }
            }
        }
        throw new IllegalArgumentException("Entity not found on the grid.");
    }

    public int[] getLocationCords(Entity e) {
        GridCell cellTarget = getLocation(e);
        for (int row = 0; row < getLength(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                GridCell cell = getCell(row, col);
                if (cell == cellTarget) {
                    return new int[] { row, col };
                }
            }
        }
        throw new IllegalArgumentException("Entity not found on the grid.");
    }

    public boolean checkDistance(int row, int col, int distance) {
        return Math.abs(row) + Math.abs(col) <= distance;
    }

    public void removeEntity(int row, int col, Entity entity) {
        removeEntity(entity);
    }

    public void removeEntity(Entity entity) {
        GridCell cell = getLocation(entity);
        if (!cell.isEmpty() && cell.getContent().contains(entity)) {
            cell.removeContent();
            return;
        }
        return; // Entity not found, do nothing
    }

    public int getDistance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }

    public int getDistance(GridCell c1, GridCell c2) {
        int[] pos1 = getLocationCords(c1);
        int[] pos2 = getLocationCords(c2);
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }

    public GridCell getCellAdjacent(Entity reference, char direction) {
        int[] init = getLocationCords(getLocation(reference));
        switch (direction) {
            case 'e' -> init[1] += 1;
            case 'w' -> init[1] -= 1;
            case 's' -> init[0] += 1;
            case 'n' -> init[0] -= 1;
            default -> throw new IllegalStateException("Unexpected direction: " + direction);
        }
        return getCell(init[0], init[1]);
    }

    /**
     * Nearby entities list in a diamond shaped pattern.
     *
     * @param center the center
     * @param range  the range
     * @return the list
     */
    public List<Entity> nearbyEntities(GridCell center, int range) {
        ArrayList<Entity> entities = new ArrayList<>();
        int[] pos = getLocationCords(center);
        int centerX = pos[0];
        int centerY = pos[1];
        for (int y = centerY - range; y <= centerY + range; y++) {
            for (int x = centerX - range; x <= centerX + range; x++) {
                if (Math.abs(x - centerX) + Math.abs(y - centerY) <= range) {
                    if (isValidPosition(x, y) && getCell(x, y).hasContent()) {
                        entities.addAll(getCell(x, y).getContent());
                    }
                }
            }
        }
        entities.remove(center.getContent().get(0));
        return entities;
    }

    public boolean isValidAdjacent(PlayableCharacter p, char direction) {
        int pos[] = getLocationCords(p);
        switch (direction) {
            case 'e' -> pos[1] += 1;
            case 'w' -> pos[1] -= 1;
            case 's' -> pos[0] += 1;
            case 'n' -> pos[0] -= 1;
            default -> throw new IllegalStateException("Unexpected direction: " + direction);
        }
        return isValidPosition(pos[0], pos[1]);
    }
}
