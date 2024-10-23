package com.angrybirds.game.Birds;

public class Blues extends Bird implements SpecialAbility {

    public Blues() {
        this.Name = "Blues";
        this.Mass = 10;
        this.Velocity = 10;
//        this.BirdModel = new Texture("Blues.png");
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("BluesSound.mp3"));
        BirdList.add(this);
    }
    public void SpecialAbility() {
        return;}
}
