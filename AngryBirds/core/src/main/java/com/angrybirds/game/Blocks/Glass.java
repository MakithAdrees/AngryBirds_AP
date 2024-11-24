package com.angrybirds.game.Blocks;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Glass extends Block {


    public Glass(World world, Vector2 position, Vector2 dimension) {
        super(world, new Texture("glass_horizontal_stick.png"), position, dimension);
//        this.Material_Name = "Glass";
//        this.Hp = Hp;
//        this.dimension = new Dimension()
//        this.horizontal_stick = new Texture("glass_horizontal_stick.png");
//        this.Breaking_Sound = Gdx.audio.newMusic(Gdx.files.internal("block_breaking.mp3"));
    }
}