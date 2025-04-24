package com.thaddycat.gradletest;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import com.thaddycat.gradletest.backend.PCCharacter;

import java.util.List;

public class CharacterSelector {
    private PCCharacter selectedCharacter;
    private List<PCCharacter> playerCharacters;

    public CharacterSelector(List<PCCharacter> playerCharacters) {
        this.playerCharacters = playerCharacters;
        this.selectedCharacter = null;
    }

    public void handleClick(int screenX, int screenY, int button, CameraWrapper camera) {
        if (button != Input.Buttons.LEFT) return; // Only respond to left-clicks

        Vector2 worldCoords = camera.unproject(screenX, screenY);

        for (PCCharacter character : playerCharacters) {
            Rectangle bounds = getCharacterBounds(character);
            if (bounds.contains(worldCoords)) {
                selectedCharacter = character;
                return;
            }
        }

        // If no character was clicked, deselect
        selectedCharacter = null;
    }



    private Rectangle getCharacterBounds(PCCharacter character) {
        int cellSize = 32; // or whatever your cell size is
        int padding = 4;
        float x = character.getPosition().getX() * cellSize + padding;
        float y = character.getPosition().getY() * cellSize + padding;
        float size = cellSize - 2 * padding;
        return new Rectangle(x, y, size, size);
    }

    public PCCharacter getSelectedCharacter() {
        return selectedCharacter;
    }

    public boolean hasSelectedCharacter() {
        return selectedCharacter != null;
    }

    public void deselect() {
        selectedCharacter = null;
    }
}
