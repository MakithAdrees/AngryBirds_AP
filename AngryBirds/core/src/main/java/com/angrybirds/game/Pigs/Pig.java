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
    protected Body p_bdy;
    public Texture hurt;
    public Texture healthy;

    public Pig(float initialHp, float size, Texture initialTexture, Texture hurt) {
        this.Hp = initialHp;
        this.size = size;
        this.PigModel = initialTexture;
        this.hurt = hurt;
        this.healthy = initialTexture;
    }
    public void Damage_Taken(float damage) {
        Hp -= damage;
        if (Hp <= 0) {
            PigRIP();
        } else {
            Change_Pig_Image();
        }
    }
    public void PigRIP() {
        System.out.println("Pig is dead!");
        // Dispose of the pig's texture or play a death sound
    }

    public void Change_Pig_Image() {
        if (Hp < 50) {
            PigModel = hurt;
        } else {
            PigModel = healthy;
        }
    }

    public void setPigBody(World world, Vector2 position) {
        BodyDef bdy_def = new BodyDef();
        bdy_def.type = BodyDef.BodyType.DynamicBody;
        bdy_def.position.set(position);
        p_bdy = world.createBody(bdy_def);

        CircleShape shape = new CircleShape();
        shape.setRadius(size);

        FixtureDef f_def = new FixtureDef();
        f_def.shape = shape;
        f_def.density = 1.0f;
        f_def.restitution = 0.3f;
        f_def.friction = 0.6f;

        p_bdy.createFixture(f_def);
        shape.dispose();
    }

    public void dispose() {
        if (PigModel != null) {
            PigModel.dispose();
        }
    }
}
