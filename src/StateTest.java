import org.junit.Test;

import static org.junit.Assert.*;

public class StateTest {

        @Test
        public void testState() {
            State state = State.EMPTY;
            assertEquals("EMPTY", state.toString());
        }

        @Test
        public void testState2() {
            State state = State.PLAYER1;
            assertEquals("PLAYER1", state.toString());
        }

        @Test
        public void testState3() {
            State state = State.PLAYER2;
            assertEquals("PLAYER2", state.toString());
        }

        @Test
        public void testState4() {
            State state = State.V_LINE;
            assertEquals("V_LINE", state.toString());
        }

        @Test
        public void testState5() {
            State state = State.H_LINE;
            assertEquals("H_LINE", state.toString());
        }

        @Test
        public void testState6() {
            State state = State.X;
            assertEquals("X", state.toString());
        }

}