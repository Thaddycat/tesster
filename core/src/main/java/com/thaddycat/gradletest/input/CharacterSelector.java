package com.thaddycat.gradletest.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import com.thaddycat.gradletest.CameraWrapper;
import com.thaddycat.gradletest.backend.GameCharacter;
import com.thaddycat.gradletest.CharacterManager;
import com.thaddycat.gradletest.backend.PCCharacter;

/**
 * Listens for left‐clicks on the world.
 * Only PCCharacter instances can be selected.
 */
public class CharacterSelector extends InputAdapter {
    private final int cellSize  ;
    private final CameraWrapper camera;     // to convert screen‐coords into world‐coords
    private final UIManager      ui;          // to update the UI
    private final CharacterManager model;      // to ask “who’s at this spot?”
    private final CommandMenuOpener menuOpener;


    public CharacterSelector(CameraWrapper camera,
                             CharacterManager model,
                             UIManager ui,
                             int cellSize,
                             CommandMenuOpener menuOpener)
    {
        this.camera = camera;
        this.model  = model;
        this.ui     = ui;
        this.cellSize = cellSize;
        this.menuOpener = menuOpener;
    }
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        menuOpener.closeMenu(); // first thing: hide any right-click menu
        if (button != Input.Buttons.LEFT) return false; // only care about left‐clicks
        Vector2 world = camera.unproject(screenX, screenY);
        // Now convert world coordinates to grid cell indices
        int cellX = (int) (world.x / cellSize);
        int cellY = (int) (world.y / cellSize);
        // 3) Dump everything so we can inspect the math

        Gdx.app.log("CharacterSelector",
            String.format("click screen=[%d,%d]  → world=[%.2f,%.2f]  → cell=[%d,%d]",
                screenX, screenY, world.x, world.y, cellX, cellY));

        // Look for a character at that grid cell
        GameCharacter clicked = model.getCharacterAt(new Vector2(cellX, cellY));

        // 4) Ask your model
        if (clicked == null) {
            Gdx.app.log("CharacterSelector", "  no character at that cell");
            model.clearSelection();
            ui.clearSelection();
            return false; //let next handler try
        }

        // 5) Only PCs
        if (!(clicked instanceof PCCharacter)) {
            Gdx.app.log("CharacterSelector", "  hit NPC “" + clicked.getName() + "”—ignoring");
            model.clearSelection();
            ui.clearSelection();
            return false; //let next handler try
        }

        Gdx.app.log("CharacterSelector", "  selecting PC “" + clicked.getName() + "”");
        model.selectCharacter(clicked);
        ui.setSelectedCharacter(clicked);
        return true;
    }
}
