package game;

public class Player extends Character {

    public Player(int x, int y) {
        super(x, y);
    }

    public Player movePlayer(int x, int y) {
        return new Player(x, y);
    }
}
