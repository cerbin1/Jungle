package game;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CharactersGeneratorTest {
    @Test
    public void shouldGeneratePlayer() {
        // given
        CharactersGenerator generator = new CharactersGenerator(new Board(10, 10, new ArrayList<>()));

        // when
        Player player = generator.generatePlayer();

        // then
        assertTrue(player != null);
    }

    @Test
    public void shouldGenerateBoar() {
        // given
        CharactersGenerator generator = new CharactersGenerator(new Board(10, 10, new ArrayList<>()));

        // when
        Boar boar = generator.generateBoar();

        // then
        assertTrue(boar != null);
    }

    @Test
    public void shouldGenerateTortoise() {
        // given
        CharactersGenerator generator = new CharactersGenerator(new Board(10, 10, new ArrayList<>()));

        // when
        Tortoise tortoise = generator.generateTortoise();

        // then
        assertTrue(tortoise != null);
    }

    @Test
    public void shouldGenerateHare() {
        // given
        CharactersGenerator generator = new CharactersGenerator(new Board(10, 10, new ArrayList<>()));

        // when
        Hare hare = generator.generateHare();

        // then
        assertTrue(hare != null);
    }

}