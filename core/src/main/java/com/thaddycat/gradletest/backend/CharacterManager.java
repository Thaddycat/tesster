package com.thaddycat.gradletest.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import com.badlogic.gdx.math.Vector2;




public class CharacterManager {
    private static CharacterManager instance;
    private final List<Character> characters;

    private CharacterManager() {
        characters = new ArrayList<>();
    }

    public static CharacterManager getInstance() {
        if (instance == null) {
            instance = new CharacterManager();
        }
        return instance;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public List<Character> getCharacterArrayList() {
        return Collections.unmodifiableList(characters);
    }

    public List<Character> getPCCharacters() {
        List<Character> pcs = new ArrayList<>();
        for (Character c : characters) {
            if (c instanceof PCCharacter) {
                pcs.add(c);
            }
        }
        return pcs;
    }

    public List<Character> getAliveCharacters() {
        List<Character> alive = new ArrayList<>();
        for (Character c : characters) {
            if (c.getResourcePoints().getHp() > 0) {
                alive.add(c);
            }
        }
        return alive;
    }

    public Character getCharacterByName(String name) {
        for (Character c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public Character getCharacterAt(Vector2 worldCoords) {
        for (Character c : characters) {
            if (c.getPosition().getX() == (int)worldCoords.x &&
                c.getPosition().getY() == (int)worldCoords.y) {
                return c;
            }
        }
        return null;
    }

    private Character selectedCharacter;

    public void selectCharacter(Character c) {
        this.selectedCharacter = c;
    }

    public void clearSelection() {
        this.selectedCharacter = null;
    }

    public Character getSelectedCharacter() {
        return selectedCharacter;
    }

    public void clearAll() {
        characters.clear();
    }
}
