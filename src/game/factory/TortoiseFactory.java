package game.factory;

import game.Character;
import game.Point;
import game.Tortoise;

import java.util.Random;

public class TortoiseFactory implements CharacterFactory {
    @Override
    public Character create(Point position, int strength) {
        return new Tortoise(position, strength);
    }

    @Override
    public int getRandomStrength() {
        return new Random().nextInt(2);
    }
}
