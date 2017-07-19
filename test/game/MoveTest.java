package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class MoveTest {

    @Test
    public void shouldGetUpMoveOnUpDirection() {
        // given
        Move move = new Move();

        // when
        Point point = move.fromChar('w');

        // then
        assertEquals(new Point(0, -1), point);
    }

    @Test
    public void shouldGetDownMoveOnUpDirection() {
        // given
        Move move = new Move();

        // when
        Point point = move.fromChar('s');

        // then
        assertEquals(new Point(0, 1), point);
    }

    @Test
    public void shouldGetRightMoveOnUpDirection() {
        // given
        Move move = new Move();

        // when
        Point point = move.fromChar('d');

        // then
        assertEquals(new Point(1, 0), point);
    }

    @Test
    public void shouldGetLeftMoveOnUpDirection() {
        // given
        Move move = new Move();

        // when
        Point point = move.fromChar('a');

        // then
        assertEquals(new Point(-1, 0), point);
    }

    @Test
    public void shouldNotGetMove() {
        // given
        Move move = new Move();

        // when
        Point point = move.fromChar(' ');

        // then
        assertEquals(new Point(0, 0), point);
    }
}