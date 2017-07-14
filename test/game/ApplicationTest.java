package game;

import org.junit.Test;

import static game.Application.isCorrectMove;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ApplicationTest {
    @Test
    public void shouldReturnPassCorrectMove() {
        // when
        boolean move = isCorrectMove('w');

        // then
        assertTrue(move);
    }

    @Test
    public void shouldNotPassWrongMove() {
        // when
        boolean move = isCorrectMove('c');

        // then
        assertFalse(move);
    }
}
