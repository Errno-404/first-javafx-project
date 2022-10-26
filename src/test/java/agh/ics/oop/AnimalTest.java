package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void isAt() {
        Animal cat = new Animal();
        assertTrue(cat.isAt(new Vector2d(2,2)));

        cat.move(MoveDirection.FORWARD);
        assertTrue(cat.isAt(new Vector2d(2, 3)));
    }

    @Test
    void move() {
        Animal frog = new Animal();

        // testy na poprawną obsługę obrotu zwierzęcia - czy zamiast getPosition() można było użyć isAt() ?

        frog.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST, frog.getAnimalDirection());
        assertEquals(new Vector2d(2, 2), frog.getPosition());

        frog.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.SOUTH, frog.getAnimalDirection());
        assertEquals(new Vector2d(2, 2), frog.getPosition());

        frog.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.WEST, frog.getAnimalDirection());
        assertEquals(new Vector2d(2, 2), frog.getPosition());

        frog.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.NORTH, frog.getAnimalDirection());
        assertEquals(new Vector2d(2, 2), frog.getPosition());

        frog.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST, frog.getAnimalDirection());
        assertEquals(new Vector2d(2, 2), frog.getPosition());

        frog.move(MoveDirection.LEFT);
        assertEquals(MapDirection.SOUTH, frog.getAnimalDirection());
        assertEquals(new Vector2d(2, 2), frog.getPosition());

        frog.move(MoveDirection.LEFT);
        assertEquals(MapDirection.EAST, frog.getAnimalDirection());
        assertEquals(new Vector2d(2, 2), frog.getPosition());

        frog.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH, frog.getAnimalDirection());
        assertEquals(new Vector2d(2, 2), frog.getPosition());


        // testy na ruch zwierzęcia do przodu i do tyłu wraz ze zmianą kierunku

        frog.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.NORTH, frog.getAnimalDirection());
        assertEquals(new Vector2d(2, 3), frog.getPosition());

        frog.move(MoveDirection.LEFT);
        frog.move(MoveDirection.BACKWARD);
        assertEquals(MapDirection.WEST, frog.getAnimalDirection());
        assertEquals(new Vector2d(3, 3), frog.getPosition());


        //testy na nieprzekraczanie granicy mapy

        frog.move(MoveDirection.BACKWARD);
        assertEquals(MapDirection.WEST, frog.getAnimalDirection());
        assertEquals(new Vector2d(4, 3), frog.getPosition());

        frog.move(MoveDirection.BACKWARD);
        assertEquals(MapDirection.WEST, frog.getAnimalDirection());
        assertEquals(new Vector2d(4, 3), frog.getPosition());

        frog.move(MoveDirection.RIGHT);
        frog.move(MoveDirection.FORWARD);
        frog.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.NORTH, frog.getAnimalDirection());
        assertEquals(new Vector2d(4, 4), frog.getPosition());

        // test na nieznany kierunek, tak na wszelki wypadek

        frog.move(MoveDirection.NONE);
        assertEquals(MapDirection.NORTH, frog.getAnimalDirection());
        assertEquals(new Vector2d(4, 4), frog.getPosition());

        // test na dolną granicę (0, 0)

        Animal dog = new Animal();
        dog.move(MoveDirection.LEFT);
        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.WEST, dog.getAnimalDirection());
        assertEquals(new Vector2d(0, 2), dog.getPosition());

        dog.move(MoveDirection.LEFT);
        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);
        dog.move(MoveDirection.FORWARD);

        assertEquals(MapDirection.SOUTH, dog.getAnimalDirection());
        assertEquals(new Vector2d(0, 0), dog.getPosition());

    }
}