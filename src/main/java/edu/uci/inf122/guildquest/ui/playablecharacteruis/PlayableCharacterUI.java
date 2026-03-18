package edu.uci.inf122.guildquest.ui;

import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

public class PlayableCharacterUI {
    private PlayableCharacter character;

    public PlayableCharacterUI(PlayableCharacter character) {
        this.character = character;
    }

    public abstract void display();
}