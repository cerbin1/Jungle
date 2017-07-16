package game;

import java.util.Random;

import static java.util.Arrays.stream;

public class Board {
    private int width;
    private int height;
    private final char[][] board;

    private final static Random random = new Random();

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

    public boolean include(Point move) {
        return 0 <= move.getX() && move.getX() < width && 0 <= move.getY() && move.getY() < height;
    }

    public void displayBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[j][i]);
            }
            System.out.println();
        }
    }

    public int countFreeSpaces() {
        return (int) stream(board)
                .map(String::new)
                .flatMapToInt(String::chars)
                .filter(i -> i == ' ')
                .count();
    }

    public boolean isEmptySpace(int x, int y) {
        return board[x][y] == ' ';
    }

    public void generate(char character) {
        if (countFreeSpaces() != 0) {
            while (true) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                if (isEmptySpace(x, y)) {
                    board[x][y] = character;
                    break;
                }
            }
        }
    }

    public void placePlayer(Point point) {
        board[point.getX()][point.getY()] = '@';
    }

    public void placeBoar(int x, int y) {
        board[x][y] = 'b';
    }

    public void placeTortoise(int x, int y) {
        board[x][y] = 't';
    }

    public void placeHare(int x, int y) {
        board[x][y] = 'h';
    }

    public Player generatePlayer() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (board[x][y] == ' ') {
                board[x][y] = '@';
                return new Player(x, y);
            }
        }
    }

    public Boar generateBoar() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (board[x][y] == ' ') {
                int strength = random.nextInt(3);
                board[x][y] = 'b';
                return new Boar(x, y, strength);
            }
        }
    }

    public Tortoise generateTortoise() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (board[x][y] == ' ') {
                int strength = random.nextInt(2);
                board[x][y] = 't';
                return new Tortoise(x, y, strength);
            }
        }
    }

    public Hare generateHare() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (board[x][y] == ' ') {
                int strength = random.nextInt(1);
                board[x][y] = 'h';
                return new Hare(x, y, strength);
            }
        }
    }

    public boolean isPlayer(int x, int y) {
        return board[x][y] == '@';
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isBoar(int x, int y) {
        return board[x][y] == 'b';
    }

    public boolean isTortoise(int x, int y) {
        return board[x][y] == 't';
    }

    public boolean isHare(int x, int y) {
        return board[x][y] == 'h';
    }

    public boolean isGrass(int x, int y) {
        return board[x][y] == '#';
    }

    public boolean isApple(int x, int y) {
        return board[x][y] == 'a';
    }

    public void remove(Character character) {
        board[character.getX()][character.getY()] = ' ';
    }

    public void place(Character character) {
        board[character.getX()][character.getY()] = character.getCharacter();
    }
}
