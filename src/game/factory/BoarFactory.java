package game.factory;

import game.Boar;
import game.Point;
import game.Character;

import java.util.Random;

public class BoarFactory implements CharacterFactory {
    @Override
    public Character create(Point position, int strength) {
        return new Boar(position, strength);
    }

    @Override
    public int getRandomStrength() {
        return new Random().nextInt(3);
    }
}
