package game;

import java.util.ArrayList;
import java.util.List;

class Game {
    private MovesHelper movesHelper;

    private Board board;

    private Player player;
    private Boar boar;
    private Tortoise tortoise;
    private Hare hare;

    private CharactersGenerator charactersGenerator;

    Game(int width, int height) {
        movesHelper = new MovesHelper();
        board = new Board(width, height, this);
        charactersGenerator = new CharactersGenerator(board);
        generateCharacters();
        generateNature();
    }

    private void generateCharacters() {
        player = charactersGenerator.generatePlayer();
        boar = charactersGenerator.generateBoar();
        tortoise = charactersGenerator.generateTortoise();
        hare = charactersGenerator.generateHare();
    }

    void displayBoard() {
        board.displayBoard();
    }

    void makeMove(char direction) {
        Point move = movesHelper.getMove(direction);
        Point newPosition = move.add(player.getPoint());
        if (board.include(newPosition)) {
            if (isAppleOn(newPosition)) {
                player.incrementStrength();
                board.removeApple(newPosition);
            }
            if (isBoarOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, boar)) {
                    System.out.println("Boar has been eaten");
                    boar = charactersGenerator.generateBoar();
                } else {
                    System.out.println("Player has been eaten");
                    player = charactersGenerator.generatePlayer();
                    player.resetStrength();
                    newPosition.setLocation(player.getX(), player.getY());
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
            player.setPosition(newPosition);
            //boarMove();
            generateNature();
        }
        System.out.println(player.getX() + ", " + player.getY() + ", strength: " + player.getStrength());
    }

    private void giveAppleToPlayer(Point applePosition) {
        player.incrementStrength();
        board.removeApple(applePosition);
    }

    private void killBoar(Point point) {
        point.setLocation(player.getX(), player.getY());
        System.out.println("Boar has been eaten");
        boar = charactersGenerator.generateBoar();
    }

    private void killPlayer(Point point) {
        player = charactersGenerator.generatePlayer();
        player.resetStrength();
        point.setLocation(player.getX(), player.getY());
        System.out.println("Player has been eaten!");
    }

    private void killTortoise(Point point) {
        point.setLocation(tortoise.getX(), tortoise.getY());
        tortoise = charactersGenerator.generateTortoise();
        System.out.println("Tortoise has been eaten");
    }

    private void killHare(Point point) {
        point.setLocation(hare.getX(), hare.getY());
        hare = charactersGenerator.generateHare();
        System.out.println("Hare has been eaten");
    }

    private boolean firstCharacterHasGreaterStrengthThanSecond(Character first, Character second) {
        return first.getStrength() > second.getStrength();
    }

    private void generateNature() {
        board.generateGrass();
        board.generateApple();
    }

    private boolean isPlayerOn(Point point) {
        return player.isOnPosition(point);
    }

    private boolean isBoarOn(Point point) {
        return boar.isOnPosition(point);
    }

    private boolean isTortoiseOn(Point point) {
        return tortoise.isOnPosition(point);
    }

    private boolean isHareOn(Point point) {
        return hare.isOnPosition(point);
    }

    private boolean isGrassOn(Point point) {
        return board.isGrassOn(point);
    }

    private boolean isAppleOn(Point point) {
        return board.isAppleOn(point);
    }

    public void boarMove() {
        Point move = movesHelper.getRandomMove();
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
            boar.setPosition(newPosition);
        }
    }

    public List<Character> getCharacters() {
        List<Character> characters = new ArrayList<>(2);
        characters.add(player);
        characters.add(boar);
        return characters;
    }
}
