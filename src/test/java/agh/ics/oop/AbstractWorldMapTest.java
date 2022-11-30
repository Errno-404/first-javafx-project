package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void place() {
        GrassField map = new GrassField(10);
        Animal dog = new Animal(map, new Vector2d(0,0));
        Animal cat = new Animal(map, new Vector2d(1,3));
        dog.addObserver(map);
        cat.addObserver(map);

        map.place(dog);
        map.place(cat);

        // check for animals position after place (without collisions)
        assertEquals(new Vector2d(1, 3), cat.getPosition());
        assertEquals(new Vector2d(0, 0), dog.getPosition());

        //check for animals position after moving (without collisions)
        dog.move(MoveDirection.RIGHT);
        cat.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        cat.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(1,0), dog.getPosition());
        assertEquals(new Vector2d(1,5), cat.getPosition());


        //check for exception when two animals are about to be placed at the same position


        Animal pig = new Animal(map, new Vector2d(1,5));
        pig.addObserver(map);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> map.place(pig),
                "expected parse to throw IllegalArgumentException but it didn't");
        assertEquals("position (1, 5) is already occupied!", thrown.getMessage());


    }
}