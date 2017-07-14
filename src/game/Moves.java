package game;

import java.util.Random;

public class Moves {
    private static final int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static final Random random = new Random();

    public int[] getMove() {
        return moves[random.nextInt(3)];
    }

    public int[] getMove(char direction) {
        return getMoveDependingOnDirection(direction);
    }

    private int[] getMoveDependingOnDirection(char direction) {
        if (direction == 'w') return moves[0];
        if (direction == 's') return moves[1];
        if (direction == 'a') return moves[2];
        if (direction == 'd') return moves[3];
        return new int[]{0, 0};
    }
}
