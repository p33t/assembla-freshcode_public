package codechef.numbgame;

import org.junit.Test;

import static codechef.numbgame.NumbGame.play;
import static org.junit.Assert.assertEquals;

public class NumbGameTest {
    @Test
    public void playTest() {
        int actual = play(100000, 27);
        assertEquals(actual, 6);
    }
}
