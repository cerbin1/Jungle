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
        Point move = moves.getMove(direction);
        System.out.println(move);
        Point point = new Point(move.getX() + player.getX(), move.getY() + player.getY());
        System.out.println(point);
        if (board.include(point)) {
            System.out.println("Da sie robic ruch");
            board.remove(player);
            if (isAppleOn(point)) {
                player.incrementStrength();
                player.setCoOrdinates(point.getX(), point.getY());
            }
            if (isBoarOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, boar)) {
                    killBoar(point);
                } else {
                    killPlayer(point);
                }
            }
            if (isTortoiseOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, tortoise)) {
                    killTortoise(player);
                } else {
                    killPlayer(point);
                }
            }
            if (isHareOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, hare)) {
                    killHare(player);
                } else {
                    killPlayer(point);
                }
            }
            player.setCoOrdinates(point.getX(), point.getY());
            placeCharacters();
            boarMove();
            generateNature();
        }
        System.out.println("Punkt: " + point.toString());
        System.out.println(player.getX() + ", " + player.getY() + ", strength: " + player.getStrength());
    }

    private void placeCharacters() {
        board.place(player);
        board.place(boar);
    }

    private boolean isAppleOn(Point point) {
        return board.isApple(point.getX(), point.getY());
    }

    private void killBoar(Point point) {
        point.setLocation(boar.getX(), boar.getY());
        System.out.println("Boar has been eaten");
        boar = board.generateBoar();
    }

    private void killPlayer(Point point) {
        player = board.generatePlayer();
        player.resetStrength();
        point.setLocation(player.getX(), player.getY());
        System.out.println("Player has been eaten!");
    }

    private void killTortoise(Character killer) {
        killer.setCoOrdinates(tortoise.getX(), tortoise.getY());
        board.remove(tortoise);
        tortoise = board.generateTortoise();
        System.out.println("Tortoise has been eaten");
    }

    private void killHare(Character killer) {
        killer.setCoOrdinates(hare.getX(), hare.getY());
        board.remove(hare);
        hare = board.generateHare();
        System.out.println("Hare has been eaten");
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

    public void boarMove() {
        Point dupa = moves.getRandomMove();
        System.out.println(dupa);
        dupa.setLocation(dupa.getX() + boar.getX(), dupa.getY() + boar.getY());
        if (board.include(dupa)) {
            if (board.isEmptySpace(dupa.getX(), dupa.getY())) {
                board.remove(boar);
                boar.setCoOrdinates(dupa.getX(), dupa.getY());
            }
        }
            /*if (board.include(dupa)) {
                board.remove(boar);
                if (isGrassOn(dupa)) {
                    boar.incrementStrength();
                }
                if (isPlayerOn(dupa)) {
                    if (firstCharacterHasGreaterStrengthThanSecond(boar, player)) {
                        killPlayer(dupa);
                    } else {
                        killBoar(dupa);
                    }
                }
                if (isTortoiseOn(dupa)) {
                    if (firstCharacterHasGreaterStrengthThanSecond(boar, tortoise)) {
                        killTortoise(boar);
                    } else {
                        killBoar(dupa);
                    }
                }
                if (isHareOn(dupa)) {
                    if (firstCharacterHasGreaterStrengthThanSecond(boar, hare)) {
                        killHare(boar);
                    } else {
                        killBoar(dupa);
                    }
                }
                placeCharacters();
            }*/
    }

    private boolean isGrassOn(Point point) {
        return board.isGrass(point.getX(), point.getY());
    }
}
