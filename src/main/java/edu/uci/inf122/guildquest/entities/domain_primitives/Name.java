package edu.uci.inf122.guildquest.entities.domain_primitives;

import java.util.regex.Pattern;

/**
 * The type Name.
 */
public class Name {
    /**
     * The constant pattern defining what a valid name is.
     */
    public final static Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");

    private final String name;

    /**
     * Instantiates a new Name.
     *
     * @param name the name
     */
    public Name(String name){
        if (name==null) throw new IllegalArgumentException("No null name");
        if (name.isBlank()){
            throw new IllegalArgumentException("name must not be blank");
        } if (! pattern.matcher(name).find()){ // checks to make sure is valid name
            throw new IllegalArgumentException("name must have only letters and numbers.");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
