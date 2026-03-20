package edu.uci.inf122.guildquest.entities.domain_primitives;

public class Level {
    private int level;

    public Level(int level){
        if (level<=0) throw new IllegalArgumentException("Level cannot be 0 or below");
        this.level = level;
    }
    
    public int getLevelInt() {
        return level;
    }

}
