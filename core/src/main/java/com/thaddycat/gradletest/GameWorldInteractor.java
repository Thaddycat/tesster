package com.thaddycat.gradletest;

import com.badlogic.gdx.math.Vector2;
import com.thaddycat.gradletest.backend.CharacterManager;
import com.thaddycat.gradletest.backend.Character;

//ask chat if we can make a better name
// Handles character/map interactions, delegated from InteractionManager

public class GameWorldInteractor {
    private CharacterManager characterManager;

    public GameWorldInteractor(CharacterManager characterManager) {
        this.characterManager = characterManager;
    }

    public void handleWorldClick(Vector2 worldCoords) {
        // Could be map or character
        Character clicked = characterManager.getCharacterAt(worldCoords);
        if (clicked != null) {
            characterManager.selectCharacter(clicked);
        } else {
            characterManager.clearSelection();
        }
    }
}
