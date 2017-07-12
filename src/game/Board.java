package game;

import java.util.Random;

class Board {
    private int width, height;
    private char[][] array;
    private Player player;
    private static final Random random = new Random();

    Board(int width, int height) {
        this.width = width;
        this.height = height;
        array = new char[width][height];
        fillBoardWithStars();
        generatePlayer();
        placePlayer();
    }

    private void generatePlayer() {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        player = new Player(x, y);
    }

    void fillBoardWithStars() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[j][i] = '*';
            }
        }
    }

    private void placePlayer() {
        array[player.getX()][player.getY()] = '@';
    }


    void displayBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(array[j][i]);
            }
            System.out.println();
        }
    }

    public void makeMove(char direction) {
        if (direction == 'w') {
            int x = player.getX(), y = player.getY() - 1;
            if (!isOutOfBoard(x, y)) {
                player = player.movePlayer(x, y);
            }
        }
        if (direction == 's') {
            int x = player.getX(), y = player.getY() + 1;
            if (!isOutOfBoard(x, y)) {
                player = player.movePlayer(x, y);
            }
        }
        if (direction == 'a') {
            int x = player.getX() - 1, y = player.getY();
            if (!isOutOfBoard(x, y)) {
                player = player.movePlayer(x, y);
            }
        }
        if (direction == 'd') {
            int x = player.getX() + 1, y = player.getY();
            if (!isOutOfBoard(x, y)) {
                player = player.movePlayer(x, y);
            }
        }
        fillBoardWithStars();
        placePlayer();
    }

    private boolean isOutOfBoard(int x, int y) {
        return x < 0 || x > height || y < 0 || y > width;
    }
}
