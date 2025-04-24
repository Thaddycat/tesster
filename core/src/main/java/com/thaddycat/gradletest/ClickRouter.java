package com.thaddycat.gradletest;


// For deciding if a click was on UI, map, or character

public class ClickRouter {
    private GameStage gameStage;

    public ClickRouter(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    public boolean isUIClick(float x, float y) {
        return gameStage.getViewport().getCamera().viewportWidth < x;
    }
}
