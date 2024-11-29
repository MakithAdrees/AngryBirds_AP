package com.angrybirds.game.Pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class HelmetPig extends Pig {

    public HelmetPig(World wrld, Vector2 position, Texture PigModel) {
        super(wrld, position, PigModel, "hellie");
    }

    public HelmetPig(Vector2 position){
        super(position, "hellie");}
}
