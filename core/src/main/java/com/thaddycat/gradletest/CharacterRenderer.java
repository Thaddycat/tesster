package com.thaddycat.gradletest;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thaddycat.gradletest.backend.Cell;
import com.thaddycat.gradletest.backend.GameCharacter;
import com.thaddycat.gradletest.backend.MapGenerator;
import com.thaddycat.gradletest.backend.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterRenderer {
    private final SpriteBatch batch;
    private final List<GameCharacter> characters;
    private final int cellSize;
    private final SpriteProvider spriteInterface;
    private final Map<String, Texture> textures = new HashMap<>();

    public CharacterRenderer(SpriteBatch batch,
                             List<GameCharacter> characters,
                             int cellSize,
                             SpriteProvider spriteInterface) {
        this.batch = batch;
        this.characters = characters;
        this.cellSize = cellSize;
        this.spriteInterface = spriteInterface;
    }

    public void render(OrthographicCamera cam) {
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        for (GameCharacter c : characters) {
            String path = spriteInterface.getSpritePath(c);
            Texture tex = textures.computeIfAbsent(path, Texture::new);
            Position p = c.getPosition();
            Cell cell = MapGenerator.getCellAt(p.getX(), p.getY());
            batch.draw(c.getTexture(), cell.getXPos()*cellSize, cell.getYPos()*cellSize, cellSize, cellSize);
        }
        batch.end();
    }
}
