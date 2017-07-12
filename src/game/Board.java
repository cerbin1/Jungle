package game;

import java.util.Random;

class Board {
    private int width, height;
    private char[][] array;
    private Player player;
    private Wolf wolf;
    private Tortoise tortoise;
    private Hare hare;
    private static final Random random = new Random();
    private int freeSpaces;

    Board(int width, int height) {
        this.width = width;
        this.height = height;
        array = new char[width][height];
        freeSpaces = width * height;
        fillBoardWithWhiteSpace();
        generateCharacters();
        placePlayer();
        generateGrass();
    }

    private void generateGrass() {
        if (isEmptySpace()) {
            while (true) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                if (array[x][y] == ' ') {
                    array[x][y] = '#';
                    break;
                }
            }
        }
    }

    private boolean isEmptySpace() {
        return freeSpaces > 0;
    }

    private void generatePlayer() {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        player = new Player(x, y);
    }

    private void generateCharacters() {
        generatePlayer();
        generateWolf();
        generateTortoise();
        generateHare();
    }

    private void generateWolf() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (array[x][y] == ' ') {
                int strength = random.nextInt(3);
                array[x][y] = 'w';
                wolf = new Wolf(x, y, strength);
                break;
            }
        }
    }

    private void generateTortoise() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (array[x][y] == ' ') {
                int strength = random.nextInt(2);
                array[x][y] = 't';
                tortoise = new Tortoise(x, y, strength);
                break;
            }
        }
    }

    private void generateHare() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (array[x][y] == ' ') {
                int strength = random.nextInt(1);
                array[x][y] = 'h';
                hare = new Hare(x, y, strength);
                break;
            }
        }
    }

    private void fillBoardWithWhiteSpace() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[j][i] = ' ';
            }
        }
    }

    private void placePlayer() {
        array[player.getX()][player.getY()] = '@';
    }

    private void clearPlayer() {
        array[player.getX()][player.getY()] = ' ';
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
            clearPlayer();
            player = player.movePlayer(x, y);
            placePlayer();
            generateGrass();
        }
        System.out.println(player.getX() + ", " + player.getY());
    }

    private boolean isOutOfBoard(int x, int y) {
        return 0 > x || x >= width || 0 > y || y >= height;
    }
}
