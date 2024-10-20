package com.angrybirds.game.Screen;

import com.angrybirds.game.Main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SignUpScreen implements Screen {

    private Main game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Texture backgroundTexture;
    private Stage stage;
    private TextButton signupButton, backButton;
    private Music theme;
    private BitmapFont font; // Store the font
    private Texture buttonTexture; // Store the button texture

    public SignUpScreen(Main game, BitmapFont font, Texture buttonTexture, Texture backgroundTexture, Music theme, OrthographicCamera gamecam, Viewport gameport) {
        this.game = game;
        this.font = font; // Assign the passed font
        this.buttonTexture = buttonTexture; // Assign the passed button texture
        this.backgroundTexture = backgroundTexture;
        this.theme = theme;
        this.gamecam = gamecam;
        this.gameport = gameport;

        stage = new Stage(gameport);

        // Create button styles using the passed font and button texture
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(buttonTexture);
        buttonStyle.down = new TextureRegionDrawable(buttonTexture);
        buttonStyle.font = font;

        // Create the buttons
        signupButton = new TextButton("SignUp", buttonStyle);
        backButton = new TextButton("Back", buttonStyle);

        // Set button sizes and positions
        signupButton.setSize(135, 80);
        backButton.setSize(120, 80);

        signupButton.setPosition((gameport.getWorldWidth() - signupButton.getWidth()) / 2, (gameport.getWorldHeight() / 2 )+60);
        backButton.setPosition((gameport.getWorldWidth() - backButton.getWidth()) / 2, (gameport.getWorldHeight() / 2) - 40);

        // Add button click listeners
        signupButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Signup button clicked!");
                // Handle signup logic here
                game.setScreen(new OptionsScreen(game, font, buttonTexture, backgroundTexture, theme, gamecam, gameport));

            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainScreen(game));  // Go back to MainScreen
            }
        });

        // Add elements to the stage
        stage.addActor(signupButton);
        stage.addActor(backButton);

        // Set input processor to handle button clicks
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
