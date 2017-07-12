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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void clearCharacter(char[][] array) {
        array[x][y] = ' ';
    }

    public void incrementStrength() {
        strength++;
    }
}
