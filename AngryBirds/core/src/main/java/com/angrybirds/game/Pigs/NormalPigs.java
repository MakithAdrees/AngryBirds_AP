package com.angrybirds.game.Pigs;

import com.badlogic.gdx.graphics.Texture;

public class NormalPigs extends Pig {
    public NormalPigs() {
        super(100.0f, 1.0f, new Texture("NormalPig_Healthy.png"), new Texture("NormalPig_Healthy.png"));
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
