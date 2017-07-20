package game;

import game.factory.CharacterFactory;

public abstract class Character {
    private int strength = 0;
    private Point position;
    private final char character;

    protected Character(Point position, char character) {
        this.position = position;
        this.character = character;
    }

    public abstract CharacterFactory getFactory();

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

    public char getCharacter() {
        return character;
    }

    public boolean isOnPosition(Point other) {
        return this.position.equals(other);
    }

    public Point getPoint() {
        return position;
    }
}
