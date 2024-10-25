package com.angrybirds.game.Birds;

import com.angrybirds.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.assets.AssetManager;

public class Red extends Bird implements SpecialAbility {
    private String Name;
    private int Mass;
    private float Velocity;
    public Texture BirdModel;
    private Music BirdSound;

    public Red(){
        this.Name = "Red";
        this.Mass = 10;
        this.Velocity = 10;
        this.BirdModel = new Texture("Red.png");//game.assetManager.get("Red.png", Texture.class);
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("RedSound.mp3"));
        BirdList.add(this);
    }
    public void SpecialAbility() {
        return;}
}
