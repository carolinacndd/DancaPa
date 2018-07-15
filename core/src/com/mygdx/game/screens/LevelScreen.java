package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by carolinacandido on 15/07/18.
 */

public class LevelScreen implements Screen {


    //Asset Loader
    private final static String PATH= "levelscreen/";
    private static Texture levelbackgroundTexture = new Texture(PATH + "game-background.png");
    private static Texture movesTexture = new Texture(PATH + "moves-buttons.png");
    private static TextureRegion [] moves = new TextureRegion[8];

    private static float width = Gdx.graphics.getWidth();
    private static float height = Gdx.graphics.getHeight();

    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();
    private Game game;
    private Stage stage;


    public LevelScreen(Game game)
    {
        this.game = game;
        this.stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        initUI();
    }

    private void createMoves()
    {
        int step = 91; int x = 0; int y = 0;
        for(int i=0; i<moves.length; ++i){
            TextureRegion txtreg = new TextureRegion(movesTexture,
                    x, y, step, step);
            txtreg.flip(true, false);
            moves[i] = txtreg;
            x +=step;
        }
    }

    private void initUI()
    {
        createMoves();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
