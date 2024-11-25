package com.angrybirds.game.Pigs;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

abstract public class Pig {
    protected float size;
    protected float Hp;
    public Texture PigModel;
    protected Music PigSound;
    public Body pig_bdy;
    public Texture hurt;
    public Texture healthy;
    public Vector2 position;
    public World wrld;
    private String name;

    public Pig(World wrld, Vector2 position, Texture PigModel, String name) {
        this.wrld = wrld;
        this.PigModel = PigModel;
        this.position = position;
        this.name = name;
        setPigBody(wrld, position);

    }
//    public void Damage_Taken(float damage) {
//        Hp -= damage;
//        if (Hp <= 0) {
//            PigRIP();
//        } else {
//            Change_Pig_Image();
//        }
//    }
    public void PigRIP() {
        System.out.println("Pig is dead!");
        // Dispose of the pig's texture or play a death sound
    }

//    public void Change_Pig_Image() {
//        if (Hp < 50) {
//            PigModel = hurt;
//        } else {
//            PigModel = healthy;
//        }
//    }

    public void setPigBody(World world, Vector2 position) {
        BodyDef bdy_def = new BodyDef();
        bdy_def.type = BodyDef.BodyType.DynamicBody;
        bdy_def.position.set(position);
        pig_bdy = world.createBody(bdy_def);

        CircleShape shape = new CircleShape();
        shape.setRadius(38);

        FixtureDef f_def = new FixtureDef();
        f_def.shape = shape;
        f_def.density = 1.0f;
        f_def.restitution = 0.3f;
        f_def.friction = 0.6f;

        pig_bdy.createFixture(f_def);
        shape.dispose();
    }

    public void dispose() {
        if (PigModel != null) {
            PigModel.dispose();
        }
    }


}
