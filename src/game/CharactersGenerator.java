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
        while (true) { // TODO losowanie punktu aż trafisz na pusty jest średnie. Lepiej wyciągnąć streamem wszystkie wolne, i z tych wolnych raz zrobić randoma.
            Point point = getRandomPoint();
            if (board.isAbleToPlaceCharacterOn(point)) {
                return characterFactory.create(point, characterFactory.getRandomStrength());
            }
        }
    }

    private Point getRandomPoint() {
        return new Point(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()));
    }
}
