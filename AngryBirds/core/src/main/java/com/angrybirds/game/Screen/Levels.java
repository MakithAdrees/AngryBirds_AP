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
    final private Texture bg;
    final private ImageButton  load, loadtable, musicon, musicoff, back, mylevel;
    final private ImageButton level1, level2, level3, level4;
    final private Main game;
    final private OrthographicCamera gamecam;
    final private Viewport gameport;
    final private Stage stage;
    final private Music theme;


    public Levels(Main game, OrthographicCamera cam, Viewport port){
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
        mylevel.setVisible(false);

        Texture level11 = game.assetManager.get("level1.png", Texture.class);
        TextureRegion level111 = new TextureRegion(level11);
        TextureRegionDrawable level1111 = new TextureRegionDrawable(level111);
        level1 = new ImageButton(level1111);
        Texture level22 = game.assetManager.get("level2.png", Texture.class);
        TextureRegion level222 = new TextureRegion(level22);
        TextureRegionDrawable level2222 = new TextureRegionDrawable(level222);
        level2 = new ImageButton(level2222);
        Texture level33 = game.assetManager.get("level3.png", Texture.class);
        TextureRegion level333 = new TextureRegion(level33);
        TextureRegionDrawable level3333 = new TextureRegionDrawable(level333);
        level3 = new ImageButton(level3333);
        Texture level44 = game.assetManager.get("level4.png", Texture.class);
        TextureRegion level444 = new TextureRegion(level44);
        TextureRegionDrawable level4444 = new TextureRegionDrawable(level444);
        level4 = new ImageButton(level4444);

        level1.setPosition(218, (gameport.getWorldHeight() - level1.getHeight())/2);
        level2.setPosition(582, (gameport.getWorldHeight() - level2.getHeight())/2);
        level3.setPosition(946, (gameport.getWorldHeight() - level3.getHeight())/2);
        level4.setPosition(1314, (gameport.getWorldHeight() - level4.getHeight())/2);

        stage.addActor(level4);
        if (game.lev1)
            stage.addActor(level2);
        if (game.lev2)
            stage.addActor(level3);
        if (game.lev3)
            stage.addActor(level4);

        level1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Level1(game, cam, port));}});

        level2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Level2(game, cam, port));}});

        level3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Level3(game, cam, port));}});

        level4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new Level4(game, cam, port));}});

        Texture bac = game.assetManager.get("back.png", Texture.class);
        TextureRegion b = new TextureRegion(bac);
        TextureRegionDrawable b2 = new TextureRegionDrawable(b);
        back = new ImageButton(b2);
        back.setPosition((gameport.getWorldWidth()) - 150, (gameport.getWorldHeight()) - 150);
        back.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new OptionsScreen(game, cam, port));}
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
                    stage.addActor(musicoff);
                    game.musicOn = false;}}});
        musicoff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music button clicked in Options Screen!");
                if (!theme.isPlaying()){
                    theme.play();
                    musicoff.remove();
                    stage.addActor(musicon);
                    game.musicOn = true;}}});
        if (theme.isPlaying())
            stage.addActor(musicon);
        else
            stage.addActor(musicoff);


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
                game.setScreen(new Level1(game, cam, port));}});
        stage.addActor(load);
        stage.addActor(loadtable);
        loadtable.setVisible(false);
    Gdx.input.setInputProcessor(stage);}

    @Override
    public void show() {
        if (!theme.isPlaying()) {
            if (game.musicOn){
                theme.setLooping(true);
                theme.play();
                stage.addActor(musicon);
                musicoff.remove();}}
        else{
            if (!game.musicOn){
                stage.addActor(musicoff);
                musicon.remove();}}}

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
