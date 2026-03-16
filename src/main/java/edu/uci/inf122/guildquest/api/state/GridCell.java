package edu.uci.inf122.guildquest.api.state;

import edu.uci.inf122.guildquest.entities.Entity;
import java.util.List;
import java.util.ArrayList;

public class GridCell {
    // placeholder for now : Must be able to hold an Entity (player character, NPC,
    // enemy, etc.) or be empty
    private List<Entity> content;

    // Added constructor so every cell starts empty
    public GridCell() {
        this.content = new ArrayList<>();
    }

    public void removeContent() {
        if (content != null)
            content = null;
    }

    public boolean hasContent() {
        return content != null;
    }

    public List<Entity> getContent() {
        return content;
    }

    public void setContent(Entity newEntity) {
        content = new ArrayList<>();
        content.add(newEntity);
    }

    // Helper method for readability when checking the grid
    public boolean isEmpty() {
        return content == null || content.isEmpty();
    }
}