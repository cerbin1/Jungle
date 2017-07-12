package game;

public class Wolf extends Character {
    public Wolf(int x, int y, int strength) {
        super(x, y);
        setStrength(strength);
    }

    public void incrementStrength() {
        setStrength(getStrength() + 1);
    }

    @Override
    public void move(Board board) {
        int[] dupa = Moves.getMove();
        int x = getX() + dupa[0], y = getY() + dupa[1];
        if (!board.isOutOfBoard(x, y)) {
            clearCharacter(board.getArray());
            if (board.getArray()[x][y] == '#') {
                incrementStrength();

            } else if (board.getArray()[x][y] == '@') {
                if (board.getPlayer().getStrength() > getStrength()) {
                    board.generateWolf();
                } else {
                    board.generatePlayer();
                }
            }
        } else if (board.getArray()[x][y] == 'h') {
            if (board.getHare().getStrength() > getStrength()) {
                board.generateWolf();
            } else {
                board.generateHare();
            }
        } else if (board.getArray()[x][y] == 't') {
            if (board.getTortoise().getStrength() > getStrength()) {
                board.generateWolf();
            } else {
                board.generateTortoise();
            }
        }
    }

    public void clear(char[][] array) {
        this.clearCharacter(array);
    }
}
