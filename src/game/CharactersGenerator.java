package game;

import game.factory.CharacterFactory;

import java.util.Random;

public class CharactersGenerator {
    private Board board;

    private final static Random random = new Random();

    public CharactersGenerator(Board board) {
        this.board = board;
    }

    public Character generate(CharacterFactory characterFactory) {
        Point availablePoint = getRandom();
        return characterFactory.create(availablePoint, characterFactory.getRandomStrength());
    }

    private Point getRandom() {
        return board.getAvailablePoints().get(random.nextInt(board.getAvailablePoints().size()));
    }
}
