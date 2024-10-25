package com.angrybirds.game.Pigs;

import com.badlogic.gdx.graphics.Texture;

public class NormalPigs extends Pig {
    private Texture Healthy, Hurt;

    public NormalPigs(){
        this.Healthy = new Texture("NormalPig_Healthy.png");
        this.Hurt = new Texture("NormalPig_Hurt.png");
    }

    public void PigRIP() {
        return;
    }

    public void Change_Pig_Image(Pig pig) {
        return;
    }
}
