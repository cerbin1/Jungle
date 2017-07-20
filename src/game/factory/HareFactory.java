package game.factory;

import game.Character;
import game.Hare;
import game.Point;

import java.util.Random;

public class HareFactory implements CharacterFactory {
    @Override
    public Character create(Point position, int strength) {
        return new Hare(position, strength);
    }

    @Override
    public int getRandomStrength() {
        return new Random().nextInt(1);
    }
}
