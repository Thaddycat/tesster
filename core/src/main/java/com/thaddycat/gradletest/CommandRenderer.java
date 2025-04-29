package com.thaddycat.gradletest;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.thaddycat.gradletest.backend.AttackCommand;
import com.thaddycat.gradletest.backend.Command;
import com.thaddycat.gradletest.backend.MoveCommand;
import com.thaddycat.gradletest.backend.Cell;
import com.thaddycat.gradletest.backend.GameCharacter;
import com.thaddycat.gradletest.backend.MapGenerator;

import java.util.List;

public class CommandRenderer {
    private final SpriteBatch batch;
    private final Texture moveIcon;
    private final Texture attackIcon;
    private final int cellSize;

    public CommandRenderer(SpriteBatch batch,
                           Texture moveIcon,
                           Texture attackIcon,
                           int cellSize) {
        this.batch       = batch;
        this.moveIcon    = moveIcon;
        this.attackIcon  = attackIcon;
        this.cellSize    = cellSize;
    }

    public void render(OrthographicCamera cam, List<Command> queue) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        for (Command cmd : queue) {
            if (cmd instanceof MoveCommand) {
                MoveCommand m = (MoveCommand) cmd;
                Cell targetCell = m.getTarget();
                if (targetCell != null) {
                    batch.draw(
                        moveIcon,
                        targetCell.getXPos() * cellSize,
                        targetCell.getYPos() * cellSize,
                        cellSize, cellSize
                    );
                }
            } else if (cmd instanceof AttackCommand) {
                AttackCommand a = (AttackCommand) cmd;
                GameCharacter defender = a.getTarget();
                if (defender != null) {
                    Cell cell = MapGenerator.getCellAt(
                        defender.getPosition().getX(),
                        defender.getPosition().getY()
                    );
                    if (cell != null) {
                        batch.draw(
                            attackIcon,
                            cell.getXPos() * cellSize,
                            cell.getYPos() * cellSize,
                            cellSize, cellSize
                        );
                    }
                }
            }
        }
        batch.end();
    }

    public void dispose() {
        moveIcon.dispose();
        attackIcon.dispose();
    }
}

