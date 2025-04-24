package com.thaddycat.gradletest;

public interface UIInputHandler {
    boolean handleKey(int keycode);
    boolean handleTouch(int x, int y, int button);
}
