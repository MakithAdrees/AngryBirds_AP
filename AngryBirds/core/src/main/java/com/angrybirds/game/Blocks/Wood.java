package com.angrybirds.game.Blocks;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Wood extends Block {

//    public Texture vertical_stick;
    public Wood(World world, Vector2 position, Vector2 dimension) {
        super(world, new Texture("wood_vertical_stick.png"), position, dimension);
//        this.Material_Name = "Wood";
//        this.Hp = Hp;
//        this.vertical_stick = new Texture("wood_vertical_stick.png");
//        this.Breaking_Sound = Gdx.audio.newMusic(Gdx.files.internal("block_breaking.mp3"));
    }
}