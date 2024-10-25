package com.angrybirds.game.Blocks;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Wood extends Block {

    public Texture vertical_stick;
    public Wood() {
        this.Material_Name = "Wood";
        this.Hp = Hp;
        this.vertical_stick = new Texture("wood_vertical_stick.png");
//        this.Breaking_Sound = Gdx.audio.newMusic(Gdx.files.internal("block_breaking.mp3"));
    }
}