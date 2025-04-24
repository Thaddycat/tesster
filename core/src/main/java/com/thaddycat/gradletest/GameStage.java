package com.thaddycat.gradletest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.List;
import java.util.ArrayList;

public class GameStage extends Stage {
    private List<ButtonInterface> uiButtons = new ArrayList<>();
    private CameraWrapper cameraWrapper;
    private MenuManager menuManager;
    private InteractionManager interactionManager;

    public GameStage(CameraWrapper cameraWrapper, MenuManager menuManager, InteractionManager interactionManager) {
        this.cameraWrapper = cameraWrapper;
        this.menuManager = menuManager;
        this.interactionManager = interactionManager;
    }

    public void addUIButton(ButtonInterface button) {
        uiButtons.add(button);
        // we assume your GameButton implements Actor or you’ll need to wrap it
        this.addActor((Actor) button);
    }

    /**
     * Call this from your input handler (e.g. on touchDown).
     * x,y are screen coordinates.
     */
    public void handleClick(float x, float y) {
        // if the click was on any UI button, bail out
        if (isUIClick(x, y)) return;

        // otherwise turn it into world coords and route to game logic
        int mx = Gdx.input.getX();
        int my = Gdx.input.getY();
        Vector2 world = cameraWrapper.unproject(mx, my);
        interactionManager.routeClick(world);
    }

    private boolean isUIClick(float x, float y) {
        for (ButtonInterface button : uiButtons) {
            if (((Actor) button).hit(x, y, true) != null) return true;
        }
        return false;
    }

    public void update(float delta) {
        this.act(delta);
        this.draw();
    }

}
