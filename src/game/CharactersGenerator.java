package game;

import java.util.Random;

public class CharactersGenerator {
    private Board board;

    private final static Random random = new Random();

    public CharactersGenerator(Board board) {
        this.board = board;
    }

    public Player generatePlayer() {
        while (true) {
            Point point = getRandomPoint();
            if (board.isEmptySpaceOn(point)) {
                return new Player(point);
            }
        }
    }

    public Boar generateBoar() {
        while (true) {
            Point point = getRandomPoint();
            if (board.isAbleToPlaceCharacterOn(point)) {
                int strength = random.nextInt(3);
                return new Boar(point, strength);
            }
        }
    }

    public Tortoise generateTortoise() {
        while (true) {
            Point point = getRandomPoint();
            if (board.isEmptySpaceOn(point)) {
                int strength = random.nextInt(2);
                return new Tortoise(point, strength);
            }
        }
    }

    public Hare generateHare() {
        while (true) {
            Point point = getRandomPoint();
            if (board.isEmptySpaceOn(point)) {
                int strength = random.nextInt(1);
                return new Hare(point, strength);
            }
        }
    }

    private Point getRandomPoint() {
        return new Point(random.nextInt(board.getWidth()), random.nextInt(board.getHeight()));
    }
}
