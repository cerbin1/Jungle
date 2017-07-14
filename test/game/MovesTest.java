package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovesTest {

    @Test
    public void shouldGetUpMoveOnUpDirection() {
        // given
        Moves moves = new Moves();

        // when
       int[] array = moves.getMove('w');

        // then
        assertArrayEquals(new int[]{0, -1}, array);
    }

    @Test
    public void shouldNotGetNeMove() {
        // given
        Moves moves = new Moves();

        // when
        int[] array = moves.getMove(' ');

        // then
        assertArrayEquals(new int[]{0, 0}, array);
    }
}