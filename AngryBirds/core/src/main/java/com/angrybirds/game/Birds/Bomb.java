package com.angrybirds.game.Birds;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Bomb extends Bird implements SpecialAbility {
    private String Name;
    private int Mass;
    private float Velocity;
    public Texture BirdModel;
    private Music BirdSound;

    public Bomb(World wrld, Vector2 pos){
        super(wrld, pos, new Texture("Bomb.png"),10, "Bomb", 27);
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("BombSound.mp3")
    }
    public void SpecialAbility() {
        return;}
}
