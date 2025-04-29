/*package com.thaddycat.gradletest.input;

import com.badlogic.gdx.InputAdapter;

import java.util.ArrayList;
import java.util.List;


public class InputRouter extends InputAdapter {
    private final List<InputHandler> handlers = new ArrayList<>();
    public void addHandler(InputHandler h) { handlers.add(h); }
    public void removeHandler(InputHandler h) { handlers.remove(h); }

    @Override
    public boolean touchDown(int x, int y, int ptr, int btn) {
        for (InputHandler h : handlers)
            if (h.touchDown(x,y,ptr,btn)) return true;
        return false;
    }

    @Override
    public boolean keyDown(int key) {
        for (InputHandler h : handlers)
            if (h.keyDown(key)) return true;
        return false;
    }
    @Override
    public boolean keyUp(int key) {
        for (InputHandler h : handlers)
            if (h.keyUp(key)) return true;
        return false;
    }
}
*/
