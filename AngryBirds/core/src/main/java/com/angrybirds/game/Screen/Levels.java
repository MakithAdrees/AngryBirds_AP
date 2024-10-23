package com.angrybirds.game.Screen;

import com.angrybirds.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Levels implements Screen {
    private Texture bg;
    private ImageButton level1, load, loadtable, musicon, musicoff, back, mylevel;
    private Main game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Stage stage;
    private Music theme;

    public Levels(Main game, Texture buttonTexture, Texture pressTexture, Music a, OrthographicCamera cam, Viewport port){
        this.game = game;
        this.gamecam = new OrthographicCamera();
        this.gameport = new StretchViewport(1820, 980, gamecam);

        bg = game.assetManager.get("levelmenubg.png", Texture.class);
        theme = game.assetManager.get("levelmusic.mp3", Music.class);

        stage = new Stage(new StretchViewport(1820, 980));

        Texture my = game.assetManager.get("createlevel.png", Texture.class);
        TextureRegion my1 = new TextureRegion(my);
        TextureRegionDrawable my2 = new TextureRegionDrawable(my1);
        mylevel = new ImageButton(my2);
        mylevel.setPosition(30, 30);
        stage.addActor(mylevel);

        Texture lev = game.assetManager.get("level1.png", Texture.class);
        TextureRegion l = new TextureRegion(lev);
        TextureRegionDrawable l2 = new TextureRegionDrawable(l);
        level1 = new ImageButton(l2);
        level1.setPosition(218, (gameport.getWorldHeight() - level1.getHeight())/2);
        stage.addActor(level1);
        level1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Level1(game, buttonTexture, pressTexture, a, cam, port));
            }
        });

        Texture bac = game.assetManager.get("back.png", Texture.class);
        TextureRegion b = new TextureRegion(bac);
        TextureRegionDrawable b2 = new TextureRegionDrawable(b);
        back = new ImageButton(b2);
        back.setPosition((gameport.getWorldWidth()) - 150, (gameport.getWorldHeight()) - 150);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                game.setScreen(new OptionsScreen(game, buttonTexture, pressTexture, a, cam, port));  // Go back to MainScreen
            }
        });
        stage.addActor(back);

        Texture mon = game.assetManager.get("musicon.png", Texture.class);
        TextureRegion mon1 = new TextureRegion(mon);
        TextureRegionDrawable mon2 = new TextureRegionDrawable(mon1);
        musicon = new ImageButton(mon2);
        Texture mof = game.assetManager.get("musicoff.png",Texture.class);
        TextureRegion mof1 = new TextureRegion(mof);
        TextureRegionDrawable mof2 = new TextureRegionDrawable(mof1);
        musicoff = new ImageButton(mof2);
        musicon.setPosition(30 , gameport.getWorldHeight() - 110);
        musicoff.setPosition(30, gameport.getWorldHeight() - 110);
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
        stage.addActor(musicon);

        Texture la1 = game.assetManager.get("save.png", Texture.class);
        TextureRegion la2 = new TextureRegion(la1);
        TextureRegionDrawable la3 = new TextureRegionDrawable(la2);
        load = new ImageButton(la3);
        Texture lt1 = game.assetManager.get("load.png", Texture.class);
        TextureRegion lt2 = new TextureRegion(lt1);
        TextureRegionDrawable lt3 = new TextureRegionDrawable(lt2);
        loadtable = new ImageButton(lt3);

        load.setPosition(gameport.getWorldWidth() -130, 30);
        loadtable.setPosition((gameport.getWorldWidth()-loadtable.getWidth())/2, (gameport.getWorldHeight()-loadtable.getHeight())/2);

        load.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Loading");
                if (loadtable.isVisible())
                    loadtable.setVisible(false);
                else
                    loadtable.setVisible(true);}});

        loadtable.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Level1(game, buttonTexture, pressTexture, a, cam, port));}});
        stage.addActor(load);
        stage.addActor(loadtable);
        loadtable.setVisible(false);
    Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        theme.setLooping(true);
        theme.play();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(bg, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
        game.batch.end();

        stage.act(v);
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        if (theme.isPlaying())
            theme.pause();
    }

    @Override
    public void dispose() {
//        bg.dispose();
//        stage.dispose();
        theme.dispose();
    }
}
