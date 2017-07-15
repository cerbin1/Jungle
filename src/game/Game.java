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
        point.setLocation(point.getX() + player.getX(), point.getY() + player.getY());
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

    private boolean isPlayerOn(Point point) {
        return board.isPlayer(point.getX(), point.getY());
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
        Point point = moves.getRandomMove();
        point.setLocation(point.getX() + boar.getX(), point.getY() + boar.getY());
        if (board.include(point)) {
            board.remove(boar);
            if (isGrassOn(point)) {
                boar.incrementStrength();
            }
            if (isPlayerOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, player)) {
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
            boar.setCoOrdinates(x, y);
            board.placeBoar(x, y);
        }
    }

    private boolean isGrassOn(Point point) {
        return board.isGrass(point.getX(), point.getY());
    }
}
