import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class GameWindowTest {
    @Test
    public void testIsBlack() {
        Color black = Color.BLACK;
        assertTrue(GameWindow.isBlack(black));
    }

    @Test
    public void testIsWhiteWithRed() {
        Color red = Color.RED;
        assertFalse(GameWindow.isWhite(red));
    }

    @Test
    public void testIsBlackWithRed() {
        Color red = Color.RED;
        assertFalse(GameWindow.isBlack(red));
    }

    @Test
    public void testIsWhite() {
        Color white = Color.WHITE;
        assertTrue(GameWindow.isWhite(white));
    }
}