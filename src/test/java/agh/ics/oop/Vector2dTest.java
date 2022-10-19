package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// mam na odwrót wartości expected i actual ..
class Vector2dTest {

    @Test
    void testToString() {
        Vector2d one = new Vector2d(-4, 5);
        assertEquals("(-4, 5)", one.toString());
    }

    @Test
    void precedes() {
        Vector2d one = new Vector2d(2, 5);
        Vector2d two = new Vector2d(0, 0);
        assertFalse(one.precedes(two));

    }

    @Test
    void follows() {
        Vector2d one = new Vector2d(-5, 4);
        Vector2d two = new Vector2d(0, 4);
        assertFalse(one.follows(two));

        one = new Vector2d(12, 16);
        two = new Vector2d(5, 4);
        assertTrue(one.follows(two));
    }

    @Test
    void add() {
        Vector2d one = new Vector2d(1, 2);
        Vector2d two = new Vector2d(5, 1);
        assertEquals(one.add(two), new Vector2d(6, 3));
    }

    @Test
    void subtract() {
        Vector2d one = new Vector2d(-1, -5);
        Vector2d two = new Vector2d(-5, 4);
        assertEquals(one.subtract(two), new Vector2d(4, -9));
    }

    @Test
    void upperRight() {
        Vector2d one = new Vector2d(-5, 4);
        Vector2d two = new Vector2d(0, -1);
        assertEquals(one.upperRight(two), new Vector2d(0, 4));
    }

    @Test
    void lowerLeft() {
        Vector2d one = new Vector2d(-5, 4);
        Vector2d two = new Vector2d(0, -1);
        assertEquals(one.lowerLeft(two), new Vector2d(-5, -1));
    }

    @Test
    void opposite() {
        Vector2d first = new Vector2d(0, 0);
        Vector2d second = new Vector2d(0, -1);
        Vector2d third = new Vector2d(-1, 0);
        Vector2d fourth = new Vector2d(-5, 6);

        assertAll(() -> assertEquals(first.opposite(), new Vector2d(0, 0)),
                () -> assertEquals(second.opposite(), new Vector2d(0, 1)),
                () -> assertEquals(third.opposite(), new Vector2d(1, 0)),
                () -> assertEquals(fourth.opposite(), new Vector2d(5, -6)));
    }

    @Test
    void testEquals() {
        Vector2d one = new Vector2d(-5, 10);
        Vector2d two = new Vector2d(-5, 10);
        assertEquals(one, two);

        two.add(new Vector2d(2, 2));
        two.subtract(new Vector2d(2, 2));
        assertEquals(one ,two);

        assertNotEquals(one, two.add(new Vector2d(3, 1)));
        assertEquals(one, one);
    }
}