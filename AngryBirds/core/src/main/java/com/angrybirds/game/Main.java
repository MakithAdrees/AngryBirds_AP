package com.angrybirds.game;

import com.angrybirds.game.Screen.LoadingScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

public class Main extends Game {
    public SpriteBatch batch;
    public AssetManager assetManager;
    public boolean musicOn = true, lev1 = false, lev2 = false, lev3 = false, lev4 = false;
    @Override
    public void create(){
        batch = new SpriteBatch();
        assetManager = new AssetManager();

        // Preload assets
        assetManager.load("createlevel.png", Texture.class);
        assetManager.load("next.png", Texture.class);
        assetManager.load("pause.png", Texture.class);
        assetManager.load("continue.png", Texture.class);
        assetManager.load("replay.png", Texture.class);
        assetManager.load("levelsmenu.png", Texture.class);

        assetManager.load("Red.png", Texture.class);

        assetManager.load("gameplay_background.jpg", Texture.class);
        assetManager.load("pausescreen1.png", Texture.class);
        assetManager.load("pausescreen2.png", Texture.class);
        assetManager.load("pausescreen3.png", Texture.class);
        assetManager.load("pausescreen4.png", Texture.class);

        assetManager.load("victory.png", Texture.class);
        assetManager.load("lost.png", Texture.class);
        assetManager.load("optionscreenbg.png", Texture.class);
        assetManager.load("levelmenubg.png", Texture.class);
        assetManager.load("save.png", Texture.class);
        assetManager.load("load.png", Texture.class);
        assetManager.load("load_button.png", Texture.class);
        assetManager.load("play.png", Texture.class);
        assetManager.load("faq.png", Texture.class);
        assetManager.load("level1.png", Texture.class);
        assetManager.load("level2.png", Texture.class);
        assetManager.load("level3.png", Texture.class);
        assetManager.load("level4.png", Texture.class);
        assetManager.load("musicon.png", Texture.class);
        assetManager.load("musicoff.png", Texture.class);
        assetManager.load("quit.png", Texture.class);
        assetManager.load("back.png", Texture.class);
        assetManager.load("background.jpg", Texture.class);
        assetManager.load("angrybirdsbg.jpg", Texture.class);
        assetManager.load("loadingimage.jpg", Texture.class);
        assetManager.load("login.png", Texture.class);
        assetManager.load("signup.png", Texture.class);
        assetManager.load("bad.mp3", Music.class);
        assetManager.load("levelmusic.mp3", Music.class);
        assetManager.load("gameplaymusic.mp3", Music.class);
        assetManager.load("button.png", Texture.class);
        assetManager.load("button_rectangle_flat.png", Texture.class);
        assetManager.load("button_rectangle_gloss.png", Texture.class);
        assetManager.load("check_square_color_cross.png", Texture.class);

        //pigs
        assetManager.load("NormalPig_Healthy.png", Texture.class);
        assetManager.load("MoustachePig_Healthy.png", Texture.class);
        assetManager.load("king_pig.png", Texture.class);
        assetManager.load("soldier_pig.png", Texture.class);

        //blocks
        assetManager.load("glass_horizontal_stick.png", Texture.class);
        assetManager.load("cracked_glass_horizontal_stick.png", Texture.class);
        assetManager.load("wood_vertical_stick.png", Texture.class);
        assetManager.load("cracked_wood_vertical_stick.png", Texture.class);
        assetManager.load("stone_horizontal_stick.png", Texture.class);
        assetManager.load("cracked_stone_horizontal_stick.png", Texture.class);
        assetManager.load("stone_vertical_stick.png", Texture.class);


        assetManager.finishLoading();

        setScreen(new LoadingScreen(this));

    }
    @Override
    public void render(){
        super.render();
    }
    @Override
    public void dispose() {
    }
}
