package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private Game game;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int width;
    private int height;
    private final char[][] board;

    private final List<Point> grass;
    private final List<Point> apples;

    private final static Random random = new Random();

    public Board(int width, int height, Game game) {
        this.game = game;
        this.width = width;
        this.height = height;
        board = new char[width][height];
        grass = new ArrayList<>();
        apples = new ArrayList<>();
    }

    private void fillBoardWithDots() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[j][i] = '.';
            }
        }
    }

    public boolean include(Point point) {
        return 0 <= point.getX() && point.getX() < width && 0 <= point.getY() && point.getY() < height;
    }

    public void displayBoard() {
        fillBoardWithDots();
        for (Character character : game.getCharacters()) {
            board[character.getX()][character.getY()] = character.getCharacter();
        }

        for (Point point : grass) {
            board[point.getX()][point.getY()] = '#';
        }

        for (Point point : apples) {
            board[point.getX()][point.getY()] = 'a';
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[j][i]);
            }
            System.out.println();
        }
    }

    public int countFreeSpaces() {
        return width * height - game.getCharacters().size() - grass.size() - apples.size();
    }


    public boolean isEmptySpaceOn(Point point) {
        return !grass.contains(point)
                && !apples.contains(point)
                && game.getCharacters().stream().noneMatch(character -> character != null && character.isOnPosition(point));
    }

    public boolean isAppleOn(Point position) {
        return apples.contains(position);
    }

    public boolean isGrassOn(Point position) {
        return grass.contains(position);
    }

    public void generateGrass() {
        if (countFreeSpaces() != 0) {
            while (true) {
                Point point = getRandomPoint();
                if (isEmptySpaceOn(point)) {
                    addGrassOn(point);
                    break;
                }
            }
        }
    }

    private void addGrassOn(Point point) {
        grass.add(point);
    }

    public void generateApple() {
        if (countFreeSpaces() != 0) {
            while (true) {
                Point point = getRandomPoint();
                if (isEmptySpaceOn(point)) {
                    addAppleOn(point);
                    break;
                }
            }
        }
    }

    private void addAppleOn(Point point) {
        apples.add(point);
    }

    private Point getRandomPoint() {
        return new Point(random.nextInt(width), random.nextInt(height));
    }

    public void removeApple(Point applePosition) {
        apples.remove(applePosition);
    }

    public void removeGrass(Point grassPosition) {
        grass.remove(grassPosition);
    }

    public boolean isAbleToPlaceCharacterOn(Point point) {
        if (isEmptySpaceOn(point)) {
            return true;
        }
        if (isGrassOn(point)) {
            removeGrass(point);
            return true;
        }
        if (isAppleOn(point)) {
            removeApple(point);
            return true;
        }
        return false;
    }
}
