package game;

public abstract class Character {
    private int strength = 0;
    private Point position;
    private final char character;

    protected Character(Point position, char character) {
        this.position = position;
        this.character = character;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public void incrementStrength() {
        strength++;
    }

    public void setPosition(Point position) {
        if (position.getX() >= 0 && position.getY() >= 0) {
            this.position = position;
        }
    }

    public boolean standsOn(Point otherPosition) {
        return position.equals(otherPosition);
    }

    public char getCharacter() {
        return character;
    }

    public boolean isOnPosition(Point position) {
        return this.position.equals(position);
    }
}
