package com.angrybirds.game.Pigs;

import com.badlogic.gdx.graphics.Texture;

public class MoustachePig extends Pig {

    public MoustachePig() {
        super(100.0f, 1.0f, new Texture("MoustachePig_Healthy.png"), new Texture("MoustachePig_Healthy.png"));
    }

    @Override
    public void PigRIP() {
        System.out.println("Normal pig has died!");
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
