package game;

public class Board {
    private int width;
    private int height;
    private final char[][] board;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        board = new char[width][height];
        fillBoardWithSpaces();
    }

    private void fillBoardWithSpaces() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[j][i] = ' ';
            }
        }
    }

    public boolean isInsideBoard(int x, int y) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }


    public int countFreeSpaces() {
        int freeSpaces = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[j][i] == ' ') freeSpaces++;
            }
        }
        return freeSpaces;
    }
}
