package com.thaddycat.gradletest;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.thaddycat.gradletest.backend.Cell;
import com.thaddycat.gradletest.backend.GameCharacter;
import com.thaddycat.gradletest.backend.CharacterGenerator;
import com.thaddycat.gradletest.backend.MapGenerator;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thaddycat.gradletest.input.*;

import java.util.List;

public class GameScreen implements Screen {
    private static final int CELL_SIZE = 40;

    private final OrthographicCamera  camera;
    private final SpriteBatch         batch;
    private final MapRenderer         mapRenderer;
    private final CharacterRenderer   charRenderer;
    private final CommandRenderer     cmdRenderer;

    private GameStage gameStage;
    private UIManager uiManager;

    public GameScreen() {
        // — camera & wrappers
        camera = new OrthographicCamera(800, 500);
        camera.position.set(400, 250, 0);
        camera.update();
        CameraWrapper cameraWrapper = new CameraWrapper(camera);
        batch   = new SpriteBatch();
        // - skin,
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        // - menuManager
        MenuManager menuManager = new MenuManager();
        // — Load & register characters and map
        MapGenerator.generateCellArray(5, 5); // sets up internal state
        CharacterGenerator.loadCharacters();
        List<Cell> cells = MapGenerator.getCellArray();        // now grab the list
        List<GameCharacter> characters = CharacterManager.getInstance().getCharacterArrayList();
        // -- render map
        mapRenderer  = new MapRenderer(cells, CELL_SIZE);
        // --render planned commands

        Texture moveIcon = new Texture("drop.png");
        Texture attackIcon = new Texture("sword_blue.png");
        cmdRenderer  = new CommandRenderer(batch,
            moveIcon,
            attackIcon,
            CELL_SIZE);
        // - render sprites
        charRenderer = new CharacterRenderer(batch, characters, CELL_SIZE, new DefaultSpriteProvider());
        // build each input handler
        // 1) Stage/UI
        this.gameStage = new GameStage(cameraWrapper, menuManager, skin);
        // 2) world‐click interactor
        CommandMenuOpener commandMenu = new CommandMenuOpener(cameraWrapper,CharacterManager.getInstance(),uiManager, gameStage, skin, CELL_SIZE);
        // 3) UI facade
        this.uiManager = new UIManager(gameStage, skin);
        // 4) character selector
        CharacterSelector selector  = new CharacterSelector(cameraWrapper,
            CharacterManager.getInstance(),
            uiManager, CELL_SIZE, commandMenu);
        // 5) global keys handler
        GlobalKeyHandler keys      = new GlobalKeyHandler();
        // build the input multiplexer and register in desired order:
        InputMuxBuilder builder = new InputMuxBuilder();
        // 1 + 2 → UI layer               (Stage + raw UI)
        builder.addUIProcessor(gameStage);
        builder.addUIProcessor(uiManager);      // optional, only if UIManager implements InputProcessor
        //  gameplay interactions  (selection + commands)
        // 3) selecting PCs
        builder.addGameplayProcessor(selector);
        // 4) moves & attacks
        builder.addGameplayProcessor(commandMenu);
        // 5 → global keys & fallback
        builder.addGlobalProcessor(keys);
        // e) Finally catch-all fallback handler, if you have one
        //  builder.addGlobalProcessor(otherHandler);
        Gdx.input.setInputProcessor(builder.build());
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f,0.1f,0.3f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mapRenderer.render(camera);
        charRenderer.render(camera);
        cmdRenderer.render(camera, GameController.INSTANCE.getQueue());
        gameStage.act(delta);
        gameStage.draw();
    }
    @Override
    public void show() {
        // Prepare your screen here.
    }
    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }
    @Override
    public void pause() {
        // Invoked when your application is paused.
    }
    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }
    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }
    @Override
    public void dispose() {
        batch.dispose();
        cmdRenderer.dispose();
        mapRenderer.dispose();
    }
}
