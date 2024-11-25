package com.angrybirds.game.Blocks;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Wood extends Block {

    public Wood(World world,Texture block_texture, Vector2 position, Vector2 dimension) {
        super(world,block_texture, position, dimension);
//        this.Hp = Hp;

    }
}