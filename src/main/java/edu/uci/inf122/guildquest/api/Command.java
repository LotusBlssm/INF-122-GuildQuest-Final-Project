package edu.uci.inf122.guildquest.api;

public interface Command {
    int getPlayerIndex();
    CommandType getCommandType();
}