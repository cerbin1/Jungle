package game;

import java.util.ArrayList;
import java.util.List;

class Game {
    private Board board;

    private Player player;
    private Boar boar;
    private Tortoise tortoise;
    private Hare hare;

    private Moves moves;

    Game(int width, int height) {
        moves = new Moves();
        board = new Board(width, height, this);
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
        Point newPosition = new Point(move.getX() + player.getX(), move.getY() + player.getY());
        if (board.include(newPosition)) {
            if (isAppleOn(newPosition)) {
                giveAppleToPlayer(newPosition);
            }
            if (isBoarOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, boar)) {
                    killBoar(newPosition);
                } else {
                    killPlayer(newPosition);
                }
            }
            if (isTortoiseOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, tortoise)) {
                    killTortoise(newPosition);
                } else {
                    killPlayer(newPosition);
                }
            }
            if (isHareOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, hare)) {
                    killHare(newPosition);
                } else {
                    killPlayer(newPosition);
                }
            }

            if (isGrassOn(newPosition)) {
                board.removeGrass(newPosition);
            }

            player.setCoordination(newPosition);
            boarMove();
            generateNature();
        }
        System.out.println(player.getX() + ", " + player.getY() + ", strength: " + player.getStrength());
    }

    private void giveAppleToPlayer(Point applePosition) {
        player.incrementStrength();
        board.removeApple(applePosition);
    }

    private void placeCharacters() {
        board.place(player);
        board.place(boar);
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

    private void killTortoise(Point point) {
        point.setLocation(tortoise.getX(), tortoise.getY());
        board.remove(tortoise);
        tortoise = board.generateTortoise();
        System.out.println("Tortoise has been eaten");
    }

    private void killHare(Point point) {
        point.setLocation(hare.getX(), hare.getY());
        board.remove(hare);
        hare = board.generateHare();
        System.out.println("Hare has been eaten");
    }

    private boolean firstCharacterHasGreaterStrengthThanSecond(Character first, Character second) {
        return first.getStrength() > second.getStrength();
    }

    private void generateNature() {
        board.generateGrass();
        board.generateApple();
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

    private boolean isGrassOn(Point point) {
        return board.isGrass(point.getX(), point.getY());
    }

    private boolean isAppleOn(Point point) {
        return board.isApple(point.getX(), point.getY());
    }

    public void boarMove() {
        Point move = moves.getRandomMove();
        Point newPosition = new Point(move.getX() + boar.getX(), move.getY() + boar.getY());
        if (board.include(newPosition)) {
            if (isGrassOn(newPosition)) {
                boar.incrementStrength();
                board.removeGrass(newPosition);
            }
            if (isPlayerOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, player)) {
                    killPlayer(newPosition);
                } else {
                    killBoar(newPosition);
                }
            }
            if (isTortoiseOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, tortoise)) {
                    killTortoise(newPosition);
                } else {
                    killBoar(newPosition);
                }
            }
            if (isHareOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, hare)) {
                    killHare(newPosition);
                } else {
                    killBoar(newPosition);
                }
            }
            if (isAppleOn(newPosition)) {
                board.removeApple(newPosition);
            }
            boar.setCoordination(newPosition);
        }
    }

    public List<Character> getCharacters() {
        List<Character> characters = new ArrayList<>(2);
        characters.add(player);
        characters.add(boar);
        return characters;
    }
}
