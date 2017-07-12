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
        generatePlayer();
        placePlayer();
    }

    private void generatePlayer() {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        player = new Player(x, y);
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

    void makeMove(char direction) {
        int x = -1, y = -1;
        System.out.println(player.getX() + ", " + player.getY());
        if (direction == 'w') {
            x = player.getX();
            y = player.getY() - 1;
        }
        if (direction == 's') {
            x = player.getX();
            y = player.getY() + 1;
        }
        if (direction == 'a') {
            x = player.getX() - 1;
            y = player.getY();
        }
        if (direction == 'd') {
            x = player.getX() + 1;
            y = player.getY();
        }
        if (!isOutOfBoard(x, y)) {
            player = player.movePlayer(x, y);
        }
        placePlayer();
    }

    private boolean isOutOfBoard(int x, int y) {
        return 0 > x || x >= width || 0 > y || y >= height;
    }
}
