package com.thaddycat.gradletest;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.thaddycat.gradletest.backend.GameCharacter;
import com.thaddycat.gradletest.backend.PCCharacter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CameraWrapper {
    private OrthographicCamera camera;

    public CameraWrapper(OrthographicCamera camera) {
        this.camera = camera;
    }

    public Vector2 unproject(int screenX, int screenY) {
        Vector3 worldCoords = camera.unproject(new Vector3(screenX, screenY, 0));
        return new Vector2(worldCoords.x, worldCoords.y);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void update() {
        camera.update();
    }
}
