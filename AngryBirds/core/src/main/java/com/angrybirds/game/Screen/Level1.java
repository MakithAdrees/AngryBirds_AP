package com.angrybirds.game.Screen;
import com.angrybirds.game.Birds.Bird;
import com.angrybirds.game.Birds.Red;
import com.angrybirds.game.Birds.Chuck;
import com.angrybirds.game.Birds.Bomb;
//import com.angrybirds.game.Extras.Catapult;
import com.angrybirds.game.Blocks.Block;
import com.angrybirds.game.Blocks.Glass;
import com.angrybirds.game.Blocks.Wood;
import com.angrybirds.game.Blocks.Stone;

import com.angrybirds.game.Extras.Catapult;
import com.angrybirds.game.Main;
import com.angrybirds.game.Pigs.MoustachePig;
import com.angrybirds.game.Pigs.NormalPigs;
import com.angrybirds.game.Pigs.Pig;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.InputProcessor;


import java.util.ArrayList;

import static com.angrybirds.game.Birds.Bird.GRAVITY;
import static java.lang.Math.abs;

public class Level1 implements Screen, InputProcessor {
    final Texture bg;
    final ImageButton pause, resume, restart, menu, save, musicon, musicoff, pausemenu, won, victoryscreen, menu2, restart2, next;
    private Main game;
    final OrthographicCamera gamecam;
    final Viewport gameport;
    final Stage stage;
    final Music theme;
    private Red Red;
    private Chuck Chuck;
    private Bomb Bomb;
    private NormalPigs minion;
    private MoustachePig foreman;
    private Glass glass;
    private Wood wood1, wood2, wood3, wood4;
    private Stone stone;
    private World wld;
    private Box2DDebugRenderer dbgrndr;
    private InputMultiplexer inputMultiplexer;

    private TextureRegion wd_rg;

    private Body groundBody;

    public Texture catapult;


    ArrayList<Bird> birds = new ArrayList<>();
    ArrayList<Block> blocks_list = new ArrayList<>();
    ArrayList<Pig> pig_list = new ArrayList<>();

    private boolean isDragging = false;
    private Bird selectedBird = null;
    private Vector2 dragStartPosition = new Vector2();
    private Vector2 currentMousePosition = new Vector2();
    private Vector2 slingshotPosition = new Vector2(250, 290);

    private Texture glass_texture, wood_texture, stone_texture, minion_tex, foreman_tex;
    private ShapeRenderer shapeRenderer;



