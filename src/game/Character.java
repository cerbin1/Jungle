package game;

public abstract class Character {
    private int strength = 0, x, y;
    private final char character;

    protected Character(int x, int y, char character) {
        this.x = x;
        this.y = y;
        this.character = character;
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

    public void setCoordination(Point point) {
        int x = point.getX(), y = point.getY();
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        }
    }

    public char getCharacter() {
        return character;
    }
}
