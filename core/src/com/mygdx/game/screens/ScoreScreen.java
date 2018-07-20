package com.mygdx.game.screens;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.helpers.ButtonFactory;
import com.mygdx.game.helpers.Pair;
import com.mygdx.game.helpers.Position;
import com.mygdx.game.model.Move;
import com.mygdx.game.model.Timer;

import java.util.ArrayList;
import java.util.List;

public class ScoreScreen implements Screen {

    private final int ROWS = 3;
    private final int COLUMNS = 3;
    private final int STEP = 91;
    private final int MOVEHEIGHT = 84;
    private final int NRMOVES = 21;

    //Asset Loader
    private final static String LEVELPATH = "levelscreen/";
    private final static String SCOREPATH = "scorescreen/";
    private static Texture levelbackgroundTexture = new Texture(LEVELPATH + "background_middle.png");
    private static Texture movesButtonsTexture = new Texture(LEVELPATH + "moves-buttons.png");
    private static TextureRegion[] movesButtons = new TextureRegion[8];
    private static Texture movesTexture = new Texture(LEVELPATH + "moves-border.png");
    private static TextureRegion [] moves = new TextureRegion[8];
    private static Texture scoreBoardTexture = new Texture(SCOREPATH + "scoreboard.png");
    private static Music music1 = Gdx.audio.newMusic(Gdx.files.getFileHandle("levelscreen/sound/girlslikeyou.m4a", Files.FileType.Internal));

    private List<Pair<TextureRegion, Position>> choreography = new ArrayList<>();
    private List<Pair<Move, Timer>> choreographyOnSegs = new ArrayList<>();

    private static float width = Gdx.graphics.getWidth();
    private static float height = Gdx.graphics.getHeight();
    private static float MoveOffset = 2f;

    private SpriteBatch batch = new SpriteBatch();
    private BitmapFont font = new BitmapFont();
    private Game game;
    private int nrMoves, score;
    private Stage stage;
    private static float FRAME_DURATION = 1f;
    private float elapsed_time = 0f;
    private float origin_x, origin_y =  (Gdx.graphics.getHeight() - MOVEHEIGHT) / 2;
    private int timerSeg = 0;
    private boolean start = false;


    //Dimensions

    //moveTexture
    private float moveWidth = width/4;
    private float moveHeight = moveWidth * (float) MOVEHEIGHT/STEP;

    private float offSetX = height/10;
    private float offSetY = height/10;

    private float boardWidth = width*2/3;
    private float boardHeight = boardWidth * (float) scoreBoardTexture.getHeight()/(float) scoreBoardTexture.getWidth();


    public ScoreScreen(Game game, int nrMoves, int score)
    {
        this.game = game;
        this.stage = new Stage(new ScreenViewport(), batch);
        this.nrMoves = nrMoves;
        this.score = score;
        Gdx.input.setInputProcessor(stage);
        initUI();

    }

    private void createMoves()
    {
        int x = 0; int y = 0;
        for(int i = 0; i< movesButtons.length; ++i){
            TextureRegion txtreg = new TextureRegion(movesButtonsTexture,
                    x, y, STEP, STEP);
            txtreg.flip(true, false);
            TextureRegion txmtreg = new TextureRegion(movesTexture,
                    x, y, STEP, STEP);
            txtreg.flip(true, false);
            movesButtons[i] = txtreg;
            moves[i] = txmtreg;
            x +=STEP;

        }
        System.out.println(movesButtons.length + "Moves");
        System.out.println("Move height: "+ movesButtons[0].getTexture().getHeight() + "Move widht: " + movesButtons[0].getTexture().getWidth());
    }

    private void initUI()
    {

        createMoves();
        float buttonMoveWidth = width/5f;
        float buttonMoveHeight = buttonMoveWidth * movesButtons[0].getRegionHeight()/movesButtons[0].getRegionWidth();
        final float movePosX = width/2 - buttonMoveWidth-buttonMoveWidth/2;
        final float movePosY = 0;

        float auxX2 = movePosX;
        float auxY2 = movePosY;
        int movesIdx=0;
        for(int c=0; c<COLUMNS; c++)
        {
            for(int r=0; r<ROWS; r++)
            {
                if(c==r && c!=0 && c!=COLUMNS-1)
                {
                    auxX2+=buttonMoveWidth;
                    continue;
                }
                if(movesIdx>= movesButtons.length)
                {
                    break;
                }
                ImageButton moveButton = ButtonFactory.createButton(movesButtons[movesIdx]);
                final int m  = movesIdx;
                moveButton.setSize(buttonMoveWidth, buttonMoveHeight);
                moveButton.getImageCell().size(buttonMoveWidth,buttonMoveHeight);
                moveButton.setPosition(auxX2, auxY2);
                moveButton.addListener(new InputListener(){
                    @Override
                    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                    }
                    @Override
                    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                        Gdx.input.vibrate(100);
                        return true;
                    }
                });
                stage.addActor(moveButton);
                movesIdx+=1;
                auxX2+=buttonMoveWidth;
            }
            auxY2+=buttonMoveHeight;
            auxX2=movePosX;
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

        elapsed_time +=  Gdx.graphics.getDeltaTime();

        batch.begin();
        font.getData().setScale(3f);
        batch.draw(levelbackgroundTexture, 0f, 0f, width, height);
        font.draw(batch, "Score:" + String.valueOf(score), width/20, height-2f);

        font.draw(batch, "Timer:" + String.valueOf(timerSeg), width/2, height-2f);


        /*for(int i=0; i<NRMOVES; i++)
        {
            Pair<TextureRegion, Position> moveExec = choreography.get(i);
            Pair<Move, Timer> moveTime = choreographyOnSegs.get(i);
            float auxX = moveExec.snd.getX();
            batch.draw(moveExec.fst, auxX, moveExec.snd.getY(), moveWidth, moveHeight);
            if(i==NRMOVES-1 && auxX+moveWidth<width/2)
                game.setScreen(new ScoreScreen());


            //batch.draw(moveExec.fst.getTexture(), moveExec.snd.getX(), origin_y, moveWidth, moveHeight);

            moveExec.snd.setX(auxX - MoveOffset);
        }*/

        batch.end();

        stage.act(Gdx.graphics.getRawDeltaTime());
        stage.draw();

        batch.begin();
        batch.draw(scoreBoardTexture, width/2-boardWidth/2, height/2-boardHeight/2);
        font.draw(batch, "Score: " + score + "/" + nrMoves*100, width/2-boardWidth/2, height/2+boardHeight);
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

    private float getX(int seg)
    {
        if(seg == 0)
            return  width/2 - moveWidth/2;
        return width/2 + width/2 * seg - moveWidth/2;
    }
}

