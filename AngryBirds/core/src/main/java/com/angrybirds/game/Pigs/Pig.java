package com.angrybirds.game.Pigs;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

abstract public class Pig {
    protected float size;
    protected float Hp;
    protected Texture PigModel;
    protected Music PigSound;

    public void Damage_Taken(float damage) {
        Hp -= damage;
        if (Hp <= 0) {
            PigRIP();}}

    public void PigRIP() {}

    public void Change_Pig_Image(Pig pig){}
}
