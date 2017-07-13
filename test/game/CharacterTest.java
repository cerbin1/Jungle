package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {
    @Test
    public void shouldCreateCharacter() {
        // given
        Character character;

        // when
        character = new Player(0, 0);

        // then
        assertEquals(0, character.getX());
        assertEquals(0, character.getY());
        assertEquals(0, character.getStrength());
    }

    @Test
    public void shouldIncrementStrength() {
        // given
        Character character = new Player(0, 0);
        int strengthBefore = character.getStrength();

        // when
        character.incrementStrength();
        character.incrementStrength();
        character.incrementStrength();

        // then
        assertEquals(0, strengthBefore);
        assertEquals(3, character.getStrength());
    }

    @Test
    public void shouldSetCoordinates() {
        // given
        Character character = new Player(0, 0);

        // when
        character.setCoOrdinates(15, 23);

        // then
        assertEquals(15, character.getX());
        assertEquals(23, character.getY());
    }

    @Test
    public void shouldNotSetCoordinates() {
        // given
        Character character = new Player(0, 0);

        // when
        character.setCoOrdinates(-12, 32);

        // then
        assertEquals(0, character.getX());
        assertEquals(0, character.getY());
    }
}
