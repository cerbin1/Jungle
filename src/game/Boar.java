package game;

import game.factory.BoarFactory;
import game.factory.CharacterFactory;

public class Boar extends Character {
    public Boar(Point position, int strength) {
        super(position, 'b');
        setStrength(strength);
    }

    @Override
    public CharacterFactory getFactory() {
        return new BoarFactory();
    }
}