    public Level1(Main game, OrthographicCamera cam, Viewport port) {
        this.game = game;
        this.gamecam = new OrthographicCamera();

        gamecam.setToOrtho(false, 1820 / 100f, 920 / 100f);
        this.gameport = new StretchViewport(1820, 980, gamecam);
        this.catapult = new Texture("slingshot.png");
        this.wld = new World(new Vector2(0, GRAVITY), true);
        this.dbgrndr = new Box2DDebugRenderer();
        shapeRenderer = new ShapeRenderer();
        setupCollisionListener(wld);

        bg = game.assetManager.get("gameplay_background.jpg", Texture.class);
        theme = game.assetManager.get("gameplaymusic.mp3", Music.class);


        Red = new Red(wld, new Vector2(200, 150));
        Chuck = new Chuck(wld, new Vector2(140, 150));
        Bomb = new Bomb(wld, new Vector2(70, 150));

        minion_tex = game.assetManager.get("NormalPig_Healthy.png", Texture.class);
        foreman_tex = game.assetManager.get("MoustachePig_Healthy.png", Texture.class);
        minion = new NormalPigs(wld, new Vector2(1355, 165), minion_tex);
        foreman = new MoustachePig(wld, new Vector2(1355, 432), foreman_tex);
        wood_texture = game.assetManager.get("wood_vertical_stick.png", Texture.class);
        stone_texture = game.assetManager.get("stone_horizontal_stick.png", Texture.class);
        glass_texture = game.assetManager.get("glass_horizontal_stick.png", Texture.class);
        wd_rg = new TextureRegion(wood_texture);

        glass = new Glass(wld, glass_texture, new Vector2(1400, 390), new Vector2(Math.abs(stone_texture.getWidth()), Math.abs(stone_texture.getHeight() - 50)));
        wood1 = new Wood(wld,wood_texture, new Vector2(1300, 252), new Vector2(Math.abs(wood_texture.getWidth() - 90), Math.abs(wood_texture.getHeight() + 20)));
        wood2 = new Wood(wld,wood_texture, new Vector2(1500, 252), new Vector2(Math.abs(wood_texture.getWidth() - 90), Math.abs(wood_texture.getHeight() + 20)));


        birds.add(Red);
        birds.add(Chuck);
        birds.add(Bomb);

        blocks_list.add(glass);
        blocks_list.add(wood1);
        blocks_list.add(wood2);

        pig_list.add(minion);
        pig_list.add(foreman);


        for (Bird bird : birds){
            bird.brdBody.setActive(false);
//            bird.brdBody.setGravityScale(0);
        }
//        for (Block blk : blocks_list){
//            blk.boxbody.setActive(false);
//            blk.boxbody.setGravityScale(0);
//        }
//        for (Pig piggie : pig_list){
//            piggie.pig_bdy.setActive(false);
//        }


        stage = new Stage(new StretchViewport(1820, 980));

        createGroundSlab();


        Texture r = game.assetManager.get("continue.png", Texture.class);
        TextureRegion r1 = new TextureRegion(r);
        TextureRegionDrawable r2 = new TextureRegionDrawable(r1);
        resume = new ImageButton(r2);
        resume.setPosition((gameport.getWorldWidth() - resume.getWidth()) / 2, gameport.getWorldHeight() - resume.getHeight() - 50);

        Texture re = game.assetManager.get("replay.png", Texture.class);
        TextureRegion re1 = new TextureRegion(re);
        TextureRegionDrawable re2 = new TextureRegionDrawable(re1);
        restart = new ImageButton(re2);
        restart.setPosition((gameport.getWorldWidth()) / 2 - 110, 250);

        Texture s = game.assetManager.get("save.png", Texture.class);
        TextureRegion s1 = new TextureRegion(s);
        TextureRegionDrawable s2 = new TextureRegionDrawable(s1);
        save = new ImageButton(s2);
        save.setPosition((gameport.getWorldWidth()) / 2 + 130, 250);

        Texture u = game.assetManager.get("levelsmenu.png", Texture.class);
        TextureRegion u1 = new TextureRegion(u);
        TextureRegionDrawable u2 = new TextureRegionDrawable(u1);
        menu = new ImageButton(u2);
        menu.setPosition((gameport.getWorldWidth()) / 2 - 230, 250);

        Texture b = game.assetManager.get("pausescreen.png", Texture.class);
        TextureRegion b2 = new TextureRegion(b);
        TextureRegionDrawable b3 = new TextureRegionDrawable(b2);
        pausemenu = new ImageButton(b3);
        pausemenu.setPosition((gameport.getWorldWidth() - pausemenu.getWidth()) / 2, 0);
        Texture p = game.assetManager.get("pause.png", Texture.class);
        TextureRegion p1 = new TextureRegion(p);
        TextureRegionDrawable p2 = new TextureRegionDrawable(p1);
        pause = new ImageButton(p2);
        pause.setPosition(30, (gameport.getWorldHeight() - pause.getHeight() - 30));

        Texture mon = game.assetManager.get("musicon.png", Texture.class);
        TextureRegion mon1 = new TextureRegion(mon);
        TextureRegionDrawable mon2 = new TextureRegionDrawable(mon1);
        musicon = new ImageButton(mon2);
        Texture mof = game.assetManager.get("musicoff.png", Texture.class);
        TextureRegion mof1 = new TextureRegion(mof);
        TextureRegionDrawable mof2 = new TextureRegionDrawable(mof1);
        musicoff = new ImageButton(mof2);
        musicon.setPosition((gameport.getWorldWidth()) / 2 + 10, 250);
        musicoff.setPosition((gameport.getWorldWidth()) / 2 + 10, 250);

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


        pause.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (pausemenu.isVisible()) {
                    pausemenu.setVisible(false);
                    resume.setVisible(false);
                    restart.setVisible(false);
                    save.setVisible(false);
                    menu.setVisible(false);
                    musicoff.setVisible(false);
                    musicon.setVisible(false);
                } else {
                    pausemenu.setVisible(true);
                    resume.setVisible(true);
                    restart.setVisible(true);
                    save.setVisible(true);
                    menu.setVisible(true);
                    if (theme.isPlaying())
                        musicon.setVisible(true);
                    else
                        musicoff.setVisible(true);
                }
            }
        });


        musicon.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music button clicked in Options Screen!");
                if (theme.isPlaying()) {
                    theme.pause();
                    musicon.remove();
                    stage.addActor(musicoff);
                }
            }
        });

        musicoff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Music button clicked in Options Screen!");
                if (theme.isPlaying()) {
                    theme.pause();
                } else {
                    theme.play();
                    musicoff.remove();
                    stage.addActor(musicon);
                }
            }
        });

        resume.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                pausemenu.setVisible(false);
                resume.setVisible(false);
                restart.setVisible(false);
                save.setVisible(false);
                menu.setVisible(false);
                musicoff.setVisible(false);
                musicon.setVisible(false);
            }
        });

        restart.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                dispose();
                theme.dispose();
                theme.play();
                pausemenu.setVisible(false);
                resume.setVisible(false);
                restart.setVisible(false);
                save.setVisible(false);
                menu.setVisible(false);
                musicoff.setVisible(false);
                musicon.setVisible(false);
            }
        });

        menu.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new Levels(game, cam, port));
            }
        });

        save.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new Levels(game, cam, port));
            }
        });

        Texture v = game.assetManager.get("victory.png", Texture.class);
        TextureRegion v1 = new TextureRegion(v);
        TextureRegionDrawable v2 = new TextureRegionDrawable(v1);
        victoryscreen = new ImageButton(v2);
        victoryscreen.setPosition((gameport.getWorldWidth() - victoryscreen.getWidth()) / 2, 0);

        Texture n = game.assetManager.get("next.png", Texture.class);
        TextureRegion n1 = new TextureRegion(n);
        TextureRegionDrawable n2 = new TextureRegionDrawable(n1);
        won = new ImageButton(n2);
        won.setPosition(gameport.getWorldWidth() - 130, 30);
        next = new ImageButton(n2);
        next.setPosition((gameport.getWorldWidth()) / 2 + 70, 200);

        menu2 = new ImageButton(u2);
        menu2.setPosition((gameport.getWorldWidth()) / 2 - 170, 200);
        restart2 = new ImageButton(re2);
        restart2.setPosition((gameport.getWorldWidth()) / 2 - 50, 200);
        stage.addActor(won);
        stage.addActor(victoryscreen);
        victoryscreen.setVisible(false);
        stage.addActor(next);
        next.setVisible(false);
        stage.addActor(menu2);
        menu2.setVisible(false);
        stage.addActor(restart2);
        restart2.setVisible(false);

        won.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                if (victoryscreen.isVisible()) {
                    victoryscreen.setVisible(false);
                    menu2.setVisible(false);
                    restart2.setVisible(false);
                    next.setVisible(false);
                } else {
                    victoryscreen.setVisible(true);
                    menu2.setVisible(true);
                    restart2.setVisible(true);
                    next.setVisible(true);
                }
            }
        });

        restart2.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                victoryscreen.setVisible(false);
                menu2.setVisible(false);
                restart2.setVisible(false);
                next.setVisible(false);
            }
        });
        menu2.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new Levels(game, cam, port));
            }
        });
        next.addListener(new ClickListener() {
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new Levels(game, cam, port));
            }
        });

        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(inputMultiplexer);

    }



    public void createGroundSlab() {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(0, 0);
        groundBody = wld.createBody(groundBodyDef);

        EdgeShape groundShape = new EdgeShape();
        groundShape.set(0, 135, stage.getWidth(), 135);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = groundShape;
        fixtureDef.friction = 10.0f;
        fixtureDef.restitution = 0.1f;

        groundBody.createFixture(fixtureDef);
        groundShape.dispose();
    }


    @Override
    public void show() {
        theme.setLooping(true);
//        theme.play();
    }


    public void handleBirdBlockCollision(Bird bird, Block block) {
        float damage = 120;
        block.takeDamage(damage);
//        if (bird.brdBody.getLinearVelocity().y < 0 ) {
            Vector2 bird_vel = bird.brdBody.getLinearVelocity();
            System.out.println("Before change: " + bird_vel);
            bird.brdBody.setLinearVelocity(bird_vel.x / 4, bird_vel.y / 4);
            System.out.println("after change: " + bird.brdBody.getLinearVelocity());

    }

    private void setupCollisionListener(World world) {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Body bodyA = contact.getFixtureA().getBody();
                Body bodyB = contact.getFixtureB().getBody();

                if (bodyA.getUserData() instanceof Pig && bodyB.getUserData() instanceof Block) {
                    handleBlockPigCollision((Pig)bodyA.getUserData(), (Block)bodyB.getUserData());

                } else if (bodyB.getUserData() instanceof Pig && bodyA.getUserData() instanceof Block) {
                    handleBlockPigCollision((Pig)bodyB.getUserData(), (Block)bodyA.getUserData());


                }
                else if (bodyB.getUserData() instanceof Pig && bodyA.getUserData() instanceof Bird) {
                    handleBirdPigCollision((Pig) bodyB.getUserData(), (Bird) bodyA.getUserData());
                }
                else if (bodyB.getUserData() instanceof Pig && bodyA.getUserData() instanceof Bird) {
                    handleBirdPigCollision((Pig) bodyB.getUserData(), (Bird) bodyA.getUserData());
                }
                else if (bodyA.getUserData() instanceof Bird && bodyB.getUserData() instanceof Block) {
                    handleBirdBlockCollision((Bird)bodyA.getUserData(), (Block)bodyB.getUserData());
                }else if (bodyB.getUserData() instanceof Bird && bodyA.getUserData() instanceof Block) {
                    handleBirdBlockCollision((Bird)bodyB.getUserData(), (Block)bodyA.getUserData());}
            }

            @Override
            public void endContact(Contact contact) {}

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {}

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {}
        });
    }


    private void handleBirdPigCollision(Pig pig, Bird bird) {
        float damage = 200;
        pig.takeDamage(damage);
//        if (bird.brdBody.getLinearVelocity().x > 5f && bird.brdBody.getLinearVelocity().y > 5f) {
            Vector2 bird_vel = bird.brdBody.getLinearVelocity();
            System.out.println("Before change: " + bird_vel);
            bird.brdBody.setLinearVelocity(bird_vel.x / 4, bird_vel.y / 4);
            System.out.println("after change: " + bird.brdBody.getLinearVelocity());
//        }
    }

    private void handleBlockPigCollision(Pig pig, Block block) {
        if (block instanceof Glass) {
            pig.takeDamage(20);
        }
        else{
            pig.takeDamage(200);
        }
    }

    @Override
    public void render(float delta) {
        wld.step(delta, 4, 2);
        wld.setGravity(new Vector2(0, -10));
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        game.batch.draw(bg, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());

        Array<Block> blocksToRemove = new Array<>();
        Array<Body> bodyToDestroy = new Array<>();

        for (Block block : blocks_list) {
            block.updateBlockRelationships(blocks_list);
            if (block.needsDestruction || block.isDestroyed || block.boxbody.getPosition().y < 170) {
                blocksToRemove.add(block);
                bodyToDestroy.add(block.boxbody);
            }
            else{
                block.render(game.batch);
                block.boxbody.setGravityScale(100);
            }
        }

        for (Block block : blocksToRemove) {
            blocks_list.remove(block);
        }
        for (Body body : bodyToDestroy) {
            if (body.isActive()) {
                body.setActive(false);
                wld.destroyBody(body);
            }

        }

        game.batch.end();

        game.batch.begin();
        Array<Pig> pigsToRemove = new Array<>();
        Array<Body> pigBodyToDestroy = new Array<>();

        for (Pig piggie : pig_list) {
            piggie.update();
            if (piggie.isDead) {
                pigsToRemove.add(piggie);
                pigBodyToDestroy.add(piggie.pig_bdy);
            } else {
                piggie.render(game.batch);
                piggie.pig_bdy.setActive(true);
                piggie.pig_bdy.setGravityScale(10);
            }
        }

        for (Pig piggie : pigsToRemove) {
            pig_list.remove(piggie);
        }
        for (Body body : pigBodyToDestroy) {
            if (body.isActive()) {
                body.setActive(false);
                wld.destroyBody(body);
            }
        }

        game.batch.end();

        game.batch.begin();
        game.batch.draw(catapult, 200, 130);

        Array<Bird> birdsToRemove = new Array<>();
        Array<Body> birdBodyToDestroy = new Array<>();

        for (Bird bird : birds) {
            if (!isDragging || bird != selectedBird) {
                game.batch.draw(bird.birdModel, bird.brdBody.getPosition().x - 35, bird.brdBody.getPosition().y - 35,
                        catapult.getWidth() - 10, catapult.getHeight() - 120);
            }
        }

        if (isDragging && selectedBird != null) {
            game.batch.draw(selectedBird.birdModel, currentMousePosition.x - 35, currentMousePosition.y - 35,
                    catapult.getWidth() - 10, catapult.getHeight() - 120);
        }

        game.batch.end();

        game.batch.begin();

        if (isDragging && selectedBird != null) {
            shapeRenderer.setProjectionMatrix(gamecam.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            shapeRenderer.setColor(0.6f, 0.3f, 0.1f, 1f);
            shapeRenderer.rectLine(slingshotPosition.x, slingshotPosition.y, currentMousePosition.x, currentMousePosition.y, 6);
            shapeRenderer.rectLine(slingshotPosition.x + 2, slingshotPosition.y, currentMousePosition.x, currentMousePosition.y, 6);

            Vector2 launchVector = slingshotPosition.cpy().sub(currentMousePosition).scl(100);
            Array<Vector2> trajectoryPoints = Catapult.calculateTrajectory(slingshotPosition, launchVector);

            if (trajectoryPoints.size > 0) {
                float alpha = 0.8f;
                float alphaStep = alpha / trajectoryPoints.size;
                for (int i = 0; i < trajectoryPoints.size; i++) {
                    Vector2 point = trajectoryPoints.get(i);
                    shapeRenderer.setColor(0.2f, 0.2f, 0.2f, alpha);
                    float radius = (i < trajectoryPoints.size / 4) ? 5 : (i < trajectoryPoints.size / 2) ? 4 : (i < trajectoryPoints.size / 4 * 3) ? 3 : 2;
                    shapeRenderer.circle(point.x, point.y, radius);
                    alpha -= alphaStep;
                }
            }
            shapeRenderer.end();
        }

        if (selectedBird != null && selectedBird.launched) {
            Vector2 birdPosition = selectedBird.brdBody.getPosition();
            if (birdPosition.x < 0 || birdPosition.x > gameport.getWorldWidth() || birdPosition.y < 0 || birdPosition.y > gameport.getWorldHeight()) {
                birdsToRemove.add(selectedBird);
                birdBodyToDestroy.add(selectedBird.brdBody);
                selectedBird = null;
            } else {
                float tim = delta;
                selectedBird.launchTime += tim;

                float scale = 3f, gravity = -5f;
                float velocityX = selectedBird.brdBody.getLinearVelocity().x > 0
                        ? selectedBird.brdBody.getLinearVelocity().x * 4f
                        : 0;
                float velocityY = selectedBird.brdBody.getLinearVelocity().y * 4f;

                float newX = selectedBird.getPosition().x + velocityX * tim;
                float newY = selectedBird.getPosition().y + velocityY * tim + 0.5f * -50 * 100 * tim * tim;

                selectedBird.setPosition(newX, newY);

                velocityY += -130 * tim;
                selectedBird.brdBody.setLinearVelocity(velocityX, velocityY);

                if (selectedBird.brdBody.getLinearVelocity().x <= 0 && selectedBird.brdBody.getLinearVelocity().y >= 0 || selectedBird.brdBody.getLinearVelocity().x < 40f) {
                    selectedBird.brdBody.setLinearVelocity(0, 0);
                    selectedBird.launched = false;
                    selectedBird.brdBody.setActive(false);
                    wld.destroyBody(selectedBird.brdBody);
                    birds.remove(selectedBird);
                    selectedBird = null;
                } else {
                    for (Contact contact : wld.getContactList()) {
                        if (contact.isTouching()) {
                            Body bodyA = contact.getFixtureA().getBody();
                            Body bodyB = contact.getFixtureB().getBody();
                            if (bodyA == selectedBird.brdBody || bodyB == selectedBird.brdBody) {
                                Vector2 currentVelocity = selectedBird.brdBody.getLinearVelocity();
                                selectedBird.brdBody.setLinearVelocity(currentVelocity.x / 7, currentVelocity.y / 7);
                            }
                        }
                    }
                }
            }

            for (Bird bird : birdsToRemove) {
                birds.remove(bird);
            }
            for (Body body : birdBodyToDestroy) {
                if (body.isActive()) {
                    body.setActive(false);
                    wld.destroyBody(body);
                }
            }
        }

        if (pig_list.isEmpty()) {
            victoryscreen.setVisible(true);
        }

        dbgrndr.render(wld, gamecam.combined);

        game.batch.end();

        stage.act(delta);
        stage.draw();
    }


//    @Override
//    public void render(float delta) {
//        wld.step(delta, 4, 2);
//        wld.setGravity(new Vector2(0, -10));
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        game.batch.setProjectionMatrix(gamecam.combined);
//        game.batch.begin();
//        game.batch.draw(bg, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
//
//        Array<Block> b_2_r = new Array<>();
//        Array<Body> body_2_des = new Array<>();
//
//        for (Block block : blocks_list) {
//            block.updateBlockRelationships(blocks_list);
//
//            if (block.needsDestruction) {
//                b_2_r.add(block);
//                body_2_des.add(block.boxbody);
//            }
//        }
//
//        for (Block blk : blocks_list) {
//            if (!blk.isDestroyed) {
//                blk.render(game.batch);
//                blk.boxbody.setActive(true);
//                blk.boxbody.setGravityScale(130);
//            } else if(blk.boxbody.getPosition().y < 200){
//                b_2_r.add(blk);
//                body_2_des.add(blk.boxbody);
//            }
//            else {
//                b_2_r.add(blk);
//                body_2_des.add(blk.boxbody);
//            }
//        }
//
//        for (Block blk : b_2_r) {
//            blocks_list.remove(blk);
//        }
//
//        for (Body body : body_2_des) {
//            wld.destroyBody(body);
//        }
//
//        game.batch.end();
//
//        game.batch.begin();
//        Array<Pig> pigs_2_r = new Array<>();
//        Array<Body> pig_body_2_des = new Array<>();
//
//        for (Pig piggie : pig_list) {
//            piggie.update();
//            if (piggie.isDead) {
//                pigs_2_r.add(piggie);
//                pig_body_2_des.add(piggie.pig_bdy);
//            } else {
//                piggie.render(game.batch);
//            }
//        }
//
//        for (Pig piggie : pigs_2_r) {
//            pig_list.remove(piggie);
//        }
//
//        for (Body body : pig_body_2_des) {
//            wld.destroyBody(body);
//        }
//        game.batch.end();
//
//        game.batch.begin();
//        game.batch.draw(catapult, 200, 130);
//
//        Array<Bird> birds_2_r = new Array<>();
//        Array<Body> birdvdy_2_d = new Array<>();
//        for (Bird bird : birds) {
//            if (!isDragging || bird != selectedBird) {
//                game.batch.draw(bird.birdModel, bird.brdBody.getPosition().x - 35, bird.brdBody.getPosition().y - 35,
//                        catapult.getWidth() - 10, catapult.getHeight() - 120);
//            }
//        }
//
//        if (isDragging && selectedBird != null) {
//            game.batch.draw(selectedBird.birdModel, currentMousePosition.x - 35, currentMousePosition.y - 35,
//                    catapult.getWidth() - 10, catapult.getHeight() - 120);
//        }
//
//        game.batch.end();
//        game.batch.begin();
//
//
//
//        if (isDragging && selectedBird != null) {
//            shapeRenderer.setProjectionMatrix(gamecam.combined);
//            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//
//            shapeRenderer.setColor(0.6f, 0.3f, 0.1f, 1f);
//            shapeRenderer.rectLine(slingshotPosition.x, slingshotPosition.y, currentMousePosition.x, currentMousePosition.y, 6);
//            shapeRenderer.rectLine(slingshotPosition.x + 2, slingshotPosition.y, currentMousePosition.x, currentMousePosition.y, 6);
//
//            Vector2 launchVector = slingshotPosition.cpy().sub(currentMousePosition).scl(100);
//            Array<Vector2> trajectoryPoints = Catapult.calculateTrajectory(slingshotPosition, launchVector);
//
//            if (trajectoryPoints.size > 0) {
//                float alpha = 0.8f;
//                float alphaStep = alpha / trajectoryPoints.size;
//                for (int i = 0; i < trajectoryPoints.size; i++) {
//                    Vector2 point = trajectoryPoints.get(i);
//                    shapeRenderer.setColor(0.2f, 0.2f, 0.2f, alpha);
//                    if (i < trajectoryPoints.size/4)
//                        shapeRenderer.circle(point.x, point.y, 5);
//                    else if (i < trajectoryPoints.size/4*2)
//                        shapeRenderer.circle(point.x, point.y, 4);
//                    else if (i < trajectoryPoints.size/4*3)
//                        shapeRenderer.circle(point.x, point.y, 3);
//                    else
//                        shapeRenderer.circle(point.x, point.y, 2);
//                    alpha -= alphaStep;}}
//            shapeRenderer.end();}
//
//        if (selectedBird!= null && selectedBird.launched) {
//
//            Vector2 birdPosition = selectedBird.brdBody.getPosition();
//            if (birdPosition.x < 0 || birdPosition.x > gameport.getWorldWidth() || birdPosition.y < 0 || birdPosition.y > gameport.getWorldHeight()) {
//                birds_2_r.add(selectedBird);
//                birdvdy_2_d.add(selectedBird.brdBody);
//                selectedBird = null;
//            } else {
//                float tim = delta;
////            tim = 0.005f;
////            System.out.println(tim);
//                selectedBird.launchTime += tim;
//                //projectile motion
//                float scale = 3f, gravity = -5f;
//                float velocityX = selectedBird.brdBody.getLinearVelocity().x > 0
//                        ? selectedBird.brdBody.getLinearVelocity().x * 4f
//                        : 0;
//                float velocityY = selectedBird.brdBody.getLinearVelocity().y * 4f;
//
//                float newX = selectedBird.getPosition().x + velocityX * tim;
//                float newY = selectedBird.getPosition().y + velocityY * tim + 0.5f * -50 * 100 * tim * tim;
//
//                selectedBird.setPosition(newX, newY);
//
//                velocityY += -130 * tim;
//                selectedBird.brdBody.setLinearVelocity(velocityX, velocityY);
//                if (selectedBird.brdBody.getLinearVelocity().x <= 0 && selectedBird.brdBody.getLinearVelocity().y >= 0 || selectedBird.brdBody.getLinearVelocity().x < 40f ){
//                    selectedBird.brdBody.setLinearVelocity(0,0);
//                    selectedBird.launched = false;
//                    selectedBird.brdBody.setActive(false);
//                    wld.destroyBody(selectedBird.brdBody);
//                    birds.remove(selectedBird);
//                    selectedBird = null;
//                }
//                else{
//                    for (Contact contact : wld.getContactList()) {
//                        if (contact.isTouching()) {
//                            Body bodyA = contact.getFixtureA().getBody();
//                            Body bodyB = contact.getFixtureB().getBody();
//                            // Check if either body is the bird
//                            if (bodyA == selectedBird.brdBody || bodyB == selectedBird.brdBody) {
//                                System.out.println("detected");
//                                Vector2 currentVelocity = selectedBird.brdBody.getLinearVelocity();
//                                System.out.println("before: " + currentVelocity);
//                                selectedBird.brdBody.setLinearVelocity(currentVelocity.x / 7, currentVelocity.y / 7);
//                                System.out.println("After: " + selectedBird.brdBody.getLinearVelocity());
//                            }
//                        }
//                    }
//                }
//            }
//            for (Bird bird : birds_2_r) {
//                birds.remove(bird);
//            }
//
//            for (Body body : birdvdy_2_d) {
//                wld.destroyBody(body);
//            }
//        }
//
//        if (pig_list.isEmpty()){
//            victoryscreen.setVisible(true);}
//
//        dbgrndr.render(wld, gamecam.combined);
//
//        game.batch.end();
//
//        stage.act(delta);
//        stage.draw();
//    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 worldPosition = gamecam.unproject(new Vector3(screenX, screenY, 0));

        for (Bird bird : birds) {
            if (bird.getBounds().contains(worldPosition.x, worldPosition.y)) {
                isDragging = true;
                selectedBird = bird;

                selectedBird.setPosition(slingshotPosition.x, slingshotPosition.y);

                dragStartPosition.set(slingshotPosition);
                currentMousePosition.set(dragStartPosition);
                break;
            }
        }

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isDragging && selectedBird != null) {
            Vector3 worldCoordinates = gamecam.unproject(new Vector3(screenX, screenY, 0));
            currentMousePosition.set(worldCoordinates.x, worldCoordinates.y);

            if (currentMousePosition.dst(slingshotPosition) > 100) {
                Vector2 direction = currentMousePosition.cpy().sub(slingshotPosition).nor();
                currentMousePosition.set(slingshotPosition).add(direction.scl(100));
            }

            selectedBird.setPosition(currentMousePosition.x, currentMousePosition.y);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isDragging && selectedBird != null) {
            selectedBird.brdBody.setGravityScale(1);
            Vector3 worldCoordinates = gamecam.unproject(new Vector3(screenX, screenY, 0));
            Vector2 releaseVelocity = slingshotPosition.cpy().sub(currentMousePosition).scl(3);  // Stretch multiplier

            selectedBird.brdBody.setLinearVelocity(releaseVelocity);
            selectedBird.brdBody.setActive(true);

//            for (Pig piggie : pig_list){
//                piggie.pig_bdy.setActive(true);
//                piggie.pig_bdy.setGravityScale(130);
//            }
            selectedBird.launched = true;

            isDragging = false;
        }
        return true;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }


    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        gamecam.update();
        stage.getViewport().update(width, height, true);}

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }



    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }


    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        theme.dispose();
    }
}
