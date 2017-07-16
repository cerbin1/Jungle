package game;

import java.util.ArrayList;
import java.util.List;

class Game {
    private Board board;

    private Player player;
    private Boar boar;
    private Tortoise tortoise;
    private Hare hare;

    private List<Character> characters = new ArrayList<>();

    private Moves moves;

    Game(int width, int height) {
        moves = new Moves();
        board = new Board(width, height, characters);
        generateCharacters();
        addCharactersToList();
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
            board.remove(player);
            if (isAppleOn(newPosition)) {
                player.incrementStrength();
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
            player.setCoordination(newPosition);
            placeCharacters();
            boarMove();
            generateNature();
        }
        System.out.println(player.getX() + ", " + player.getY() + ", strength: " + player.getStrength());
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
        System.out.println(move);
        Point point = new Point(move.getX() + boar.getX(), move.getY() + boar.getY());
        if (board.include(point)) {
            board.remove(boar);
            if (isGrassOn(point)) {
                boar.incrementStrength();
            }
            if (isPlayerOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, player)) {
                    killPlayer(point);
                } else {
                    killBoar(point);
                }
            }
            if (isTortoiseOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, tortoise)) {
                    killTortoise(point);
                } else {
                    killBoar(point);
                }
            }
            if (isHareOn(point)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, hare)) {
                    killHare(point);
                } else {
                    killBoar(point);
                }
            }
            boar.setCoordination(point);
            placeCharacters();
        }
    }

    public void addCharactersToList() {
        characters.add(player);
        characters.add(boar);
        characters.add(tortoise);
        characters.add(hare);
    }
}
