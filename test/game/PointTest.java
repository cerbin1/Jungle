package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void shouldCreatePoint() {
        // given


        // when
        Point point = new Point(1, 15);

        // then
        assertEquals(1, point.getX());
        assertEquals(15, point.getY());
    }

    @Test
    public void shouldSetPointLocation() {
        // given
        Point point = new Point(10, 20);
        Point newPoint = new Point(point.getX(), point.getY());

        // when
        newPoint.setLocation(5, 10);

        // then
        assertEquals(new Point(10, 20), point);
        assertEquals(new Point(5, 10), newPoint);
    }

    @Test
    public void shouldAddPoint() {
        // given
        Point point = new Point(4, 5);

        // when
        Point addedPoint = point.add(new Point(6, 5));

        // then
        assertTrue(addedPoint.equals(new Point(10, 10)));
    }

    @Test
    public void shouldGetPointToString() {
        // given
        Point point = new Point(13, 21);

        // when
        String pointToString = point.toString();

        // then
        assertEquals("Point{x=13, y=21}", pointToString);
    }
}
