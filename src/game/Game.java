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
    private Moves moves;

    Game(int width, int height) {
        board = new Board(width, height);
        moves = new Moves();
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
        int[] move = moves.getMove(direction);
        int x = player.getX() + move[0], y = player.getY() + move[1];
        if (board.isInsideBoard(x, y)) {
            board.removeCharacter(player);
            if (isBoar(x, y)) {
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

    private boolean isBoar(int x, int y) {
        return board.isBoar(x, y);
    }

    void killPlayer() {
        player = board.generatePlayer();
        player.resetStrength();
        System.out.println("Player has been eaten!");
    }

    public void boarMove() {
        int[] move = moves.getMove();
        int x = boar.getX() + move[0], y = boar.getY() + move[1];
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
