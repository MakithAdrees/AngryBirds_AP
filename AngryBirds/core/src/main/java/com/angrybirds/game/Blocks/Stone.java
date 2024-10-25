package com.angrybirds.game.Blocks;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Stone extends Block {
    public Texture horizontal_stick;


    public Stone() {
        this.Material_Name = "Stone";
        this.Hp = Hp;
        this.horizontal_stick = new Texture("stone_horizontal_stick.png");
//        this.Breaking_Sound = Gdx.audio.newMusic(Gdx.files.internal("block_breaking.mp3"));
    }
}