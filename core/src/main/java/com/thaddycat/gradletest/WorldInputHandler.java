package com.thaddycat.gradletest;

public interface WorldInputHandler {
    boolean handleKey(int keycode);
    boolean handleTouch(int x, int y, int button);
}
