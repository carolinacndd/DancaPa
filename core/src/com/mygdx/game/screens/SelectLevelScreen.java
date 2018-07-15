package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.helpers.ButtonFactory;

/**
 * Created by carolinacandido on 15/07/18.
 */

public class SelectLevelScreen implements Screen {


    //Asset Loader
    private final static String PATH= "levelscreen/";
    private static Texture levelbackgroundTexture = new Texture(PATH + "levelscreen.png");
    private static Texture choosesongTexture = new Texture(PATH + "musicscreen-choose-white.png");
    private static Texture logoTexture = new Texture("logo.png");
    private static Texture chooserTexture = new Texture(PATH + "musicscreen-song-bg.png");
    private static Texture speakerTexture =  new Texture(PATH + "speaker-icon.png");
    private static Texture star1Texture =  new Texture(PATH + "star-1.png");
    private static Texture star2Texture =  new Texture(PATH + "star-2.png");
    private static Texture star3Texture =  new Texture(PATH + "star-3.png");


    private static float width = Gdx.graphics.getWidth();
    private static float height = Gdx.graphics.getHeight();

    //Dimensions

    //logoTexture
    private float logoWidth = width*3/4;
    private float logoHeight = logoWidth * (float) logoTexture.getHeight()/(float) logoTexture.getWidth();
    private float logoPosX = width/2 - logoWidth/2;
    private float logoPosY = height - height/8 - logoHeight/2;

    //Choose song title
    private float chooseTitleWidth = width*3/4;
    private float chooseTitleHeight = chooseTitleWidth * (float) choosesongTexture.getHeight()/(float) choosesongTexture.getWidth();
    private float chooseTitlePosX = width/2 - chooseTitleWidth /2;
    private float chooseTitlePosY = logoPosY - chooseTitleHeight /2;

    //Chooser
    private float chooserWidth = width*.90f;
    private float chooserHeight = chooserWidth * (float) chooserTexture.getHeight()/(float) chooserTexture.getWidth();
    private float chooserPosX = width/2 - chooserWidth /2;
    private float chooserPosY = chooseTitlePosY - height/8;
    private float chooserOffset = height/10;



    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();
    private Game game;
    private Stage stage;

    public SelectLevelScreen(Game game)
    {
        this.game = game;
        this.stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        initUI();
    }

    private void initUI()
    {
        float auxPosY = chooserPosY;
        for(int i=0; i<3; i++)
        {
            ImageButton infoButton = ButtonFactory.createButton(chooserTexture);
            infoButton.setSize(chooserWidth, chooserHeight);
            infoButton.setPosition(chooserPosX, auxPosY);
            infoButton.addListener((c)->{
                Gdx.input.vibrate(100);
                game.setScreen(new SelectLevelScreen(game));
                return true;
            });
            stage.addActor(infoButton);
            auxPosY -= chooserOffset;
        }

    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(levelbackgroundTexture, 0f, 0f, width, height);
        batch.draw(logoTexture, logoPosX, logoPosY, logoWidth, logoHeight);
        batch.draw(choosesongTexture, chooseTitlePosX, chooseTitlePosY, chooseTitleWidth, chooseTitleHeight);
        batch.end();

        stage.act(Gdx.graphics.getRawDeltaTime());
        stage.draw();

        renderChooserContent();
    }

    private void renderChooserContent()
    {
        batch.begin();

        for(int i=0; i<3; i++)
        {

        }
        font.
        font.draw(batch, "Hello World", 100, 100);

        batch.end();
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
