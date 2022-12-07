import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerDetailsTest {

        @Test
        public void testPlayerDetails() {
            PlayerDetails playerDetails = new PlayerDetails("Test");
            assertEquals("Test", playerDetails.name);
            assertEquals(0, playerDetails.piecesOnBoard);
            assertEquals(0, playerDetails.killedPieces);
        }

        @Test
        public void testPlayerDetails2() {
            PlayerDetails playerDetails = new PlayerDetails();
            assertEquals("Player", playerDetails.name);
            assertEquals(0, playerDetails.piecesOnBoard);
            assertEquals(0, playerDetails.killedPieces);
        }

}