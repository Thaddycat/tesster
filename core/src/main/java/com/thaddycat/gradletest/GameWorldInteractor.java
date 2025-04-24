package com.thaddycat.gradletest;

import com.badlogic.gdx.math.Vector2;
import com.thaddycat.gradletest.backend.GameCharacter;
import com.thaddycat.gradletest.CharacterManager;

//ask chatGPT if we can make a better name
// Handles character/map interactions, delegated from InteractionManager

/** Handles world‐clicks (map or character selection). */
public class GameWorldInteractor {
    private final CharacterManager characterManager;


    public GameWorldInteractor(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    public void handleWorldClick(Vector2 worldCoords) {
        // Could be map or character
        GameCharacter clicked = characterManager.getCharacterAt(worldCoords);
        if (clicked != null) {
            characterManager.selectCharacter(clicked);
        } else {
            characterManager.clearSelection();
        }
    }
}
