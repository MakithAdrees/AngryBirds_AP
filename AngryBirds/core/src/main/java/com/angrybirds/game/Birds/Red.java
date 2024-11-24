package com.angrybirds.game.Birds;

import com.angrybirds.game.Main;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Red extends Bird implements SpecialAbility {

    public Red(World wrld, Vector2 pos){
        super(wrld, pos, new Texture("Red.png"),100, "Red");
//        this.BirdSound = Gdx.audio.newMusic(Gdx.files.internal("RedSound.mp3"));
    }
    @Override
    public void SpecialAbility() {

        return;}
}
