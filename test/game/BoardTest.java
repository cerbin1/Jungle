package game;

import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    private static Board getBoard() {
        return new Board(10, 10);
    }

    @Test
    public void shouldReturnTrueIfPointIsOnTheBoard() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.isInsideBoard(5, 5);

        // then
        Assert.assertTrue(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfXIsSameAsWidth() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.isInsideBoard(10, 5);

        // then
        Assert.assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfXIsLargerAsWidth() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.isInsideBoard(15, 5);

        // then
        Assert.assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfXIsSmallerThanZero() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.isInsideBoard(-5, 5);

        // then
        Assert.assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfYIsSameAsHeight() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.isInsideBoard(5, 10);

        // then
        Assert.assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfYIsLargerAsHeight() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.isInsideBoard(5, 15);

        // then
        Assert.assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfYIsSmallerThanZero() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.isInsideBoard(5, -5);

        // then
        Assert.assertFalse(isOnBoard);
    }

    @Test
    public void shouldCountFreeSpaces() {
        // given
        Board board = getBoard();

        // when
        int freeSpaces = board.countFreeSpaces();
        // then
        Assert.assertEquals(100, freeSpaces);
    }

    @Test
    public void shouldReturnTrueIfItIsEmptySpace() {
        // given
        Board board = getBoard();

        // when
        boolean isEmpty = board.isEmptySpace(0, 0);

        // then
        Assert.assertTrue(isEmpty);
    }

}
