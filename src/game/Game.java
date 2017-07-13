package game;

class Game {
    private Board board;

    public Player getPlayer() {
        return player;
    }

    private Player player;
    private Boar boar;
    private Tortoise tortoise;
    private Hare hare;

    Game(int width, int height) {
        board = new Board(width, height);
        generateCharacters();
        board.generate('a');
        board.generate('#');
    }

    private void generateCharacters() {
        player = board.generatePlayer();
        boar = board.generateBoar();
        tortoise = board.generateTortoise();
        hare = board.generateHare();
    }

    void displayBoard() {
        board.displayBoard();
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
        if (board.isInsideBoard(x, y)) {
            player.removeCharacterFrom(board.getBoard()); // Set remove in Board
            if (board.isWolf(x, y)) {
                if (player.getStrength() > boar.getStrength()) {
                    System.out.println("Boar has been eaten");
                    boar = board.generateBoar();
                } else {
                    killPlayer();
                }
            } else if (board.isHare(x, y)) {
                if (player.getStrength() > hare.getStrength()) {
                    hare = board.generateHare();
                } else {
                    killPlayer();
                }
            } else if (board.isTortoise(x, y)) {
                if (player.getStrength() > tortoise.getStrength()) {
                    tortoise = board.generateTortoise();
                } else {
                    killPlayer();
                }
            } else if (board.isApple(x, y)) {
                player.incrementStrength();
                player.setCoOrdinates(x, y);
            }
            board.placePlayer(x, y);
            player.setCoOrdinates(x, y);
            boarMove();
            board.generate('a');
            board.generate('#');
        }
        System.out.println(player.getX() + ", " + player.getY() + ", " + player.getStrength());
    }

    void killPlayer() {
        player = board.generatePlayer();
        player.resetStrength();
        System.out.println("Player has been eaten!");
    }

    public void boarMove() {
        int[] moves = Moves.getMove();
        int x = boar.getX() + moves[0], y = boar.getY() + moves[1];
        if (board.isInsideBoard(x, y)) {
            boar.removeCharacterFrom(board.getBoard()); // Set remove in Board
            if (board.isGrass(x, y)) {
                boar.incrementStrength();
                boar.setCoOrdinates(x, y);
            } else if (board.isPlayer(x, y)) {
                if (getPlayer().getStrength() > boar.getStrength()) {
                    boar = board.generateBoar();
                } else {
                    killPlayer();
                }
            } else if (board.isHare(x, y)) {
                if (hare.getStrength() > boar.getStrength()) {
                    boar = board.generateBoar();
                } else {
                    hare = board.generateHare();
                }
            } else if (board.isTortoise(x, y)) {
                if (tortoise.getStrength() > boar.getStrength()) {
                    boar = board.generateBoar();
                } else {
                    tortoise = board.generateTortoise();
                }
            }
            boar.setCoOrdinates(x, y);
            board.placeBoar(x, y);
        }
    }
}
