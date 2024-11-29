package com.angrybirds.game.Birds;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Blues extends Bird implements SpecialAbility {

    private String Name;
    private int Mass;

    public Texture BirdModel;
    private Music BirdSound;

    public Blues(World wrld, Vector2 pos) {
        super(wrld, pos, new Texture("Blue.png"), 10, "Blues", 20);
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("RedSound.mp3"));
    }

    public Blues(Vector2 pos) {
        super(pos, 10, "Blues", 20);}

    public void SpecialAbility() {
        return;
    }
}

