package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovesHelperTest {

    @Test
    public void shouldGetUpMoveOnUpDirection() {
        // given
        MovesHelper movesHelper = new MovesHelper();

        // when
        Point point = movesHelper.getMove('w');

        // then
        assertEquals(new Point(0, -1), point);
    }

    @Test
    public void shouldGetDownMoveOnUpDirection() {
        // given
        MovesHelper movesHelper = new MovesHelper();

        // when
        Point point = movesHelper.getMove('s');

        // then
        assertEquals(new Point(0, 1), point);
    }

    @Test
    public void shouldGetRightMoveOnUpDirection() {
        // given
        MovesHelper movesHelper = new MovesHelper();

        // when
        Point point = movesHelper.getMove('d');

        // then
        assertEquals(new Point(1, 0), point);
    }

    @Test
    public void shouldGetLeftMoveOnUpDirection() {
        // given
        MovesHelper movesHelper = new MovesHelper();

        // when
        Point point = movesHelper.getMove('a');

        // then
        assertEquals(new Point(-1, 0), point);
    }

    @Test
    public void shouldNotGetMove() {
        // given
        MovesHelper movesHelper = new MovesHelper();

        // when
        Point point = movesHelper.getMove(' ');

        // then
        assertEquals(new Point(0, 0), point);
    }
}