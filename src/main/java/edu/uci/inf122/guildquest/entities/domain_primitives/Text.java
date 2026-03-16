package edu.uci.inf122.guildquest.entities.domain_primitives;

public class Text {
    private final String text;
    public Text(String text){
        if (text==null) throw new IllegalArgumentException("text cannot be null");
        this.text = text;
    }
}
