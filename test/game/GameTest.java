package game;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void shouldGetCharacters() {
        // given
        Game game = new Game(10, 10);

        // when
        List characters = game.getCharacters();

        // then
        assertEquals(4, characters.size());
    }
}