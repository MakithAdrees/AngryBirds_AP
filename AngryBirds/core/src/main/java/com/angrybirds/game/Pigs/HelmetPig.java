package com.angrybirds.game.Pigs;

import com.badlogic.gdx.graphics.Texture;

public class HelmetPig extends Pig {

    public HelmetPig() {
        super(100.0f, 1.0f, new Texture("HelmetPig_Healthy.png"), new Texture("HelmetPig_hurt.png"));
    }

    @Override
    public void PigRIP() {
        System.out.println("Helmet pig has died!");
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
