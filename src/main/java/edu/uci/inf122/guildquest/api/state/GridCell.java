package edu.uci.inf122.guildquest.api.state;

import edu.uci.inf122.guildquest.entities.Entity;

public class GridCell {
    private Entity content;
    public void removeContent(){
        if (content!=null) content=null;
    }
    public boolean hasContent() {
        return content == null;
    }
    public Entity getContent(){
        return content;
    }
    public void setContent(Entity newEntity){
        content = newEntity;
    }
}
