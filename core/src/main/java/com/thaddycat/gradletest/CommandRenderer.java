package com.thaddycat.gradletest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thaddycat.gradletest.backend.*;


import java.util.List;

public class CommandRenderer {
    private final SpriteBatch batch;
    private Texture dropTex, swordBlue, swordPink;
    private final int cellSize;

    public CommandRenderer(SpriteBatch batch,
                           Texture dropTex, Texture swordBlue, Texture swordPink,
                           int cellSize) {
        this.batch      = batch;
        this.dropTex    = dropTex;
        this.swordBlue  = swordBlue;
        this.swordPink  = swordPink;
        this.cellSize   = cellSize;
    }

    public void render(OrthographicCamera cam, List<Command> queue) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        for (Command cmd : queue) {
            if (cmd instanceof MoveCommand) {
                MoveCommand m = (MoveCommand)cmd;
                Cell t = m.getTarget();
                if (t != null) {
                    GameCharacter c = m.getCharacter();
                    batch.setColor(c.getName().contains("Jane")?1:0.3f,0,0,1);
                    batch.draw(dropTex, t.getXPos()*cellSize, t.getYPos()*cellSize, cellSize, cellSize);
                }
            } else if (cmd instanceof AttackCommand) {
                AttackCommand a = (AttackCommand)cmd;
                GameCharacter c = a.getAttacker();
                Cell t = a.getTarget().getPosition()!=null? MapGenerator.getCellAt(a.getTarget().getPosition().getX(), a.getTarget().getPosition().getY()):null;
                if (t!=null) {
                    batch.setColor(Color.WHITE);
                    batch.draw(c.getName().contains("John")?swordBlue:swordPink,
                        t.getXPos()*cellSize, t.getYPos()*cellSize, cellSize, cellSize);
                }
            }
        }
        batch.setColor(Color.WHITE);
        batch.end();
    }

    public void dispose() {
        dropTex.dispose();
        swordBlue.dispose();
        swordPink.dispose();
    }
}

