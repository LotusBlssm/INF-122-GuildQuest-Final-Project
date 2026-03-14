package edu.uci.inf122.guildquest.content;

import java.util.UUID;

public interface Realm {
    UUID getRealmID();
    String getName();
    String getDescription();
    TimeRule getTimeRule();
    int getGridWidth();
    int getGridHeight();
}
