import org.junit.Test;

import static org.junit.Assert.*;

public class GameStateTest {
    @Test
    public void testGameState() {
        GameState gameState = GameState.PLAYER1MOVE;
        assertEquals("PLAYER1MOVE", gameState.toString());
    }

    @Test
    public void testGameState2() {
        GameState gameState = GameState.PLAYER2MOVE;
        assertEquals("PLAYER2MOVE", gameState.toString());
    }

    @Test
    public void testGameState3() {
        GameState gameState = GameState.PLAYER1PLACE;
        assertEquals("PLAYER1PLACE", gameState.toString());
    }

}