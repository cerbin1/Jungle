package game;

public abstract class Character {
    private int strength = 0, x, y;

    protected Character(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incrementStrength() {
        strength++;
    }

    public void setCoOrdinates(int x, int y) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        }
    }
}
