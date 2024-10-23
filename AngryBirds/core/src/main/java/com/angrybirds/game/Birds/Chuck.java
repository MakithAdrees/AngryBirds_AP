package com.angrybirds.game.Birds;

public class Chuck extends Bird implements SpecialAbility {

    public Chuck() {
        this.Name = "Chuck";
        this.Mass = 10;
        this.Velocity = 10;
//        this.BirdModel = new Texture("Chuck.png");
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("ChuckSound.mp3"));
        BirdList.add(this);
    }
    public void SpecialAbility() {
        return;}
}
