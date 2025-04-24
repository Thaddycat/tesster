package com.thaddycat.gradletest;

// Basic abstract class for UI windows

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class AbstractUIWindow extends Group {
    public abstract void setVisible(boolean visible);
}
