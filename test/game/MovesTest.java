package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovesTest {

    @Test
    public void shouldGetUpMoveOnUpDirection() {
        // given
        Moves moves = new Moves();

        // when
        Point point = moves.getMove('w');

        // then
        assertEquals(new Point(0, -1), point);
    }

    @Test
    public void shouldNotGetNeMove() {
        // given
        Moves moves = new Moves();

        // when
        Point point = moves.getMove(' ');

        // then
        assertEquals(new Point(0, 0), point);
    }
}