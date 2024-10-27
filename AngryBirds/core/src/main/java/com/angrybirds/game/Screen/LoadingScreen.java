package com.angrybirds.game.Screen;

import com.angrybirds.game.Main;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//import java.awt.Color;

public class LoadingScreen extends ScreenAdapter {
    private final Stage stage;
    private final ProgressBar progressBar;
    private float loadingTime;
    private final float maxLoadingTime = 2f;

    private final Main game;
    private final OrthographicCamera gamecam;
    private final Texture bg;
    private final Viewport gameport;

    public LoadingScreen(Main game) {
        this.game = game;
//        stage = new Stage(new ScreenViewport());
        bg = game.assetManager.get("loadingimage.jpg", Texture.class);
        gamecam = new OrthographicCamera();
        gameport = new StretchViewport(1820, 980, gamecam);
        stage = new Stage(new StretchViewport(1820, 980));

        ProgressBarStyle progressBarStyle = createCustomProgressBarStyle();

        progressBar = new ProgressBar(0f, 1f, 0.01f, false, progressBarStyle);
        progressBar.setSize(800, 50);
        progressBar.setPosition(Gdx.graphics.getWidth() / 2f-400, Gdx.graphics.getHeight() / 2f-350);

        stage.addActor(progressBar);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        Gdx.gl.glClearColor(1, 0, 0, 1);
//        gamecam.update();
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(bg, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
        game.batch.end();

        loadingTime += delta;
        progressBar.setValue(Math.min(loadingTime / maxLoadingTime, 1f));

        stage.act();
        stage.draw();

        if (loadingTime >= maxLoadingTime) {
            game.setScreen(new MainScreen(game, gamecam, gameport));
        }
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        gamecam.update();
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        bg.dispose();
        stage.dispose();
    }

    private ProgressBarStyle createCustomProgressBarStyle() {
        Pixmap pixmapBackground = new Pixmap(1200, 50, Pixmap.Format.RGBA8888);
        pixmapBackground.setColor(0.55f, 0.27f, 0.07f, 1f);
        pixmapBackground.fillRectangle(0, 0, 1200, 50);

        Pixmap pixmapLoader = new Pixmap(400, 40, Pixmap.Format.RGBA8888);
        pixmapLoader.setColor(new Color(0.88f, 0.72f, 0.15f, 1f));
        pixmapLoader.fillRectangle(0, 0, 400, 40);

        Texture backgroundTexture = new Texture(pixmapBackground);
        Texture loaderTexture = new Texture(pixmapLoader);

        pixmapBackground.dispose();
        pixmapLoader.dispose();

        Drawable backgroundDrawable = new TextureRegionDrawable(new TextureRegion(backgroundTexture));
        Drawable loaderDrawable = new TextureRegionDrawable(new TextureRegion(loaderTexture));

        ProgressBarStyle progressBarStyle = new ProgressBarStyle();
        progressBarStyle.background = backgroundDrawable;
        progressBarStyle.knobBefore = loaderDrawable;

        return progressBarStyle;
    }
}
