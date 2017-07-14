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
        generateNature();
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
            if (isApple(x, y)) {
                player.incrementStrength();
            }
            if (isBoar(x, y)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, boar)) {
                    killBoar();
                } else {
                    killPlayer();
                    x = player.getX();
                    y = player.getY();
                }
            }
            if (isTortoise(x, y)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, tortoise)) {
                    killTortoise();
                } else {
                    killPlayer();
                    x = player.getX();
                    y = player.getY();
                }
            }
            if (isHare(x, y)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, hare)) {
                    killHare();
                } else {
                    killPlayer();
                    x = player.getX();
                    y = player.getY();
                }
            }
            board.placePlayer(x, y);
            player.setCoOrdinates(x, y);
            boarMove();
            generateNature();
        }
        System.out.println(player.getX() + ", " + player.getY() + ", strength: " + player.getStrength());
    }

    private boolean isApple(int x, int y) {
        return board.isApple(x, y);
    }

    private void killBoar() {
        System.out.println("Boar has been eaten");
        board.removeCharacter(boar);
        boar = board.generateBoar();
    }

    private void killTortoise() {
        System.out.println("Tortoise has been eaten");
        board.removeCharacter(tortoise);
        tortoise = board.generateTortoise();
    }

    private void killHare() {
        System.out.println("Hare has been eaten");
        board.removeCharacter(hare);
        hare = board.generateHare();
    }

    private boolean firstCharacterHasGreaterStrengthThanSecond(Character first, Character second) {
        return first.getStrength() > second.getStrength();
    }

    private void generateNature() {
        generateApple();
        generateGrass();
    }

    private void generateGrass() {
        board.generate('#');
    }

    private void generateApple() {
        board.generate('a');
    }

    private boolean isBoar(int x, int y) {
        return board.isBoar(x, y);
    }

    private boolean isTortoise(int x, int y) {
        return board.isTortoise(x, y);
    }

    private boolean isHare(int x, int y) {
        return board.isHare(x, y);
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
            if (isGrass(x, y)) {
                boar.incrementStrength();
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

    private boolean isGrass(int x, int y) {
        return board.isGrass(x, y);
    }
}
