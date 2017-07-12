package game;

class Board {
    private int width, height;
    private char[][] array;

    Board(int width, int height) {
        this.width = width;
        this.height = height;
        array = new char[width][height];
    }

    void fillBoardWithStars() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[j][i] = '*';
            }
        }
    }

    void displayBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(array[j][i]);
            }
            System.out.println();
        }
    }
}
