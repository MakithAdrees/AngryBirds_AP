package com.angrybirds.game.Birds;

public class Terrance extends Bird implements SpecialAbility {

    public Terrance() {
        this.Name = "Terrance";
        this.Mass = 10;
        this.Velocity = 10;
//        this.BirdModel = new Texture("Terrance.png");
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("TerranceSound.mp3"));
        BirdList.add(this);
    }
    public void SpecialAbility() {
        return;}
}
