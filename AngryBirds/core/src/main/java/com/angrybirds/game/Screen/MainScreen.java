//package com.angrybirds.game.Screen;
//
//import com.angrybirds.game.Main;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.viewport.StretchViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//
//public class MainScreen implements Screen {
//
//    private final OrthographicCamera gamecam;
//    private final Texture backgroundTexture;
//    private final Main game;
//    private final Viewport gameport;
//    private final Stage stage;
//    private final Skin skin;
//    private final TextButton loginButton, signupButton;
//    private final ImageButton musicon, musicoff;
//    public final Music theme;
//    private final Texture buttonTexture, pressTexture;
//    private final BitmapFont font;
//
//    public MainScreen(Main game,OrthographicCamera gamecam, Viewport gameport) {
//        this.game = game;
//        this.gamecam = gamecam;
//        this.gameport = gameport;
//        backgroundTexture = game.assetManager.get("angrybirdsbg.jpg", Texture.class);
//        buttonTexture = game.assetManager.get("button_rectangle_flat.png", Texture.class);
//        pressTexture = game.assetManager.get("button_rectangle_gloss.png", Texture.class);
////        gamecam = new OrthographicCamera();
////        gameport = new StretchViewport(1820, 980, gamecam);
//
//        stage = new Stage(new StretchViewport(1820, 980));
//
//        theme = game.assetManager.get("bad.mp3", Music.class);
//
//        Texture mon = game.assetManager.get("musicon.png", Texture.class);
//        TextureRegion mon1 = new TextureRegion(mon);
//        TextureRegionDrawable mon2 = new TextureRegionDrawable(mon1);
//        musicon = new ImageButton(mon2);
//        Texture mof = game.assetManager.get("musicoff.png",Texture.class);
//        TextureRegion mof1 = new TextureRegion(mof);
//        TextureRegionDrawable mof2 = new TextureRegionDrawable(mof1);
//        musicoff = new ImageButton(mof2);
//        musicon.setPosition(30 , gameport.getWorldHeight() - 110);
//        musicoff.setPosition(30, gameport.getWorldHeight() - 110);
//        musicon.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Music button clicked in Options Screen!");
//                if (theme.isPlaying()){
//                    theme.pause();
//                    musicon.remove();
//                    stage.addActor(musicoff);}}
//        });
//        musicoff.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Music button clicked in Options Screen!");
//                if (theme.isPlaying()){
//                    theme.pause();}
//                else{
//                    theme.play();
//                    musicoff.remove();
//                    stage.addActor(musicon);}}});
//        if (theme.isPlaying())
//            stage.addActor(musicoff);
//        else
//            stage.addActor(musicon);
//        font = game.assetManager.get("font/font2.fnt", BitmapFont.class);
//
//
//        skin = new Skin();
//        skin.add("font2", font);
//
//        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
//        TextButton.TextButtonStyle pressStyle = new TextButton.TextButtonStyle();
//
//        TextureRegion buttonRegion = new TextureRegion(buttonTexture);
//        TextureRegion pressRegion = new TextureRegion(pressTexture);
//        buttonRegion.setRegion(0, 0, buttonTexture.getWidth(), buttonTexture.getHeight());
//        pressRegion.setRegion(0, 0, pressTexture.getWidth(), pressTexture.getHeight());
//
//        buttonStyle.up = new TextureRegionDrawable(buttonRegion);
//        buttonStyle.down = new TextureRegionDrawable(pressRegion);
//        buttonStyle.font = font;
//        buttonStyle.fontColor = Color.BLACK;
//
//        loginButton = new TextButton("Login", buttonStyle);
//        signupButton = new TextButton("SignUp", buttonStyle);
//
//        loginButton.setSize(300, 100);
//        signupButton.setSize(300, 100);
//
//        loginButton.setPosition((gameport.getWorldWidth() - loginButton.getWidth()) / 2 - 250, (gameport.getWorldHeight() / 2) - 450);
//        signupButton.setPosition((gameport.getWorldWidth() - signupButton.getWidth()) / 2 + 250, (gameport.getWorldHeight() / 2) - 450);
//
//        loginButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Login button clicked!");
//                game.setScreen(new LoginScreen(game, font, buttonTexture, pressTexture, theme, gamecam, gameport));
//            }
//        });
//
//        signupButton.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Signup button clicked!");
//                game.setScreen(new SignUpScreen(game, font, buttonTexture, pressTexture, theme, gamecam, gameport));
//            }
//        });
//
//        stage.addActor(loginButton);
//        stage.addActor(signupButton);
//
//        Gdx.input.setInputProcessor(stage);
//    }
//
//    @Override
//    public void show() {
//        theme.setLooping(true);
//        theme.play();
//    }
//
//    @Override
//    public void render(float delta) {
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        game.batch.setProjectionMatrix(gamecam.combined);
//        game.batch.begin();
//        game.batch.draw(backgroundTexture, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
//        game.batch.end();
//
//        stage.act(delta);
//        stage.draw();
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        gameport.update(width, height);
//        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
//        gamecam.update();
//        stage.getViewport().update(width, height, true);
//    }
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {}
//
//    @Override
//    public void dispose() {
//        backgroundTexture.dispose();
//        stage.dispose();
//
//    }
//}
