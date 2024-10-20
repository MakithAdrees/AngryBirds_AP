package com.angrybirds.game.Screen;

import com.angrybirds.game.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainScreen implements Screen {

    private OrthographicCamera gamecam;
    private Texture backgroundTexture;
    private Main game;
    private Viewport gameport;
    private Stage stage;
    private Skin skin;
    private TextButton loginButton, signupButton;
    public Music theme;
    private Texture buttonTexture;
    private BitmapFont font;

    public MainScreen(Main game) {
        this.game = game;
        backgroundTexture = game.assetManager.get("background.jpg", Texture.class);
        buttonTexture = game.assetManager.get("button.png", Texture.class);
        gamecam = new OrthographicCamera();
        gameport = new StretchViewport(480, 800, gamecam);

        // Create a stage for UI
        stage = new Stage(new StretchViewport(480, 800));

        theme = game.assetManager.get("theme_song_for_game.mp3", Music.class);

        // Load the font using the .fnt file
        font = game.assetManager.get("font/font2.fnt", BitmapFont.class);

        // Create a skin for the buttons
        skin = new Skin();

        skin.add("font2", font);

        // Create button styles using the loaded font
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();

        // Scale the button texture using TextureRegion
        TextureRegion buttonRegion = new TextureRegion(buttonTexture);
        buttonRegion.setRegion(0, 0, buttonTexture.getWidth(), buttonTexture.getHeight()); // Original region

        buttonStyle.up = new TextureRegionDrawable(buttonRegion); // Use scaled button texture
        buttonStyle.down = new TextureRegionDrawable(buttonRegion); // Use scaled button texture for pressed state
        buttonStyle.font = font;

        // Create buttons with the style
        loginButton = new TextButton("Login", buttonStyle);
        signupButton = new TextButton("SignUp", buttonStyle);

        // Set button sizes based on the texture size (if needed)
        loginButton.setSize(120, 80);  // Width, Height
        signupButton.setSize(135, 80); // Width, Height

        // Center buttons in the middle of the screen
        loginButton.setPosition((gameport.getWorldWidth() - loginButton.getWidth()) / 2, (gameport.getWorldHeight() / 2) + 60);  // Adjust Y position for spacing
        signupButton.setPosition((gameport.getWorldWidth() - signupButton.getWidth()) / 2, (gameport.getWorldHeight() / 2) - 40); // Adjust Y position for spacing

        // Add listeners to buttons
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Login button clicked!");
                // Handle login button logic here
                game.setScreen(new LoginScreen(game, font, buttonTexture, backgroundTexture, theme, gamecam, gameport));
            }
        });

        signupButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Signup button clicked!");
                game.setScreen(new SignUpScreen(game, font, buttonTexture, backgroundTexture, theme, gamecam, gameport));
            }
        });

        // Add buttons to the stage
        stage.addActor(loginButton);
        stage.addActor(signupButton);

        // Set input processor to handle button clicks
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        theme.setLooping(true);
        theme.play();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
        game.batch.end();

        // Draw the stage (buttons)
        stage.act(delta);
        stage.draw(); // Draws the buttons over the background
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
    public void hide() {}

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        stage.dispose();

    }
}
