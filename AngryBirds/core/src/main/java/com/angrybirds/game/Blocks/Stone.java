package com.angrybirds.game.Blocks;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Stone extends Block {
//    public Texture horizontal_stick;


    public Stone(World world, Texture block_texture, Vector2 position, Vector2 dimension) {
        super(world, block_texture, position, dimension);
//
//        this.Material_Name = "Stone";
//        this.Hp = Hp;
//        this.horizontal_stick = new Texture("stone_horizontal_stick.png");
//        this.Breaking_Sound = Gdx.audio.newMusic(Gdx.files.internal("block_breaking.mp3"));
    }
}