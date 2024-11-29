import com.angrybirds.game.Birds.*;
import com.angrybirds.game.Main;
import com.angrybirds.game.Pigs.*;
import com.angrybirds.game.Screen.Level1;
import com.badlogic.gdx.math.Vector2;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestingAngryBirds {

    @Test
    public void birdInitialisation(){
        PrintStream orig = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        Blues blue = new Blues(new Vector2(125, 392));
        Bomb bomb = new Bomb(new Vector2(175, 392));
        Terrance terrance = new Terrance(new Vector2(225, 392));

        System.setOut(orig);
        assert output.toString().contains("bird was created") : "Some error in Angry Birds initialisation";}

    @Test
    public void pigInitialisation(){
        PrintStream orig = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        HelmetPig pig1 = new HelmetPig(new Vector2(200, 392));
        KingPig pig2 = new KingPig(new Vector2(250, 392));
        NormalPigs pig3 = new NormalPigs(new Vector2(300, 392));

        System.setOut(orig);
        assert output.toString().contains("pig was created") : "Some error in Pig initialisation";}

    @Test
    public void winningTest() {
        PrintStream orig = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //birds
        ArrayList<Bird> birds = new ArrayList<>();
        Red red = new Red(new Vector2(125, 392));
        Chuck chuck = new Chuck(new Vector2(175, 392));
        birds.add(red);
        birds.add(chuck);

        //pigs
        ArrayList<Pig> pigs = new ArrayList<>();

        Level1.victory(birds, pigs);
        System.setOut(orig);
        assert output.toString().contains("Level 1 Cleared") : "Some error in winning condition";}

    @Test
    public void losingTest() {
        PrintStream orig = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        //birds
        ArrayList<Bird> birds = new ArrayList<>();

        //pigs
        ArrayList<Pig> pigs = new ArrayList<>();
        HelmetPig pig1 = new HelmetPig(new Vector2(200, 392));
        KingPig pig2 = new KingPig(new Vector2(250, 392));
        NormalPigs pig3 = new NormalPigs(new Vector2(300, 392));
        pigs.add(pig1);
        pigs.add(pig2);
        pigs.add(pig3);
        Level1.defeat(birds, pigs);
        System.setOut(orig);
        assert output.toString().contains("Level 1 Failed") : "Some error in losing condition";}

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("TestingAngryBirds");
    }

}
