package com.thaddycat.gradletest;

import com.badlogic.gdx.math.Vector2;
import com.thaddycat.gradletest.backend.GameCharacter;
import com.thaddycat.gradletest.backend.PCCharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterManager {
    private static CharacterManager instance;
    private final List<GameCharacter> characters;

    private CharacterManager() {
        characters = new ArrayList<>();
    }

    public static CharacterManager getInstance() {
        if (instance == null) {
            instance = new CharacterManager();
        }
        return instance;
    }

    public void addCharacter(GameCharacter character) {
        characters.add(character);
    }

    public void removeCharacter(GameCharacter character) {
        characters.remove(character);
    }

    public List<GameCharacter> getCharacterArrayList() {
        return Collections.unmodifiableList(characters);
    }

    public List<GameCharacter> getPCCharacters() {
        List<GameCharacter> pcs = new ArrayList<>();
        for (GameCharacter c : characters) {
            if (c instanceof PCCharacter) {
                pcs.add(c);
            }
        }
        return pcs;
    }

    public List<GameCharacter> getAliveCharacters() {
        List<GameCharacter> alive = new ArrayList<>();
        for (GameCharacter c : characters) {
            if (c.getResourcePoints().getHp() > 0) {
                alive.add(c);
            }
        }
        return alive;
    }

    public GameCharacter getCharacterByName(String name) {
        for (GameCharacter c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public GameCharacter getCharacterAt(Vector2 worldCoords) {
        for (GameCharacter c : characters) {
            if (c.getPosition().getX() == (int)worldCoords.x &&
                c.getPosition().getY() == (int)worldCoords.y) {
                return c;
            }
        }
        return null;
    }

    private GameCharacter selectedCharacter;

    public void selectCharacter(GameCharacter c) {
        this.selectedCharacter = c;
    }

    public void clearSelection() {
        this.selectedCharacter = null;
    }

    public GameCharacter getSelectedCharacter() {
        return selectedCharacter;
    }

    public void clearAll() {
        characters.clear();
    }
}
