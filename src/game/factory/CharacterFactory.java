package game.factory;

import game.Point;
import game.Character;

public interface CharacterFactory {
    Character create(Point position, int strength);

    int getRandomStrength();
}
