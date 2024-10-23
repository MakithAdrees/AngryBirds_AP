package com.angrybirds.game.Birds;

import com.angrybirds.game.Blocks.*;
import com.angrybirds.game.Pigs.Pig;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;


abstract public class Bird {
    protected int Mass;
    protected float Velocity;
    protected String Name;
    protected Texture BirdModel;
    public Music BirdSound;
    static public ArrayList<Bird> BirdList = new ArrayList<Bird>();

    public float Damage_Calculate(Bird bird) {
        return 0;}

    public void Collision(Bird bird, Block block, Pig pig ){
        return;
    }
}
