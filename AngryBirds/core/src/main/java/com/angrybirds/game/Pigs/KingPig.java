package com.angrybirds.game.Pigs;

import com.badlogic.gdx.graphics.Texture;

public class KingPig extends Pig {
    public Texture Healthy, Hurt;

    public KingPig(){
        this.Healthy = new Texture("KingPig_Healthy.png");
        this.Hurt = new Texture("KingPig_Hurt.png");
    }
    public void KingDead() {
        return;
    }

    public void Change_Pig_Image(Pig pig) {
        return;
    }
}
