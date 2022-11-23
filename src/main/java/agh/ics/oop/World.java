package agh.ics.oop;


public class World {
    public static void main(String[] args) {

        MoveDirection[] directions = new OptionsParser().parse(args);
        Vector2d[] positions = {new Vector2d(0, 0), new Vector2d(3, 4)};

        // Grass Field
        GrassField field = new GrassField(10);
        IEngine engine = new SimulationEngine(directions, field, positions);

        // Rectangular Map
//         RectangularMap map = new RectangularMap(10, 5);
//         IEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

    }
}

















