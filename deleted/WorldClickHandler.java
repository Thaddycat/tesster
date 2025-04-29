/*package com.thaddycat.gradletest.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.thaddycat.gradletest.CameraWrapper;


// Handles left-clicks on the world (is a character is not selected).
//Converts screen coordinates to world coordinates and tells the GameWorldInteractor to act accordingly.
public class WorldClickHandler extends InputAdapter {
    private final CameraWrapper cameraWrapper;
    private final CommandProcessor commandProcessor;

    public WorldClickHandler(CameraWrapper cameraWrapper, CommandProcessor worldInteractor) {
        this.cameraWrapper = cameraWrapper;
        this.commandProcessor = worldInteractor;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT) return false;

        Vector2 worldCoords = cameraWrapper.unproject(screenX, screenY);
        commandProcessor.handleWorldClick(worldCoords);
        return true;   //event consumed
    }
}
*/
