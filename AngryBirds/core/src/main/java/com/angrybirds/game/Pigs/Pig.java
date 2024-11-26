package com.angrybirds.game.Pigs;

import com.angrybirds.game.Birds.Bird;
import com.angrybirds.game.Blocks.Block;
import com.angrybirds.game.Blocks.Glass;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;


abstract public class Pig {
    protected float size;
    protected float maxHp;
    protected float currentHp;
    public Texture PigModel;
    protected Music PigSound;
    public Body pig_bdy;
    public Texture hurt;
    public Texture healthy;
    public Vector2 position;
    public World wrld;
    private String name;
    public boolean isDead = false;
    public Texture catapult;

    public Pig(World wrld, Vector2 position, Texture PigModel, String name) {
        this.wrld = wrld;
        this.PigModel = PigModel;
        this.position = position;
        this.name = name;
        setPigBody(wrld, position);
        this.maxHp = initializePigHp();
//        setupCollisionListener(wrld);
        this.currentHp = maxHp;

        this.catapult = new Texture("slingshot.png");
    }

    private int initializePigHp() {
        if (this instanceof NormalPigs) return 50;
        else if (this instanceof MoustachePig) return 100;
        else if (this instanceof HelmetPig) return  90;
        else if (this instanceof KingPig) return 130;
        else return  100;

    }


    public void takeDamage(float damage) {
        currentHp -= damage;
        if (currentHp <= 0) {
            isDead = true;
            PigRIP();
        }
    }

//    public void handleBirdBlockCollision(Bird bird, Block block) {
//        float damage = 120;
//        block.takeDamage(damage);
////        if (bird.brdBody.getLinearVelocity().x > 5f && bird.brdBody.getLinearVelocity().y > 5f ){
////            Vector2 bird_vel = bird.brdBody.getLinearVelocity();
////            System.out.println("Before change: " + bird_vel);
////            bird.brdBody.setLinearVelocity(bird_vel.x / 2, bird_vel.y / 2);
////            System.out.println("after change: " + bird.brdBody.getLinearVelocity());
////        }
//    }
//
//    private void setupCollisionListener(World world) {
//        world.setContactListener(new ContactListener() {
//            @Override
//            public void beginContact(Contact contact) {
//                Body bodyA = contact.getFixtureA().getBody();
//                Body bodyB = contact.getFixtureB().getBody();
//
//                if (bodyA.getUserData() instanceof Pig && bodyB.getUserData() instanceof Block) {
//                    handleBlockPigCollision((Pig)bodyA.getUserData(), (Block)bodyB.getUserData());
//
//                } else if (bodyB.getUserData() instanceof Pig && bodyA.getUserData() instanceof Block) {
//                    handleBlockPigCollision((Pig)bodyB.getUserData(), (Block)bodyA.getUserData());
//
//                }
//                else if (bodyB.getUserData() instanceof Pig && bodyA.getUserData() instanceof Bird) {
//                    handleBirdPigCollision((Pig) bodyB.getUserData(), (Bird) bodyA.getUserData());
//                }
//                else if (bodyB.getUserData() instanceof Pig && bodyA.getUserData() instanceof Bird) {
//                    handleBirdPigCollision((Pig) bodyB.getUserData(), (Bird) bodyA.getUserData());
//                }
//                else if (bodyA.getUserData() instanceof Bird && bodyB.getUserData() instanceof Block) {
//                    handleBirdBlockCollision((Bird)bodyA.getUserData(), (Block)bodyB.getUserData());
//                }else if (bodyB.getUserData() instanceof Bird && bodyA.getUserData() instanceof Block) {
//                    handleBirdBlockCollision((Bird)bodyB.getUserData(), (Block)bodyA.getUserData());}
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
//
//
//    private void handleBirdPigCollision(Pig pig, Bird bird) {
//        float damage = 200;
//        pig.takeDamage(damage);
////        if (bird.brdBody.getLinearVelocity().x > 5f && bird.brdBody.getLinearVelocity().y > 5f) {
////            Vector2 bird_vel = bird.brdBody.getLinearVelocity();
////            System.out.println("Before change: " + bird_vel);
////            bird.brdBody.setLinearVelocity(bird_vel.x / 2, bird_vel.y / 2);
////            System.out.println("after change: " + bird.brdBody.getLinearVelocity());
////        }
//    }
//
//    private void handleBlockPigCollision(Pig pig, Block block) {
//        if (block instanceof Glass) {
//            pig.takeDamage(20);
//        }
//        else{
//            pig.takeDamage(200);
//        }
//    }


    public void PigRIP() {
        System.out.println(name + " Pig is dead!");
    }

    public void setPigBody(World world, Vector2 position) {
        BodyDef bdy_def = new BodyDef();
        bdy_def.type = BodyDef.BodyType.DynamicBody;
        bdy_def.position.set(position);
        pig_bdy = world.createBody(bdy_def);

        CircleShape shape = new CircleShape();
        shape.setRadius(38);

        FixtureDef f_def = new FixtureDef();
        f_def.shape = shape;
        f_def.density = 0.01f;
        f_def.restitution = 0.0f;
        f_def.friction = 0.0f;

//        f_def.filter.categoryBits = 0x0002; // Pig category

        pig_bdy.createFixture(f_def);
        pig_bdy.setUserData(this);

        pig_bdy.setAngularDamping(0.8f);
        pig_bdy.setLinearDamping(0.2f);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        if (isDead) {
            return;
        } else {
            batch.draw(PigModel, pig_bdy.getPosition().x - 40, pig_bdy.getPosition().y - 40, catapult.getWidth() - 5, catapult.getHeight() - 110);
        }
    }

    public void update() {
        float fallVelocityThreshold = -0.01f;

        if (pig_bdy.getPosition().y <= 150 &&
                pig_bdy.getLinearVelocity().y <= fallVelocityThreshold) {
            isDead = true;
            PigRIP();
        }
    }



    public void dispose() {
        if (PigModel != null) {
            PigModel.dispose();
        }
    }
}
