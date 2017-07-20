package game;

import game.factory.CharacterFactory;
import game.factory.PlayerFactory;

public class Player extends Character {

    public Player(Point position) {
        super(position, '@');
    }

    public void resetStrength() {
        setStrength(0);
    }

    @Override
    public CharacterFactory getFactory() {
        return new PlayerFactory();
    }
}
