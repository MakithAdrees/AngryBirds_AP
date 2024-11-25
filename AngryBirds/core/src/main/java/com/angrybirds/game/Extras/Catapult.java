package com.angrybirds.game.Extras;

import com.angrybirds.game.Birds.Bird;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class Catapult {
    static public Vector2 position = new Vector2(250, 290);
    static public Texture texture = new Texture("slingshot.png");


    static public Array<Vector2> calculateTrajectory(Vector2 start, Vector2 velocity) {
        Array<Vector2> points = new Array<>();
        float timeStep = 0.2f;
        float maxTime = 3.0f;
        float x = start.x * 100;
        float y = start.y * 100;
        float vx = velocity.x * 3;
        float vy = velocity.y * 3;

        for (float t = 0; t <= maxTime; t += timeStep) {
            float xPos = x + vx*t;
            float yPos = y + vy*t + 0.5f*-50*100*t*t;
            points.add(new Vector2(xPos / 100f, yPos / 100f));}
        return points;}

}