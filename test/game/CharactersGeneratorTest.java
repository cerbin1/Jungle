package game;

import game.factory.PlayerFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CharactersGeneratorTest {
    private CharactersGenerator getCharactersGenerator() {
        return new CharactersGenerator(new Board(10, 10, new Game(10, 10)));
    }

    @Test
    public void shouldGenerate() {
        // given
        CharactersGenerator generator = getCharactersGenerator();

        // when
        Character character = generator.generate(new PlayerFactory());

        // then
        assertTrue(character != null);
    }
}
