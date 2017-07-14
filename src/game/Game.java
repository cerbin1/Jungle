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
        Point point = moves.getMove(direction);
        if (board.include(point)) {
            board.remove(player);
            if (isAppleOn(point)) {
                player.incrementStrength();
            }
            if (isBoarOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, boar)) {
                    killBoar();
                } else {
                    killPlayer(point);
                }
            }
            if (isTortoiseOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, tortoise)) {
                    killTortoise();
                } else {
                    killPlayer(point);
                }
            }
            if (isHareOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, hare)) {
                    killHare();
                } else {
                    killPlayer(point);
                }
            }
            board.placePlayer(point);
            player.setCoOrdinates(point.getX(), point.getY());
            boarMove();
            generateNature();
        }
        System.out.println(player.getX() + ", " + player.getY() + ", strength: " + player.getStrength());
    }

    private boolean isAppleOn(Point point) {
        return board.isApple(point.getX(), point.getY());
    }

    private void killBoar() {
        System.out.println("Boar has been eaten");
        board.remove(boar);
        boar = board.generateBoar();
    }

    private void killTortoise() {
        System.out.println("Tortoise has been eaten");
        board.remove(tortoise);
        tortoise = board.generateTortoise();
    }

    private void killHare() {
        System.out.println("Hare has been eaten");
        board.remove(hare);
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

    private boolean isBoarOn(Point point) {
        return board.isBoar(point.getX(), point.getY());
    }

    private boolean isTortoiseOn(Point point) {
        return board.isTortoise(point.getX(), point.getY());
    }

    private boolean isHareOn(Point point) {
        return board.isHare(point.getX(), point.getY());
    }

    void killPlayer(Point point) {
        player = board.generatePlayer();
        player.resetStrength();
        point.setLocation(player.getX(), player.getY());
        System.out.println("Player has been eaten!");
    }

    public void boarMove() {
        int[] move = moves.getMove();
        int x = boar.getX() + move[0], y = boar.getY() + move[1];
        if (board.include(x, y)) {
            board.remove(boar);
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
