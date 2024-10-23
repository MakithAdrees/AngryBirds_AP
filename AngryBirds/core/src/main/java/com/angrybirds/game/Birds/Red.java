package com.angrybirds.game.Birds;

public class Red extends Bird implements SpecialAbility {

    public Red() {
        this.Name = "Red";
        this.Mass = 10;
        this.Velocity = 10;
//        this.BirdModel = new Texture("Red.png");
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("RedSound.mp3"));
        BirdList.add(this);
    }
    public void SpecialAbility() {
        return;}
}
