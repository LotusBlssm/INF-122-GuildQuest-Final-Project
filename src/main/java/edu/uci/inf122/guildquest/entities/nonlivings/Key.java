package edu.uci.inf122.guildquest.entities.nonlivings;

public class Key {
    int size;
    public Key(int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Key){
            return ((Key) obj).getSize()==size;
        } else return super.equals(obj);
    }
}
