package game;

import game.factory.*;

import java.util.ArrayList;
import java.util.List;

class Game {
    private Move move = new Move();

    private Board board;

    private Player player;
    private Boar boar;
    private Tortoise tortoise;
    private Hare hare;

    private CharactersGenerator charactersGenerator;

    Game(int width, int height) {
        board = new Board(width, height, this);
        charactersGenerator = new CharactersGenerator(board);
        generateCharacters();
        generateNature();
    }

    private void generateCharacters() {
        player = (Player) charactersGenerator.generate(new PlayerFactory());
        boar = (Boar) charactersGenerator.generate(new BoarFactory());
        tortoise = (Tortoise) charactersGenerator.generate(new TortoiseFactory());
        hare = (Hare) charactersGenerator.generate(new HareFactory());
    }

    void displayBoard() { // TODO przyjmuj stream na jakiego ma wyswietlac
        board.displayBoard();
    }

    void makeMove(char direction) {
        Point newPosition = move.fromChar(direction).add(player.getPoint());
        if (board.include(newPosition)) {
            if (isAppleOn(newPosition)) {
                player.incrementStrength();
                board.removeApple(newPosition);
            }
            if (isBoarOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, boar)) {
                    killBoar();
                } else {
                    killPlayer();
                    newPosition = new Point(player.getX(), player.getY());
                }
            }
            if (isTortoiseOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, tortoise)) {
                    killTortoise();
                } else {
                    killPlayer();
                    newPosition = new Point(player.getX(), player.getY());
                }
            }
            if (isHareOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(player, hare)) {
                    killHare();
                } else {
                    killPlayer();
                    newPosition = new Point(player.getX(), player.getY());
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

    private void killPlayer() {
        System.out.println("Player has been eaten!");
        player = (Player) charactersGenerator.generate(new PlayerFactory());
        player.resetStrength();
    }

    private void killBoar() {
        boar = (Boar) charactersGenerator.generate(new BoarFactory());
        System.out.println("Boar has been eaten");
    }

    private void killTortoise() {
        tortoise = (Tortoise) charactersGenerator.generate(new TortoiseFactory());
        System.out.println("Tortoise has been eaten");
    }

    private void killHare() {
        hare = (Hare) charactersGenerator.generate(new HareFactory());
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
        Point newPosition = move.getRandom().add(boar.getPoint());
        if (board.include(newPosition)) {
            if (isGrassOn(newPosition)) {
                boar.incrementStrength();
                board.removeGrass(newPosition);
            }
            if (isPlayerOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, player)) {
                    killPlayer();
                } else {
                    killBoar();
                    newPosition = new Point(boar.getX(), player.getY());
                }
            }
            if (isTortoiseOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, tortoise)) {
                    killTortoise();
                } else {
                    killBoar();
                    newPosition = new Point(boar.getX(), player.getY());
                }
            }
            if (isHareOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(boar, hare)) {
                    killHare();
                } else {
                    killBoar();
                    newPosition = new Point(boar.getX(), player.getY());
                }
            }
            if (isAppleOn(newPosition)) {
                board.removeApple(newPosition);
            }
            boar.setPosition(newPosition);
        }
    }

    public void tortoiseMove() {
        Point newPosition = move.getRandom().add(tortoise.getPoint());
        if (board.include(newPosition)) {
            if (isGrassOn(newPosition)) {
                tortoise.incrementStrength();
                board.removeGrass(newPosition);
            }
            if (isPlayerOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(tortoise, player)) {
                    killPlayer();
                } else {
                    killTortoise();
                    newPosition = new Point(tortoise.getX(), tortoise.getY());
                }
            }
            if (isBoarOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(tortoise, boar)) {
                    killBoar();
                } else {
                    killTortoise();
                    newPosition = new Point(tortoise.getX(), tortoise.getY());
                }
            }
            if (isHareOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(tortoise, hare)) {
                    killHare();
                } else {
                    killTortoise();
                    newPosition = new Point(tortoise.getX(), tortoise.getY());
                }
            }
            if (isAppleOn(newPosition)) {
                board.removeApple(newPosition);
            }
            tortoise.setPosition(newPosition);
        }
    }

    public void hareMove() {
        Point newPosition = move.getRandom().add(hare.getPoint());
        if (board.include(newPosition)) {
            if (isGrassOn(newPosition)) {
                hare.incrementStrength();
                board.removeGrass(newPosition);
            }
            if (isPlayerOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(hare, player)) {
                    killPlayer();
                } else {
                    killHare();
                    newPosition = new Point(hare.getX(), hare.getY());
                }
            }
            if (isBoarOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(hare, boar)) {
                    killBoar();
                } else {
                    killHare();
                    newPosition = new Point(hare.getX(), hare.getY());
                }
            }
            if (isTortoiseOn(newPosition)) {
                if (firstCharacterHasGreaterStrengthThanSecond(hare, tortoise)) {
                    killTortoise();
                } else {
                    killHare();
                    newPosition = new Point(hare.getX(), hare.getY());
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
