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
        Point newPosition = movesHelper.getMove(direction).add(player.getPoint());
        if (board.include(newPosition)) {
            if (isAppleOn(newPosition)) {
                player.incrementStrength();
                board.removeApple(newPosition);
            }
            if (isBoarOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, boar)) {
                    killBoar();
                } else {
                    killPlayer(newPosition);
                }
            }
            if (isTortoiseOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, tortoise)) {
                    killTortoise();
                } else {
                    killPlayer(newPosition);
                }
            }
            if (isHareOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, hare)) {
                    killHare();
                } else {
                    killPlayer(newPosition);
                }
            }

            if (isGrassOn(newPosition)) {
                board.removeGrass(newPosition);
            }
            player.setPosition(newPosition);
            boarMove();
            tortoiseMove();
            hareMove();
            generateNature();
        }
        System.out.println(player.getX() + ", " + player.getY() + ", strength: " + player.getStrength());
    }

    private void killPlayer(Point point) {
        killPlayer();
        point.setLocation(player.getX(), player.getY());
    }

    private void killPlayer() {
        System.out.println("Player has been eaten!");
        player = charactersGenerator.generatePlayer();
        player.resetStrength();
    }

    private void killBoar(Point point) {
        point.setLocation(boar.getX(), boar.getY());
        killBoar();
    }

    private void killBoar() {
        System.out.println("Boar has been eaten");
        boar = charactersGenerator.generateBoar();
    }

    private void killTortoise(Point point) {
        killTortoise();
        point.setLocation(tortoise.getX(), tortoise.getY());
    }

    private void killTortoise() {
        tortoise = charactersGenerator.generateTortoise();
        System.out.println("Tortoise has been eaten");
    }

    private void killHare(Point point) {
        killHare();
        point.setLocation(tortoise.getX(), tortoise.getY());
    }

    private void killHare() {
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
        Point newPosition = movesHelper.getRandomMove().add(boar.getPoint());
        if (board.include(newPosition)) {
            if (isGrassOn(newPosition)) {
                boar.incrementStrength();
                board.removeGrass(newPosition);
            }
            if (isPlayerOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, player)) {
                    killPlayer();
                } else {
                    killBoar(newPosition);
                }
            }
            if (isTortoiseOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, tortoise)) {
                    killTortoise();
                } else {
                    killBoar(newPosition);
                }
            }
            if (isHareOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, hare)) {
                    killHare();
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

    public void tortoiseMove() {
        Point newPosition = movesHelper.getRandomMove().add(tortoise.getPoint());
        if (board.include(newPosition)) {
            if (isGrassOn(newPosition)) {
                tortoise.incrementStrength();
                board.removeGrass(newPosition);
            }
            if (isPlayerOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(tortoise, player)) {
                    killPlayer();
                } else {
                    killTortoise(newPosition);
                }
            }
            if (isBoarOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(tortoise, boar)) {
                    killBoar();
                } else {
                    killTortoise(newPosition);
                }
            }
            if (isHareOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(tortoise, hare)) {
                    killHare();
                } else {
                    killTortoise(newPosition);
                }
            }
            if (isAppleOn(newPosition)) {
                board.removeApple(newPosition);
            }
            tortoise.setPosition(newPosition);
        }
    }

    public void hareMove() {
        Point newPosition = movesHelper.getRandomMove().add(hare.getPoint());
        if (board.include(newPosition)) {
            if (isGrassOn(newPosition)) {
                hare.incrementStrength();
                board.removeGrass(newPosition);
            }
            if (isPlayerOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(hare, player)) {
                    killPlayer();
                } else {
                    killHare(newPosition);
                }
            }
            if (isBoarOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(hare, boar)) {
                    killBoar();
                } else {
                    killHare(newPosition);
                }
            }
            if (isTortoiseOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(hare, tortoise)) {
                    killTortoise();
                } else {
                    killHare(newPosition);
                }
            }
            if (isAppleOn(newPosition)) {
                board.removeApple(newPosition);
            }
            boar.setPosition(newPosition);
        }
    }


    public List<Character> getCharacters() {
        List<Character> characters = new ArrayList<>(4);
        characters.add(player);
        characters.add(boar);
        characters.add(tortoise);
        characters.add(hare);
        return characters;
    }
}
