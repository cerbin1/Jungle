package game.factory;

import game.Character;
import game.Player;
import game.Point;

public class PlayerFactory implements CharacterFactory {
    @Override
    public Character create(Point position, int strength) {
        return new Player(position);
    }

    @Override
    public int getRandomStrength() {
        return 0;
    }
}
