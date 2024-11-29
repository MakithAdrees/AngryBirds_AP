import com.angrybirds.game.Main;
import org.junit.Test;

public class TestingAngryBirds {

    @Test
    public void testMain() {
        Main main = new Main();
        main.create();
        main.render();
        main.dispose();}

}
