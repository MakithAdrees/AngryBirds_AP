package com.angrybirds.game;

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
        assetManager.load("background.jpg", Texture.class);
        assetManager.load("theme_song_for_game.mp3", Music.class);
        assetManager.load("button.png", Texture.class);
        assetManager.load("font/font2.fnt", BitmapFont.class);
        assetManager.load("font/w_0.png", Texture.class);
        assetManager.load("font/w_1.png", Texture.class);
        assetManager.finishLoading();

        setScreen(new MainScreen(this));

    }
    @Override
    public void render(){
        super.render();
    }
    @Override
    public void dispose() {
    }
}
