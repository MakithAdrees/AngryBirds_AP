package com.angrybirds.game.Screen;

import com.angrybirds.game.Birds.Red;
import com.angrybirds.game.Birds.Chuck;
import com.angrybirds.game.Birds.Bomb;
import com.angrybirds.game.Extras.Catapult;


import com.angrybirds.game.Main;
import com.angrybirds.game.Pigs.MoustachePig;
import com.angrybirds.game.Pigs.NormalPigs;
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

public class Level1 implements Screen {
    private Texture bg;
    private ImageButton pause, resume, restart, menu, save, musicon, musicoff, pausemenu, won, victoryscreen, menu2, restart2, next;
    private Main game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Stage stage;
    private Music theme;
    private Red Red;
    private Chuck Chuck;
    private Bomb Bomb;
    private Catapult slingshot;
    private NormalPigs minion;
    private MoustachePig foreman;

    public Level1(Main game, Texture buttonTexture, Texture pressTexture, Music a, OrthographicCamera cam, Viewport port){
        this.game = game;
        this.gamecam = new OrthographicCamera();
        this.gameport = new StretchViewport(1820, 980, gamecam);

        bg = game.assetManager.get("gameplay_background.jpg", Texture.class);
        theme = game.assetManager.get("gameplaymusic.mp3", Music.class);

        Red = new Red();
        Chuck = new Chuck();
        Bomb = new Bomb();

        slingshot = new Catapult();
//        minion = new NormalPigs();
//        foreman = new MoustachePig();


        stage = new Stage(new StretchViewport(1820, 980));

        Texture r = game.assetManager.get("continue.png", Texture.class);
        TextureRegion r1 = new TextureRegion(r);
        TextureRegionDrawable r2 = new TextureRegionDrawable(r1);
        resume = new ImageButton(r2);
        resume.setPosition((gameport.getWorldWidth()-resume.getWidth())/2, gameport.getWorldHeight()-resume.getHeight()-50);

        Texture re = game.assetManager.get("replay.png", Texture.class);
        TextureRegion re1 = new TextureRegion(re);
        TextureRegionDrawable re2 = new TextureRegionDrawable(re1);
        restart = new ImageButton(re2);
        restart.setPosition((gameport.getWorldWidth())/2 - 110, 250);

        Texture s = game.assetManager.get("save.png", Texture.class);
        TextureRegion s1 = new TextureRegion(s);
        TextureRegionDrawable s2 = new TextureRegionDrawable(s1);
        save = new ImageButton(s2);
        save.setPosition((gameport.getWorldWidth())/2 + 130, 250);

        Texture u = game.assetManager.get("levelsmenu.png", Texture.class);
        TextureRegion u1 = new TextureRegion(u);
        TextureRegionDrawable u2 = new TextureRegionDrawable(u1);
        menu = new ImageButton(u2);
        menu.setPosition((gameport.getWorldWidth())/2 - 230, 250);

        Texture b = game.assetManager.get("pausescreen.png", Texture.class);
        TextureRegion b2 = new TextureRegion(b);
        TextureRegionDrawable b3 = new TextureRegionDrawable(b2);
        pausemenu = new ImageButton(b3);
        pausemenu.setPosition((gameport.getWorldWidth()-pausemenu.getWidth())/2, 0);
        Texture p = game.assetManager.get("pause.png", Texture.class);
        TextureRegion p1 = new TextureRegion(p);
        TextureRegionDrawable p2 = new TextureRegionDrawable(p1);
        pause = new ImageButton(p2);
        pause.setPosition(30, (gameport.getWorldHeight() - pause.getHeight()-30));

        Texture mon = game.assetManager.get("musicon.png", Texture.class);
        TextureRegion mon1 = new TextureRegion(mon);
        TextureRegionDrawable mon2 = new TextureRegionDrawable(mon1);
        musicon = new ImageButton(mon2);
        Texture mof = game.assetManager.get("musicoff.png",Texture.class);
        TextureRegion mof1 = new TextureRegion(mof);
        TextureRegionDrawable mof2 = new TextureRegionDrawable(mof1);
        musicoff = new ImageButton(mof2);
        musicon.setPosition((gameport.getWorldWidth())/2 + 10, 250);
        musicoff.setPosition((gameport.getWorldWidth())/2 + 10, 250);

        stage.addActor(pause);
        stage.addActor(pausemenu);
        pausemenu.setVisible(false);
        stage.addActor(resume);
        resume.setVisible(false);
        stage.addActor(restart);
        restart.setVisible(false);
        stage.addActor(save);
        save.setVisible(false);
        stage.addActor(menu);
        menu.setVisible(false);
        stage.addActor(musicon);
        musicon.setVisible(false);


        pause.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                if (pausemenu.isVisible()) {
                    pausemenu.setVisible(false);
                    resume.setVisible(false);
                    restart.setVisible(false);
                    save.setVisible(false);
                    menu.setVisible(false);
                    musicoff.setVisible(false);
                    musicon.setVisible(false);}
                else{
                    pausemenu.setVisible(true);
                    resume.setVisible(true);
                    restart.setVisible(true);
                    save.setVisible(true);
                    menu.setVisible(true);
                    if (theme.isPlaying())
                        musicon.setVisible(true);
                    else
                        musicoff.setVisible(true);}}});




        musicon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music button clicked in Options Screen!");
                if (theme.isPlaying()){
                    theme.pause();
                    musicon.remove();
                    stage.addActor(musicoff);}}});

        musicoff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music button clicked in Options Screen!");
                if (theme.isPlaying()){
                    theme.pause();}
                else{
                    theme.play();
                    musicoff.remove();
                    stage.addActor(musicon);}}});

        resume.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                pausemenu.setVisible(false);
                resume.setVisible(false);
                restart.setVisible(false);
                save.setVisible(false);
                menu.setVisible(false);
                musicoff.setVisible(false);
                musicon.setVisible(false);}});

        restart.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                pausemenu.setVisible(false);
                resume.setVisible(false);
                restart.setVisible(false);
                save.setVisible(false);
                menu.setVisible(false);
                musicoff.setVisible(false);
                musicon.setVisible(false);}});

        menu.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(new Levels(game, buttonTexture, pressTexture, a, cam, port));}});

        save.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(new Levels(game, buttonTexture, pressTexture, a, cam, port));}});

        Texture v = game.assetManager.get("victory.png", Texture.class);
        TextureRegion v1 = new TextureRegion(v);
        TextureRegionDrawable v2 = new TextureRegionDrawable(v1);
        victoryscreen = new ImageButton(v2);
        victoryscreen.setPosition((gameport.getWorldWidth()-victoryscreen.getWidth())/2, 0);

        Texture n = game.assetManager.get("next.png", Texture.class);
        TextureRegion n1 = new TextureRegion(n);
        TextureRegionDrawable n2 = new TextureRegionDrawable(n1);
        won = new ImageButton(n2);
        won.setPosition(gameport.getWorldWidth()-130, 30);
        next = new ImageButton(n2);
        next.setPosition((gameport.getWorldWidth())/2 + 70,200);

        menu2 = new ImageButton(u2);
        menu2.setPosition((gameport.getWorldWidth())/2 - 170,200);
        restart2 = new ImageButton(re2);
        restart2.setPosition((gameport.getWorldWidth())/2 - 50,200);
        stage.addActor(won);
        stage.addActor(victoryscreen);
        victoryscreen.setVisible(false);
        stage.addActor(next);
        next.setVisible(false);
        stage.addActor(menu2);
        menu2.setVisible(false);
        stage.addActor(restart2);
        restart2.setVisible(false);

        won.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                if (victoryscreen.isVisible()){
                    victoryscreen.setVisible(false);
                    menu2.setVisible(false);
                    restart2.setVisible(false);
                    next.setVisible(false);}
                else {
                    victoryscreen.setVisible(true);
                    menu2.setVisible(true);
                    restart2.setVisible(true);
                    next.setVisible(true);}}});

        restart2.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                victoryscreen.setVisible(false);
                menu2.setVisible(false);
                restart2.setVisible(false);
                next.setVisible(false);}});
        menu2.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(new Levels(game, buttonTexture, pressTexture, a, cam, port));}});
        next.addListener(new ClickListener(){
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(new Levels(game, buttonTexture, pressTexture, a, cam, port));}});
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

        //game.batch.draw(Red.BirdModel, 150, 300);

        game.batch.draw(slingshot.CatapultModel, 180, 130, slingshot.CatapultModel.getWidth() + 20, slingshot.CatapultModel.getHeight() + 20);
        game.batch.draw(Red.BirdModel, 201, 272, slingshot.CatapultModel.getWidth() - 10, slingshot.CatapultModel.getHeight() - 120);
        game.batch.draw(Chuck.BirdModel, 140, 130, slingshot.CatapultModel.getWidth() - 10, slingshot.CatapultModel.getHeight() - 120);
        game.batch.draw(Bomb.BirdModel, 70, 130, slingshot.CatapultModel.getWidth() - 5, slingshot.CatapultModel.getHeight() - 110);



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
        theme.pause();
    }

    @Override
    public void dispose() {
        bg.dispose();
        stage.dispose();
    }
}
