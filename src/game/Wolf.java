package game;

public class Wolf extends Character {
    public Wolf(int x, int y, int strength) {
        super(x, y);
        setStrength(strength);
    }

    public Wolf(int x, int y) {
        super(x, y);
    }

    public void incrementStrength() {
        setStrength(getStrength() + 1);
    }
}
