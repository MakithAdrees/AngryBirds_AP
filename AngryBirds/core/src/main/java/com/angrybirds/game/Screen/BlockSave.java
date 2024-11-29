package com.angrybirds.game.Screen;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class BlockSave {
    public String texture;
    public Vector2 position;
    public Vector2 dimension;
    public float currentHp;

    public BlockSave(String texture, Vector2 position, Vector2 dimension, float currentHp) {
        this.texture = texture;
        this.position = position;
        this.dimension = dimension;
        this.currentHp = currentHp;}
}
