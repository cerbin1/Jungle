package game;

import org.junit.Test;

import static game.Application.isCorrectMove;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTest {
    @Test
    public void shouldReturnPassCorrectMove() {
        // given
        boolean move;

        // when
        move = isCorrectMove('w');

        // then
        assertTrue(move);
    }

    @Test
    public void shouldNotPassWrongMove() {
        // given
        boolean move;

        // when
        move = isCorrectMove('c');

        // then
        assertFalse(move);
    }

}