package game;

import java.util.Random;

public class Moves {
    private static final int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static final Point[] points = {new Point(0, -1), new Point(0, 1), new Point(-1, 0), new Point(1, 0)};
    private static final Random random = new Random();

    public Point getRandomMove() {
        return points[random.nextInt(3)];
    }

    public Point getMove(char direction) {
        return getMoveDependingOnDirection(direction);
    }

    private Point getMoveDependingOnDirection(char direction) {
        if (direction == 'w') return points[0];
        if (direction == 's') return points[1];
        if (direction == 'a') return points[2];
        if (direction == 'd') return points[3];
        return new Point(0, 0);
    }
}
