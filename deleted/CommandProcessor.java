/*package com.thaddycat.gradletest.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import com.thaddycat.gradletest.CameraWrapper;
import com.thaddycat.gradletest.GameController;
import com.thaddycat.gradletest.backend.*;
import com.thaddycat.gradletest.CharacterManager;
import com.thaddycat.gradletest.GameController;
import com.thaddycat.gradletest.backend.AttackCommand;
import com.thaddycat.gradletest.backend.GameCharacter;
import com.thaddycat.gradletest.backend.MapGenerator;
import com.thaddycat.gradletest.backend.MoveCommand;
import com.thaddycat.gradletest.backend.PCCharacter;
import com.thaddycat.gradletest.backend.NPCCharacter;
import com.thaddycat.gradletest.backend.Cell;


//Right-click in world to enqueue Move/Attack for the currently selected character.

public class CommandProcessor extends InputAdapter {
    private final CharacterManager characterManager;
    private final CameraWrapper camera;



    public CommandProcessor(CameraWrapper camera,
                            CharacterManager characterManager) {
        this.camera = camera;
        this.characterManager = characterManager;

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.RIGHT) return false;

        Vector2 world = camera.unproject(screenX, screenY);
        int cellX = (int) (world.x / 40); //40= cell size
        int cellY = (int) (world.y / 40); //40 = cell size

        Cell nextCell = MapGenerator.getCellAt(cellX, cellY);
        if (nextCell == null) {
            Gdx.app.error("CmdProc", "click off‐map at ["+cellX+","+cellY+"]");
            return false;
        }

        GameCharacter sel = characterManager.getSelectedCharacter();
        if (sel == null) return false;          // nothing selected → ignore

        GameCharacter target = characterManager.getCharacterAt(world);

        if (sel != null) {
            Command cmd;
            // enqueue Attack Command
            if (target != null && target != sel) {
                cmd = new AttackCommand(sel, target);
            } else {
                // enqueue move Command
                cmd = new MoveCommand(sel, nextCell);
            }
            GameController.INSTANCE.enqueueCommand(cmd);
            return true;
        }
        return false;
    }
}
*/
