package com.thaddycat.gradletest;

// Centralizes routing of clicks

import com.badlogic.gdx.math.Vector2;

public class InteractionManager {
    private GameWorldInteractor gameWorldInteractor;

    public InteractionManager(GameWorldInteractor gameWorldInteractor) {
        this.gameWorldInteractor = gameWorldInteractor;
    }

    public void routeClick(Vector2 worldCoords) {
        gameWorldInteractor.handleWorldClick(worldCoords);
    }
}
