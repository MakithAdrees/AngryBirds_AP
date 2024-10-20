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

public class OptionsScreen implements Screen {

    private Main game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Texture backgroundTexture;
    private Stage stage;
    private TextButton PlayButton, FeedbackButton, MusicButton, Exitbutton;
    private Music theme;
    private BitmapFont font; // Store the font
    private Texture buttonTexture; // Store the button texture

    public OptionsScreen(Main game, BitmapFont font, Texture buttonTexture, Texture backgroundTexture, Music theme, OrthographicCamera gamecam, Viewport gameport) {
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
        PlayButton = new TextButton("Play", buttonStyle);
        FeedbackButton = new TextButton("Feedback", buttonStyle);
        MusicButton = new TextButton("Music On/Off", buttonStyle);
        Exitbutton = new TextButton("Exit", buttonStyle);


        // Set button sizes and positions
        PlayButton.setSize(120, 80);
        FeedbackButton.setSize(190, 80);
        MusicButton.setSize(270, 80);
        Exitbutton.setSize(120, 80);


        PlayButton.setPosition((gameport.getWorldWidth() - PlayButton.getWidth()) / 2, (gameport.getWorldHeight() / 2 )+160);
        FeedbackButton.setPosition((gameport.getWorldWidth() - FeedbackButton.getWidth()) / 2 , (gameport.getWorldHeight() / 2) +60);
        MusicButton.setPosition(((gameport.getWorldWidth() - MusicButton.getWidth()) / 2) , (gameport.getWorldHeight() / 2 )- 40);
        Exitbutton.setPosition(((gameport.getWorldWidth() - Exitbutton.getWidth()) / 2 ), (gameport.getWorldHeight() / 2) - 140);

        // Add button click listeners
        PlayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Play button clicked in Options Screen!");
            }
        });

        FeedbackButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Feedback button clicked in Options Screen!");
            }
        });

        MusicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music button clicked in Options Screen!");
                if (theme.isPlaying()){
                    theme.pause();
                }
                else{
                    theme.play();
                }
            }
        });

        Exitbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Exit Button Clicked in Options Screen!");
                theme.dispose();
                game.setScreen(new MainScreen(game));
            }
        });

        // Add elements to the stage
        stage.addActor(PlayButton);
        stage.addActor(FeedbackButton);
        stage.addActor(MusicButton);
        stage.addActor(Exitbutton);

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
