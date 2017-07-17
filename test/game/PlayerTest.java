package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void shouldResetPlayerStrength() {
        // given
        Player player = new Player(new Point(0, 0));
        player.setStrength(15);
        int strengthBefore = player.getStrength();

        // when
        player.resetStrength();

        // then
        assertEquals(15, strengthBefore);
        assertEquals(0, player.getStrength());
    }
}