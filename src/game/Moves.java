package game;

import java.util.Random;

public class Moves {
    private static final int[][] moves = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static final Random random = new Random();

    public static int[] getMove() {
        return moves[random.nextInt(3)];
    }
}
