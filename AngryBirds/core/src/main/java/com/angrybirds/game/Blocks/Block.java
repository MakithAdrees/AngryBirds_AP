package com.angrybirds.game.Blocks;

import com.angrybirds.game.Birds.Bird;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

abstract public class Block {
    //    protected String Material_Name;
    public float Hp;
    public Texture Block_Texture;
    public Vector2 dimension;
    public Vector2 position;
    public Body boxbody;
    public static final float PIXELS_TO_METERS = 100f;
    public float maxHp;
    public float currentHp;
    public boolean isDestroyed = false;
    public ArrayList<Block> blocksAbove = new ArrayList<>();
    public boolean needsDestruction = false;
    public World world;


    public Block(World world, Texture Block_Texture, Vector2 position, Vector2 dimension) {
        this.dimension = dimension;
        this.world = world;
        this.Block_Texture = Block_Texture;
        this.position = position;
        createBoxBody(world, position, dimension);
        this.maxHp = calculateMaterialHp();
        this.currentHp = maxHp;}

    private float calculateMaterialHp() {
        if (this instanceof Wood) return 120;
        if (this instanceof Glass) return 50;
        if (this instanceof Stone) return 200;
        return 0;}

    public void takeDamage(float damage) {
        currentHp -= damage;
        if (currentHp <= 0) {
            isDestroyed = true;
            needsDestruction = true;
            collapsedamage();}}
//        if (currentHp < maxHp/2){
//            this.Block_Texture = new Texture("cracked_" + this.Block_Texture.toString());}}

    private void collapsedamage() {
        float collapseDamage = 100 ;

        for (Block blockAbove : blocksAbove) {
            if (!blockAbove.isDestroyed) {
                blockAbove.takeDamage(collapseDamage);
            }
        }
    }

    public void updateBlockRelationships(ArrayList<Block> blocks_list) {
        blocksAbove.clear();
        Vector2 myPos = getPosition();

        float verticalRange = dimension.y * 2;   // Define how far above to check vertically
        float horizontalRange = dimension.x * 1.5f; // Define how far to check horizontally

        for (Block other : blocks_list) {
            if (other != this && !other.isDestroyed) {
                Vector2 otherPos = other.getPosition();

                // Check if the block is within the defined coordinate range
                if (Math.abs(otherPos.x - myPos.x) <= horizontalRange && // Horizontal range
                        (otherPos.y > myPos.y && otherPos.y <= myPos.y + verticalRange)) { // Vertical range

                    blocksAbove.add(other); // Add to blocksAbove list
                }
            }
        }
    }



//
//    private void collapsedamage() {
//        for (Block blockAbove : blocksAbove) {
//            blockAbove.takeDamage(100);
//        }
//    }
//
//    private void setupCollisionListener(World world) {
//        world.setContactListener(new ContactListener() {
//            @Override
//            public void beginContact(Contact contact) {
//                Body bodyA = contact.getFixtureA().getBody();
//                Body bodyB = contact.getFixtureB().getBody();
//
//                if (bodyA.getUserData() instanceof Bird && bodyB.getUserData() instanceof Block) {
//                    handleBirdBlockCollision((Bird)bodyA.getUserData(), (Block)bodyB.getUserData());
//                } else if (bodyB.getUserData() instanceof Bird && bodyA.getUserData() instanceof Block) {
//                    handleBirdBlockCollision((Bird)bodyB.getUserData(), (Block)bodyA.getUserData());
//                }
//            }
//
//            @Override
//            public void endContact(Contact contact) {}
//
//            @Override
//            public void preSolve(Contact contact, Manifold oldManifold) {}
//
//            @Override
//            public void postSolve(Contact contact, ContactImpulse impulse) {}
//        });
//    }



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

    public void render(SpriteBatch batch) {
        if (isDestroyed || boxbody == null){
            this.dispose();
            return;
        }
        float x = boxbody.getPosition().x ;
        float y = boxbody.getPosition().y ;

        float rotation = boxbody.getAngle() * MathUtils.radiansToDegrees;

        batch.draw(Block_Texture, x - dimension.x / 2, y - dimension.y / 2, dimension.x / 2, dimension.y / 2, dimension.x, dimension.y, 1, 1, rotation, 0, 0, Block_Texture.getWidth(), Block_Texture.getHeight(), false, false
        );
    }


    public Vector2 getPosition() {
        Vector2 pos = boxbody.getPosition();
        return new Vector2(pos.x , pos.y );
    }

    public void dispose(){}
}

