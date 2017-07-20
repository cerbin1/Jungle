package game;

import game.factory.CharacterFactory;
import game.factory.HareFactory;

public class Hare extends Character {
    public Hare(Point position, int strength) {
        super(position, 'h');
        setStrength(strength);
    }

    @Override
    public CharacterFactory getFactory() {
        return new HareFactory();
    }
}
