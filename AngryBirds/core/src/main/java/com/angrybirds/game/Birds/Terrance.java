package com.angrybirds.game.Birds;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Terrance extends Bird implements SpecialAbility {
    private String Name;
    private int Mass;
    private float Velocity;
    public Texture BirdModel;
    private Music BirdSound;

    public Terrance() {
        this.Name = "Terrance";
        this.Mass = 10;
        this.Velocity = 10;
        this.BirdModel = new Texture("Terrance.png");
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("TerranceSound.mp3"));
        BirdList.add(this);
    }
    public void SpecialAbility() {
        return;}
}
