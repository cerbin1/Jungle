package game;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CharactersGeneratorTest {
    private CharactersGenerator getCharactersGenerator() {
        return new CharactersGenerator(new Board(10, 10, new Game(10, 10)));
    }

    @Test
    public void shouldGeneratePlayer() {
        // given
        CharactersGenerator generator = getCharactersGenerator();

        // when
        Player player = generator.generatePlayer();

        // then
        assertTrue(player != null);
    }

    @Test
    public void shouldGenerateBoar() {
        // given
        CharactersGenerator generator = getCharactersGenerator();

        // when
        Boar boar = generator.generateBoar();

        // then
        assertTrue(boar != null);
    }

    @Test
    public void shouldGenerateTortoise() {
        // given
        CharactersGenerator generator = getCharactersGenerator();

        // when
        Tortoise tortoise = generator.generateTortoise();

        // then
        assertTrue(tortoise != null);
    }

    @Test
    public void shouldGenerateHare() {
        // given
        CharactersGenerator generator = getCharactersGenerator();

        // when
        Hare hare = generator.generateHare();

        // then
        assertTrue(hare != null);
    }

}
