package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.helpers.ButtonFactory;

/**
 * Created by carolinacandido on 15/07/18.
 */

public class MenuScreen implements Screen {

    //Asset Loader
    private final static String PATH= "mainscreen/";
    private static Texture logo = new Texture("logo.png");
    private static Texture infobutton = new Texture(PATH + "info-icon.png");
    private static Texture mainbackground = new Texture(PATH + "mainscreen-background.png");
    private static Texture mainstartbutton = new Texture(PATH + "mainscreen-start.png");

    private static float width = Gdx.graphics.getWidth();
    private static float height = Gdx.graphics.getHeight();

    private SpriteBatch batch = new SpriteBatch();
    private Game game;
    private Stage stage;


    //Dimensions

    //logo
    private float logoWidth = width*3/4;
    private float logoHeight = logoWidth * (float)logo.getHeight()/(float)logo.getWidth();
    private float logoPosX = width/2 - logoWidth/2;
    private float logoPosY = height - height/5 - logoHeight/2;

    //info button
    private float infoButtonWidth = width/10;
    private float infoButtonHeight = infoButtonWidth * (float)infobutton.getHeight()/(float)infobutton.getWidth();
    private float infoButtonPosX = width - infoButtonWidth;
    private float infoButtonPosY = height - infoButtonWidth;

    //start button
    private float startButtonWidth = width*2/3;
    private float startButtonHeight = startButtonWidth * (float)mainstartbutton.getHeight()/(float)mainstartbutton.getWidth();
    private float startButtonPosX = width/2 - startButtonWidth/2;
    private float startButtonPosY = height*1/3 - startButtonHeight/3;


    //private float logoWidth = ;
    //private float logoHeight = ;

    public MenuScreen(Game game)
    {
        this.game = game;
        this.stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        initUI();
    }

    private void initUI()
    {
        System.out.println(startButtonPosX +"-"+ startButtonPosY);

        //info button
        ImageButton infoButton = ButtonFactory.createButton(infobutton);
        infoButton.setSize(infoButtonWidth, infoButtonHeight);
        infoButton.setPosition(infoButtonPosX, infoButtonPosY);
        infoButton.addListener((c)->{
            Gdx.input.vibrate(100);
            game.setScreen(new SelectLevelScreen(game));
            return true;
        });
        stage.addActor(infoButton);

        //start button
        ImageButton startButton = ButtonFactory.createButton(mainstartbutton);
        startButton.setSize(startButtonWidth, startButtonHeight);
        startButton.setPosition(startButtonPosX, startButtonPosY);
        startButton.addListener((c)->{
            Gdx.input.vibrate(100);
            game.setScreen(new SelectLevelScreen(game));
            return true;
        });
        stage.addActor(startButton);
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
        batch.draw(mainbackground, 0f, 0f, width, height);
        batch.draw(logo, logoPosX, logoPosY, logoWidth, logoHeight);

        batch.end();
        stage.act(Gdx.graphics.getRawDeltaTime());
        stage.draw();
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
        stage.dispose();
        batch.dispose();
    }
}
