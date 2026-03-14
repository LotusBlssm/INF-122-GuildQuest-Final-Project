package edu.uci.inf122.guildquest.content;

import java.util.UUID;

public class UserFactory {

    public static User createUser(String name) {
        return new User(UUID.randomUUID(), name, null);
    }

    public static User createUser(String name, UUID realmID) {
        return new User(UUID.randomUUID(), name, realmID);
    }
}
