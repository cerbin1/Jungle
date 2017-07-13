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
            board.removeCharacter(player);
            if (board.isBoar(x, y)) {
                if (player.getStrength() > boar.getStrength()) {
                    System.out.println("Boar has been eaten");
                    boar = board.generateBoar();
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
        System.out.println(player.getX() + ", " + player.getY() + ", strength: " + player.getStrength());
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
            board.removeCharacter(boar);
            if (board.isGrass(x, y)) {
                boar.incrementStrength();
                boar.setCoOrdinates(x, y);
            } else if (board.isPlayer(x, y)) {
                if (getPlayer().getStrength() > boar.getStrength()) {
                    System.out.println("Boar has been eaten");
                    boar = board.generateBoar();
                } else {
                    killPlayer();
                }
            }
            boar.setCoOrdinates(x, y);
            board.placeBoar(x, y);
        }
    }
}
