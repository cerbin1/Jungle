package game;

import java.util.Random;

class Game {
    private int width, height;

    public char[][] getBoard() {
        return board;
    }

    private char[][] board;

    public Player getPlayer() {
        return player;
    }

    private Player player;
    private Boar boar;
    private Tortoise tortoise;
    private Hare hare;
    private static final Random random = new Random();
    private int freeSpaces;

    Game(int width, int height) {
        this.width = width;
        this.height = height;
        board = new char[width][height];
        freeSpaces = width * height;
        fillBoardWithWhiteSpace();
        generateCharacters();
        placePlayer();
        generateGrass();
        generateApple();
    }

    private void generateGrass() {
        if (isEmptySpace()) {
            while (true) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                if (board[x][y] == ' ') {
                    board[x][y] = '#';
                    break;
                }
            }
        }
    }

    private void generateApple() {
        if (isEmptySpace()) {
            while (true) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                if (board[x][y] == ' ') {
                    board[x][y] = 'a';
                    break;
                }
            }
        }
    }

    private boolean isEmptySpace() {
        return freeSpaces > 0;
    }

    void generatePlayer() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (board[x][y] == ' ') {
                player = new Player(x, y);
                break;
            }
        }
    }

    private void generateCharacters() {
        generatePlayer();
        generateBoar();
        generateTortoise();
        generateHare();
    }

    void generateBoar() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (board[x][y] == ' ') {
                int strength = random.nextInt(3);
                board[x][y] = 'w';
                boar = new Boar(x, y, strength);
                break;
            }
        }
    }

    void generateTortoise() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (board[x][y] == ' ') {
                int strength = random.nextInt(2);
                board[x][y] = 't';
                tortoise = new Tortoise(x, y, strength);
                break;
            }
        }
    }

    void generateHare() {
        while (true) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if (board[x][y] == ' ') {
                int strength = random.nextInt(1);
                board[x][y] = 'h';
                hare = new Hare(x, y, strength);
                break;
            }
        }
    }

    private void fillBoardWithWhiteSpace() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[j][i] = ' ';
            }
        }
    }

    private void placePlayer() {
        board[player.getX()][player.getY()] = '@';
    }

    void displayBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[j][i]);
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
            player.removeCharacterFrom(board);
            if (board[x][y] == 'w') {
                if (player.getStrength() > boar.getStrength()) {
                    System.out.println("Boar has been eaten");
                    generateBoar();
                } else {
                    killPlayer();
                }
            } else if (board[x][y] == 'h') {
                if (player.getStrength() > hare.getStrength()) {
                    generateHare();
                } else {
                    killPlayer();
                }
            } else if (board[x][y] == 't') {
                if (player.getStrength() > tortoise.getStrength()) {
                    generateTortoise();
                } else {
                    killPlayer();
                }
            } else if (board[x][y] == 'a') {
                player.incrementStrength();
                player.setCoOrdinates(x, y);
            }
            player.setCoOrdinates(x, y);
            placePlayer();
            boarMove();
            generateGrass();
            generateApple();
        }
        System.out.println(player.getX() + ", " + player.getY() + ", " + player.getStrength());
    }

    private void killPlayer() {
        generatePlayer();
        resetPlayerStrength();
        System.out.println("Player has been eaten!");
    }

    public void boarMove() {
        int[] moves = Moves.getMove();
        int x = boar.getX() + moves[0], y = boar.getY() + moves[1];
        if (!isOutOfBoard(x, y)) {
            boar.removeCharacterFrom(board);
            if (board[x][y] == '#') {
                boar.incrementStrength();
                boar.setCoOrdinates(x, y);
            } else if (board[x][y] == '@') {
                if (getPlayer().getStrength() > boar.getStrength()) {
                    generateBoar();
                } else {
                    killPlayer();
                }
            } else if (board[x][y] == 'h') {
                if (hare.getStrength() > boar.getStrength()) {
                    generateBoar();
                } else {
                    generateHare();
                }
            } else if (board[x][y] == 't') {
                if (tortoise.getStrength() > boar.getStrength()) {
                    generateBoar();
                } else {
                    generateTortoise();
                }
            }
            boar.setCoOrdinates(x, y);
            placeBoar();
        }
    }

    private void resetPlayerStrength() {
        player.setStrength(0);
    }

    private void placeBoar() {
        board[boar.getX()][boar.getY()] = 'w';
    }

    boolean isOutOfBoard(int x, int y) {
        return 0 > x || x >= width || 0 > y || y >= height;
    }
}
