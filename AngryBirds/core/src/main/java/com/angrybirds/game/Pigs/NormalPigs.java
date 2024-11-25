package com.angrybirds.game.Pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class NormalPigs extends Pig {
    public NormalPigs(World wrld, Vector2 position, Texture PigModel) {
        super(wrld, position, PigModel, "Normie");
    }

    @Override
    public void PigRIP() {
        System.out.println("Normal pig has died!");
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
