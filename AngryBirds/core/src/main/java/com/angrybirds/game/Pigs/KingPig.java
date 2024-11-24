package com.angrybirds.game.Pigs;

import com.badlogic.gdx.graphics.Texture;

public class KingPig extends Pig {

    public KingPig() {
        super(100.0f, 1.0f, new Texture("KingPig_Healthy.png"), new Texture("KingPig_hurt.png"));
    }

    @Override
    public void PigRIP() {
        System.out.println("King pig has died!");
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
