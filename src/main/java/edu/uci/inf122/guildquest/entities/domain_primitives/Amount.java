package edu.uci.inf122.guildquest.entities.domain_primitives;

public class Amount {
    private final int count;
    public Amount(int c){
        if (c<0){
            throw new IllegalArgumentException("Cannot have negative amount");
        }
        count = c;
    }

    public int getCount() {
        return count;
    }
}
