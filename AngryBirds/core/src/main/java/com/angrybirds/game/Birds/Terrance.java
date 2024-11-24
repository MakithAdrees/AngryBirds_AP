package com.angrybirds.game.Birds;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Terrance extends Bird implements SpecialAbility {

    public Terrance(World wrld, Vector2 pos){
        super(wrld, pos, new Texture("Terrance.png"),10, "Red");

//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("TerranceSound.mp3"));
    }
    @Override
    public void SpecialAbility() {
        return;}
}
