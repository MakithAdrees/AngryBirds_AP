package com.angrybirds.game.Blocks;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Glass extends Block {

    public Texture horizontal_stick,box;

    public Glass() {
        this.Material_Name = "Glass";
        this.Hp = Hp;
//        this.dimension = new Dimension()
        this.horizontal_stick = new Texture("glass_horizontal_stick.png");
//        this.Breaking_Sound = Gdx.audio.newMusic(Gdx.files.internal("block_breaking.mp3"));
    }
}