package edu.uci.inf122.guildquest.api.state;

import edu.uci.inf122.guildquest.entities.Entity;

public class GridCell {
    private Entity content;

    // Added constructor so every cell starts empty
    public GridCell() {
        this.content = null;
    }

    public void removeContent(){
        if (content!=null) content=null;
    }

    // FIXED: this was previously backwards
    public boolean hasContent() {
        return content != null;
    }

    public Entity getContent(){
        return content;
    }

    public void setContent(Entity newEntity){
        content = newEntity;
    }

    // Helper method for readability when checking the grid
    public boolean isEmpty() {
        return content == null;
    }
}