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
    private int radius;
    public float damage;

    public Bird(World world, Vector2 position, Texture birdModel, int mass, String name, int radius) {
        this.name = name;
        this.mass = mass;
        this.birdModel = birdModel;
        this.position = position;
        this.radius = radius;
        this.damage = calculate_bird_damage();
        createBirdBody(world, position);}

    public Bird(Vector2 position, int mass, String name, int radius) {
        this.name = name;
        this.mass = mass;
        this.position = position;
        this.radius = radius;
        this.damage = calculate_bird_damage();
        System.out.println(name + "bird was created!");}

    private float calculate_bird_damage() {
        if (this instanceof Red) return 95;
        if (this instanceof Chuck) return 100;
        if (this instanceof Bomb) return 105;
        if (this instanceof Terrance) return 150;
        if (this instanceof Blues) return 80;
        return 0;}

    private void createBirdBody(World world, Vector2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(position);

        brdBody = world.createBody(bodyDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

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

    public String getName() {
        return name;}
}
