package com.angrybirds.game.Screen;

import com.angrybirds.game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.foreign.MemorySegment.NULL;

public class OptionsScreen implements Screen {

    private final Main game;
    private final OrthographicCamera gamecam;
    private final Viewport gameport;
    private final Texture backgroundTexture;
    private final Stage stage;
//    private TextButton FeedbackButton, MusicButton, backButton;
    private Levels lev;
    private final ImageButton PlayButton, faq, musicon, musicoff, back;
    private final Music theme;

    public OptionsScreen(Main game, OrthographicCamera gamecam, Viewport gameport) {
        this.game = game;
        backgroundTexture = game.assetManager.get("optionscreenbg.png", Texture.class);
        this.theme = game.assetManager.get("bad.mp3", Music.class);
        this.gamecam = gamecam;
        this.gameport = gameport;

        stage = new Stage(gameport);

        Texture bac = game.assetManager.get("quit.png", Texture.class);
        TextureRegion b = new TextureRegion(bac);
        TextureRegionDrawable b2 = new TextureRegionDrawable(b);
        back = new ImageButton(b2);
        back.setPosition((gameport.getWorldWidth()) - 150, (gameport.getWorldHeight()) - 150);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                game.setScreen(new MainScreen(game, gamecam, gameport));
            }
        });
        stage.addActor(back);

        Texture play = game.assetManager.get("play.png", Texture.class);
        TextureRegion t = new TextureRegion(play);
        TextureRegionDrawable t2 = new TextureRegionDrawable(t);
        PlayButton = new ImageButton(t2);
        Texture f = game.assetManager.get("faq.png", Texture.class);
        TextureRegion f1 = new TextureRegion(f);
        TextureRegionDrawable f2 = new TextureRegionDrawable(f1);
        faq = new ImageButton(f2);

        Texture mon = game.assetManager.get("musicon.png", Texture.class);
        TextureRegion mon1 = new TextureRegion(mon);
        TextureRegionDrawable mon2 = new TextureRegionDrawable(mon1);
        musicon = new ImageButton(mon2);
        Texture mof = game.assetManager.get("musicoff.png",Texture.class);
        TextureRegion mof1 = new TextureRegion(mof);
        TextureRegionDrawable mof2 = new TextureRegionDrawable(mof1);
        musicoff = new ImageButton(mof2);
//        Exitbutton = new TextButton("Exit", buttonStyle);


        PlayButton.setPosition((gameport.getWorldWidth() - PlayButton.getWidth()) / 2, (gameport.getWorldHeight() / 2 )-200);
        faq.setPosition(gameport.getWorldWidth() -130, 30);
        musicon.setPosition(30 , gameport.getWorldHeight() - 110);
        musicoff.setPosition(30, gameport.getWorldHeight() - 110);
//        Exitbutton.setPosition(((gameport.getWorldWidth() - Exitbutton.getWidth()) / 2 ), (gameport.getWorldHeight() / 2) - 140);

        PlayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play button clicked in Options Screen!");
                game.setScreen(new Levels(game, theme, gamecam, gameport));
            }
        });

        faq.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Feedback button clicked in Options Screen!");
            }
        });

        musicon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music button clicked in Options Screen!");
                if (theme.isPlaying()){
                    theme.pause();
                    musicon.remove();
                    stage.addActor(musicoff);}
            }
        });
        musicoff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music button clicked in Options Screen!");
                if (theme.isPlaying()){
                    theme.pause();
                }
                else{
                    theme.play();
                    musicoff.remove();
                    stage.addActor(musicon);
                }
            }
        });



        stage.addActor(PlayButton);
        stage.addActor(faq);
        if (theme.isPlaying())
            stage.addActor(musicon);
        else
            stage.addActor(musicoff);
//        stage.addActor(Exitbutton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        if (!theme.isPlaying()) {
            theme.setLooping(true);
            theme.play();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
        game.batch.end();


        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        gamecam.update();
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        theme.dispose();
    }
}
