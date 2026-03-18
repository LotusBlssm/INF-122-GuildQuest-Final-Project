package edu.uci.inf122.guildquest.ui.playablecharacteruis;

import edu.uci.inf122.guildquest.entities.playablecharacters.PlayableCharacter;

public abstract class PlayableCharacterUI {
    private PlayableCharacter character;

    public PlayableCharacterUI(PlayableCharacter character) {
        this.character = character;
    }

    public abstract void display();
}