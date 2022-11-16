package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {


    @Test
    void canMoveTo() {
        RectangularMap map = new RectangularMap(10, 10);
        Animal dog = new Animal(map, new Vector2d(2, 3));
        Animal cat = new Animal(map, new Vector2d(1, 0));
        Animal pig = new Animal(map, new Vector2d(5, 5));
        map.place(dog);
        map.place(cat);
        map.place(pig);

        assertFalse(map.canMoveTo(new Vector2d(2,3)));
        assertTrue(map.canMoveTo(new Vector2d(2,4)));

        assertFalse(map.canMoveTo(new Vector2d(11, 10)));

    }

    @Test
    void objectAt() {
        RectangularMap map = new RectangularMap(10, 10);
        Animal dog = new Animal(map, new Vector2d(2, 3));
        Animal cat = new Animal(map, new Vector2d(1, 0));
        Animal pig = new Animal(map, new Vector2d(5, 5));
        map.place(dog);
        map.place(cat);
        map.place(pig);

        assertInstanceOf(dog.getClass(), map.objectAt(new Vector2d(2,3)));
        assertInstanceOf(cat.getClass(), map.objectAt(new Vector2d(1,0)));
        assertInstanceOf(pig.getClass(), map.objectAt(new Vector2d(5,5)));

        assertNull(map.objectAt(new Vector2d(0, 0)));
    }

    @Test
    void isOccupied(){

        RectangularMap map = new RectangularMap(10, 10);
        Animal dog = new Animal(map, new Vector2d(2, 3));
        Animal cat = new Animal(map, new Vector2d(1, 0));
        Animal pig = new Animal(map, new Vector2d(5, 5));
        map.place(dog);
        map.place(cat);
        map.place(pig);

        assertTrue(map.isOccupied(new Vector2d(2, 3)));
        assertFalse(map.isOccupied(new Vector2d(10, 10)));
        assertFalse(map.isOccupied(new Vector2d(-3, -2)));
    }

    // metoda place została przetestowana wewnątrz poprzednich metod
}