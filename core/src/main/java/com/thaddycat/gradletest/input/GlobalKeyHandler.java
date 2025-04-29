package com.thaddycat.gradletest.input;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input;
import com.thaddycat.gradletest.GameController;

/**
 * Handles “forward” and “undo” via keyboard.
 */
public class GlobalKeyHandler extends InputAdapter {
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:
                GameController.INSTANCE.step();
                return true;
            case Input.Keys.BACKSPACE:
                GameController.INSTANCE.undo();
                return true;
            default:
                return false;
        }
    }
}
