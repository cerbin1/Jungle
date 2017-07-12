package game;

import java.util.Random;

class Board {
    private int width, height;
    private char[][] array;
    Player player;
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
}
