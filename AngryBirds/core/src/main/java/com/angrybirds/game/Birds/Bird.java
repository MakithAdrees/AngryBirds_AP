package com.angrybirds.game.Birds;

import com.angrybirds.game.Blocks.*;
import com.angrybirds.game.Pigs.Pig;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

abstract public class Bird {
    protected int mass;
    protected String name;
    public Texture birdModel;
    public Music birdSound;
    static public ArrayList<Bird> birdList = new ArrayList<>();
    public Body brdBody;
    public boolean launched = false;
    public static final float GRAVITY = -9.8f * 1000;

    public Vector2 velocity = new Vector2(0, 0);
    public Vector2 position;
    public float launchTime = 0;

    public Bird(World world, Vector2 position, Texture birdModel, int mass, String name) {
        this.name = name;
        this.mass = mass;
        this.birdModel = birdModel;
        this.position = position;
        createBirdBody(world, position);
    }

    private void createBirdBody(World world, Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);

        brdBody = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(20);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.restitution = 0.3f;
        fixtureDef.friction = 0.2f;

        brdBody.createFixture(fixtureDef);
        shape.dispose();

        brdBody.setUserData(this);

        birdList.add(this);
    }

    public float calculateDamage() {
        return mass * velocity.len(); // Mass * speed (magnitude of velocity)
    }

    public Vector2 getPosition() {
        return brdBody.getPosition();
    }

    public void setPosition(float x, float y) {
        this.position.set(x, y);
        brdBody.setTransform(position, brdBody.getAngle());
    }


    public Rectangle getBounds() {
        return new Rectangle(brdBody.getPosition().x - 20, brdBody.getPosition().y - 20, 20 * 2, 20 * 2);
    }

    public void dispose(){

    }
}
