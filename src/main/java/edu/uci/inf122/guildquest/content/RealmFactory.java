package edu.uci.inf122.guildquest.content;

import java.util.UUID;

public class RealmFactory {

    public static Realm createRealm(String name, String description, TimeRule timeRule, int gridWidth, int gridHeight) {
        return new BasicRealm(UUID.randomUUID(), name, description, timeRule, gridWidth, gridHeight);
    }
}
