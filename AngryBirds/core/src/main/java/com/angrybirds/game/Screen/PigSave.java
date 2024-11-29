package com.angrybirds.game.Screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PigSave {
    public String name;
    public Vector2 position;
    public float currentHp;

    public PigSave(String name, Vector2 position, float currentHp) {
        this.name = name;
        this.position = position;
        this.currentHp = currentHp;}
}
