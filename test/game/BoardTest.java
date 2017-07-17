package game;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {
    private static Board getBoard() {
        return new Board(10, 10, null);
    }

    @Test
    public void shouldReturnTrueIfPointIsOnTheBoard() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.include(new Point(5, 5));

        // then
        assertTrue(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfXIsSameAsWidth() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.include(new Point(10, 5));

        // then
        assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfXIsLargerAsWidth() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.include(new Point(15, 5));

        // then
        assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfXIsSmallerThanZero() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.include(new Point(-5, 5));

        // then
        assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfYIsSameAsHeight() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.include(new Point(5, 10));

        // then
        assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfYIsLargerAsHeight() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.include(new Point(5, 15));

        // then
        assertFalse(isOnBoard);
    }

    @Test
    public void shouldReturnFalseIfYIsSmallerThanZero() {
        // given
        Board board = getBoard();

        // when
        boolean isOnBoard = board.include(new Point(5, -5));

        // then
        assertFalse(isOnBoard);
    }


    @Test
    public void shouldCountAllFreeSpaces() {
        // given
        Board board = new Board(10, 10, new ArrayList<>());

        // when
        int freeSpaces = board.countFreeSpaces();

        // then
        assertEquals(100, freeSpaces);
    }

    @Test
    public void shouldCountFreeSpaces() {
        // given
        Board board = new Board(10, 10, new ArrayList<>());
        board.generateGrass();
        board.generateGrass();
        board.generateGrass();

        // when
        int freeSpaces = board.countFreeSpaces();

        // then
        assertEquals(97, freeSpaces);

    }

    @Test
    public void shouldReturnTrueOnEmptySpace() {
        // given
        Board board = new Board(10, 10, new ArrayList<>());

        // when
        boolean isEmpty = board.isEmptySpaceOn(new Point(0, 0));

        // then
        assertTrue(isEmpty);
    }
}
