package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalIntegrationTest {

    @Test
    public void IntegrationTest(){

        // utworzenie zwierząt do testowania umiejscowienia na mapie
        IWorldMap map = new RectangularMap(5, 5);
        Animal dog = new Animal(map, new Vector2d(0, 0));
        Animal cat = new Animal(map, new Vector2d(0, 0));

        // test czy można umieścić różne obiekty w tym samym miejscu
        map.place(dog);
        map.place(cat);

        assertTrue(map.isOccupied(new Vector2d(0, 0)));
        assertInstanceOf(dog.getClass(), map.objectAt(new Vector2d(0,0)));
        assertNull(map.objectAt(new Vector2d(1, 1)));

        assertEquals(dog, map.objectAt(new Vector2d(0, 0)));
        assertNotEquals(cat, map.objectAt(new Vector2d(0, 0)));


        cat.move(MoveDirection.FORWARD);
        assertNotEquals(cat, map.objectAt(new Vector2d(0, 1)));

        // test czy można wyjść poza mapę
        assertFalse(map.canMoveTo(new Vector2d(6, 2)));

        // dodanie większej liczby zwierząt na mapę
        cat = new Animal(map, new Vector2d(5, 5));
        map.place(cat);
        assertEquals( new Vector2d(5, 5), cat.getPosition());
        assertFalse(map.canMoveTo(new Vector2d(5, 5)));

        Animal pig = new Animal(map, new Vector2d(3, 4));
        Animal horse = new Animal(map, new Vector2d(2, 2));

        // pozycje zwierząt:

        // dog: 0, 0
        // cat: 5, 5
        // pig: 3, 4
        // horse: 2, 2

        // po wykonaniu kroków r r l f    b r l r  a b r l f

        // wyniki powinny być:

        // dog: 0,0 >
        // cat: 5, 5 >
        // pig 3, 4 <
        // horse 2, 3 ^
        // dog 0,0 >
        // cat 5, 5 v
        // pig 3, 4 v
        // horse 2, 3 >


        // dog 0, 0 >
        // cat 5, 5 <
        // pig 3, 4 <
        // horse 2, 4 ^



        RectangularMap zoo = new RectangularMap(5, 5);
        String[] instruction = {"r", "r", "l", "f",    "b", "r", "l", "r", "a", "b", "r", "l", "f"};
        MoveDirection[] directions = new OptionsParser().parse(instruction);
        Vector2d[] positions = {
                dog.getPosition(),
                cat.getPosition(),
                pig.getPosition(),
                horse.getPosition()
        };
        IEngine engine = new SimulationEngine(directions, zoo, positions);
        engine.run();


        assertEquals(zoo.animals.get(3).getPosition(), new Vector2d(3, 3));
        assertEquals(zoo.animals.get(0).getPosition(), new Vector2d(0, 0));
        assertEquals(zoo.animals.get(1).getPosition(), new Vector2d(5, 5));
        assertEquals(zoo.animals.get(2).getPosition(), new Vector2d(3, 4));

        assertEquals(zoo.animals.get(0).getAnimalDirection(), MapDirection.EAST);
        assertEquals(zoo.animals.get(1).getAnimalDirection(), MapDirection.WEST);
        assertEquals(zoo.animals.get(2).getAnimalDirection(), MapDirection.EAST);
        assertEquals(zoo.animals.get(3).getAnimalDirection(), MapDirection.EAST);

    }

}
