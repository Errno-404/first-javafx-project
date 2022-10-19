package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void next() {
//        MapDirection dir = MapDirection.NORTH; assertEquals(dir.next(), MapDirection.EAST);
//        dir = MapDirection.EAST; assertEquals(dir.next(), MapDirection.SOUTH);
//        dir = MapDirection.SOUTH; assertEquals(dir.next(), MapDirection.WEST);
//        dir = MapDirection.WEST; assertEquals(dir.next(), MapDirection.NORTH);

        assertAll(() -> assertEquals(MapDirection.NORTH.next(), MapDirection.EAST),
                () -> assertEquals(MapDirection.EAST.next(), MapDirection.SOUTH),
                () -> assertEquals(MapDirection.SOUTH.next(), MapDirection.WEST),
                () -> assertEquals(MapDirection.WEST.next(), MapDirection.NORTH));
    }

    @Test
    void previous() {
        assertAll(() -> assertEquals(MapDirection.NORTH.previous(), MapDirection.WEST),
                () -> assertEquals(MapDirection.WEST.previous(), MapDirection.SOUTH),
                () -> assertEquals(MapDirection.SOUTH.previous(), MapDirection.EAST),
                () -> assertEquals(MapDirection.EAST.previous(), MapDirection.NORTH));
    }
}