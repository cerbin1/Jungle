package game;

public class Player extends Character {

    public Player(int x, int y) {
        super(x, y);
    }

    public void clear(char[][] array) {
        this.removeCharacterFrom(array);
    }
}
