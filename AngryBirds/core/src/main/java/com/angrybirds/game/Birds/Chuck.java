package com.angrybirds.game.Birds;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Chuck extends Bird implements SpecialAbility {
    private Music BirdSound;

    public Chuck(World wrld, Vector2 pos){
        super(wrld, pos, new Texture("Chuck.png"),10, "Chuck", 23);

//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("ChuckSound.mp3"));

    }
    @Override
    public void SpecialAbility() {
        return;}
}
