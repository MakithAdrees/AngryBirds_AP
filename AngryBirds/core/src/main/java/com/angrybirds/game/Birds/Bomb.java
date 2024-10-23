package com.angrybirds.game.Birds;

public class Bomb extends Bird implements SpecialAbility {

    public Bomb() {
        this.Name = "Bomb";
        this.Mass = 10;
        this.Velocity = 10;
//        this.BirdModel = new Texture("Bomb.png");
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("BombSound.mp3"));
        BirdList.add(this);
    }
    public void SpecialAbility() {
        return;}
}
