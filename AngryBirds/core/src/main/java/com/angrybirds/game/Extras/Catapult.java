package com.angrybirds.game.Extras;

import com.angrybirds.game.Birds.Bird;
import com.angrybirds.game.Main;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Catapult {
    static public float Velocity;
    static public Texture CatapultModel = new Texture("slingshot.png");
    static private Music CatapultSound;

    static public void Place_Catapult(Main game){
        game.batch.draw(CatapultModel, 180, 130, CatapultModel.getWidth() + 20, CatapultModel.getHeight() + 20);}

    static public void Bird_menu(ArrayList<Bird> available_birds) {
        return;}

    static public void Bird_Launch(Bird bird){
        return;}

    static public void Aim(){
        return;}
}
