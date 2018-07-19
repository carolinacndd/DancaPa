package com.mygdx.game.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.awt.Image;

/**
 * Created by carolinacandido on 15/07/18.
 */

public class ButtonFactory {

    public static ImageButton createButton(Texture buttonTexture)
    {
        return new ImageButton(new TextureRegionDrawable(new TextureRegion(buttonTexture)));
    }

    public static ImageButton createButton(TextureRegion buttonTextureReg)
    {
        return new ImageButton(new TextureRegionDrawable(buttonTextureReg));
    }
}
