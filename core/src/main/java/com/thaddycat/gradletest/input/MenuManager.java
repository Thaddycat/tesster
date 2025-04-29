package com.thaddycat.gradletest.input;

// Handles UIWindow instances, auto-hides on show

import com.thaddycat.gradletest.UIWindow;

import java.util.ArrayList;
import java.util.List;

public class MenuManager {
    private UIWindow current;
    private List<UIWindow> windows = new ArrayList<>();

    public void registerWindow(UIWindow window) {
        windows.add(window);
    }

    public void showMenu(UIWindow menu) {
        if (current != null) current.hide();
        current = menu;
        current.show();
    }
    public void closeMenu() {
        if (current != null) current.hide();
        current = null;
    }
}

