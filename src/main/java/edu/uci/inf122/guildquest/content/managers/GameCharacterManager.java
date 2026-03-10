package edu.uci.inf122.guildquest.content.managers;

import java.util.List;
import java.util.ArrayList;
import edu.uci.inf122.guildquest.content.GameCharacter;
import edu.uci.inf122.guildquest.content.User;

import java.util.UUID;

public class GameCharacterManager {
    private UUID userID;
    private List<GameCharacter> characters;

    // no singleton
    public GameCharacterManager(UUID id) {
        userID = id;
        characters = new ArrayList<>();
    }

    // add new user
    public boolean addChar(GameCharacter newChar) {
        if (characters.contains(newChar)) {
            return false;
        }
        characters.add(newChar);
        return true;
    }

    // remove user
    public boolean removeChar(String name) {
        for (GameCharacter character : characters) {
            if (character.checkName(name)) {
                characters.remove(character);
                return true;
            }
        }
        return false;
    }

    // update user name
    public boolean updateCharName(String oldName, String newName) {
        for (GameCharacter user : characters) {
            if (user.checkName(oldName)) {
                user.setName(newName);
                return true;
            }
        }
        return false;
    }
}
