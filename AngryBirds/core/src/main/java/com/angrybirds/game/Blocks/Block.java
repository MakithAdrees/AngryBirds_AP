package com.angrybirds.game.Blocks;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

abstract public class Block {
//    protected String Material_Name;
    public float Hp;
    public Texture Block_Texture;
    private Music Breaking_Sound;
    public Vector2 dimension;
    public Vector2 position;
    public Body boxbody;

    public Block(World world, Texture Block_Texture, Vector2 position, Vector2 dimension ) {
//        this.Material_Name = Material_Name;
//        this.Block_Texture = Block_Texture;
        this.dimension = dimension;
//        this.Hp = Hp;
        this.Block_Texture = Block_Texture;
        this.position = position;
//        this.Breaking_Sound = Breaking_Sound
        createBoxBody(world, position, dimension);
    }

    private void createBoxBody(World world, Vector2 position, Vector2 dimension) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(position);

        boxbody = world.createBody(bodyDef);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(dimension.x / 2, dimension.y/ 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.2f;

        boxbody.createFixture(fixtureDef);
        boxbody.setUserData(this);
        shape.dispose();
    }

    public Vector2 getPosition() {
        return boxbody.getPosition();
    }
}

