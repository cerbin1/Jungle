package game;

public class Board {
    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isOnBoard(int x, int y) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }
}
