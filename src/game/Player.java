package game;

public class Player extends Character {

    public Player(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(Board board) {

    }

    public void clear(char[][] array) {
        this.clearCharacter(array);
    }
}
