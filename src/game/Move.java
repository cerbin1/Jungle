package game;

import java.util.Random;

public class Move { // TODO przerobic na enuma
    private static final Random random = new Random();

    private final Point[] points = {new Point(0, -1), new Point(0, 1), new Point(-1, 0), new Point(1, 0)};

    public Point getRandom() {
        return points[random.nextInt(3)];
    }

    public Point fromChar(char direction) {
        if (direction == 'w') return points[0];
        if (direction == 's') return points[1];
        if (direction == 'a') return points[2];
        if (direction == 'd') return points[3];
        return new Point(0, 0);
    }
}
