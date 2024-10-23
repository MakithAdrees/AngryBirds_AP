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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.Viewport;

public class LoginScreen implements Screen {

    private Main game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Texture backgroundTexture;
    private Stage stage;
    private TextButton LoginButton, backButton;
    private Music theme;
    private BitmapFont font; // Store the font
    private Texture buttonTexture, pressTexture;

    public LoginScreen(Main game, BitmapFont font, Texture buttonTexture, Texture pressTexture, Music theme, OrthographicCamera gamecam, Viewport gameport) {
        this.game = game;
        this.font = font; // Assign the passed font
        this.buttonTexture = buttonTexture; // Assign the passed button texture
        this.pressTexture = pressTexture;
        this.backgroundTexture = game.assetManager.get("login.png", Texture.class);
        this.theme = theme;
        this.gamecam = gamecam;
        this.gameport = gameport;

        stage = new Stage(gameport);


        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(buttonTexture);
        buttonStyle.down = new TextureRegionDrawable(pressTexture);
        buttonStyle.font = font;
        buttonStyle.fontColor = Color.BLACK;

        LoginButton = new TextButton("Continue", buttonStyle);
        Texture buttonTex = game.assetManager.get("back.png", Texture.class);
        TextButton.TextButtonStyle buttStyle = new TextButton.TextButtonStyle();
        TextureRegion buttonReg = new TextureRegion(buttonTex);
        buttStyle.up = new TextureRegionDrawable(buttonReg);
        buttStyle.down = new TextureRegionDrawable(buttonReg);
        buttStyle.font = font;

        backButton = new TextButton("", buttStyle);

        // Set button sizes and positions
        LoginButton.setSize(500, 120);
        backButton.setSize(120, 120);

        LoginButton.setPosition((gameport.getWorldWidth() - LoginButton.getWidth()) / 2, (gameport.getWorldHeight() / 2 )-350);
        backButton.setPosition((gameport.getWorldWidth()) - 150, (gameport.getWorldHeight()) - 150);

        // Add button click listeners
        LoginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Signup button clicked!");
                // Handle signup logic here
                game.setScreen(new OptionsScreen(game, buttonTexture, pressTexture, theme, gamecam, gameport));

            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainScreen(game));  // Go back to MainScreen
            }
        });

        // Add elements to the stage
        stage.addActor(LoginButton);
        stage.addActor(backButton);

        // Set input processor to handle button clicks
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
//        if (!theme.isPlaying()) {
//            theme.setLooping(true);
//            theme.play();
//        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
        game.batch.end();

        // Draw UI elements
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
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
        stage.dispose();
    }
}
