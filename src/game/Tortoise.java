package game;

import game.factory.CharacterFactory;
import game.factory.TortoiseFactory;

public class Tortoise extends Character {
    public Tortoise(Point position, int strength) {
        super(position, 't');
        setStrength(strength);
    }

    @Override
    public CharacterFactory getFactory() {
        return new TortoiseFactory();
    }
}
