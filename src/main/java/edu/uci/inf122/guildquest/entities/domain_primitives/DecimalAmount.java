package edu.uci.inf122.guildquest.entities.domain_primitives;

public class DecimalAmount {
    private final double count;
    public DecimalAmount(double c){
        if (c<0){
            throw new IllegalArgumentException("Cannot have negative amount");
        }
        count = c;
    }

    public double getCount() {
        return count;
    }
    public DecimalAmount multiply(int c){
        return new DecimalAmount(count*c);
    }

    public DecimalAmount subtract(int c){
        return new DecimalAmount(count-c);
    }

    public Amount toAmount(){
        return new Amount((int)count);
    }
}
