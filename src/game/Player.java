package game;

public class Player extends Character {

    public Player(Point position) {
        super(position, '@');
    }

    public void resetStrength() {
        setStrength(0);
    }
}
