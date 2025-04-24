package com.thaddycat.gradletest;

// Handles UIWindow instances, auto-hides on show

import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private List<UIWindow> windows = new ArrayList<>();

    public void registerWindow(UIWindow window) {
        windows.add(window);
    }

    public void showOnly(UIWindow targetWindow) {
        for (UIWindow window : windows) {
            if (window == targetWindow) {
                window.setVisible(true);
            } else {
                window.setVisible(false);
            }
        }
    }

    public void hideAll() {
        for (UIWindow window : windows) {
            window.setVisible(false);
        }
    }
}
