package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void canMoveTo() {
        GrassField map = new GrassField(10);
        Animal dog = new Animal(map, new Vector2d(2, 3));
        Animal cat = new Animal(map, new Vector2d(1, 0));
        Animal pig = new Animal(map, new Vector2d(5, 5));
        map.place(dog);
        map.place(cat);
        map.place(pig);

        assertFalse(map.canMoveTo(new Vector2d(2,3)));
        assertTrue(map.canMoveTo(new Vector2d(2,4)));
        assertFalse(map.canMoveTo(new Vector2d(1, 0)));

        // może się poruszać w dowolne miejsce niezajęte przez zwierzę
        assertTrue(map.canMoveTo(new Vector2d(1000, 10000)));



    }

    @Test
    void objectAt() {
        GrassField map = new GrassField(10);
        Animal dog = new Animal(map, new Vector2d(2, 3));
        Animal cat = new Animal(map, new Vector2d(1, 0));
        Animal pig = new Animal(map, new Vector2d(5, 5));

        map.place(dog);
        map.place(cat);
        map.place(pig);


        Animal sampleAnimal = new Animal(map, new Vector2d(0, 0));
        assertInstanceOf(sampleAnimal.getClass(), map.objectAt(new Vector2d(2, 3)));

        Grass sampleGrass = new Grass(new Vector2d(0, 0));
        Vector2d pos = map.grasses.get(new Vector2d(0, 0)).getPosition();
        assertInstanceOf(sampleGrass.getClass(), map.objectAt(pos));
    }

    @Test
    void isOccupied(){
        GrassField map = new GrassField(10);
        Animal dog = new Animal(map, new Vector2d(2, 3));
        Animal cat = new Animal(map, new Vector2d(1, 0));
        Animal pig = new Animal(map, new Vector2d(5, 5));

        map.place(dog);
        map.place(cat);
        map.place(pig);



        assertFalse(map.isOccupied(new Vector2d(-2, -4)));
        assertFalse(map.isOccupied(new Vector2d(10000, 10000)));
    }

    // metoda place została przetestowana pośrednio w metodach powyżej
}