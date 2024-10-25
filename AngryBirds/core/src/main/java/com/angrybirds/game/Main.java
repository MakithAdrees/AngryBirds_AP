package com.angrybirds.game;

import com.angrybirds.game.Screen.LoadingScreen;
import com.angrybirds.game.Screen.MainScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

public class Main extends Game {
    public SpriteBatch batch;
    public AssetManager assetManager;

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
        assetManager.load("pausescreen.png", Texture.class);
        assetManager.load("victory.png", Texture.class);
        assetManager.load("optionscreenbg.png", Texture.class);
        assetManager.load("levelmenubg.png", Texture.class);
        assetManager.load("save.png", Texture.class);
        assetManager.load("load.png", Texture.class);
        assetManager.load("play.png", Texture.class);
        assetManager.load("faq.png", Texture.class);
        assetManager.load("level1.png", Texture.class);
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
        assetManager.load("font/font2.fnt", BitmapFont.class);
//        assetManager.load("ang.tff", BitmapFont.class);
        assetManager.load("font/w_0.png", Texture.class);
        assetManager.load("font/w_1.png", Texture.class);
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
