package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObserverTest {

    @Test
    void myFoo(){
        RectangularMap map = new RectangularMap(5, 5);
        Animal observable = new Animal(map, new Vector2d(1,1));



        observable.addObserver(map);
        map.place(observable);
        observable.move(MoveDirection.FORWARD);
        observable.positionChanged(observable.getPosition(), new Vector2d(1, 2));
        assertEquals(map.animals.get(new Vector2d(1, 2)).getPosition(), new Vector2d(1,2));

    }
}
