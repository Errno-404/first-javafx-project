package agh.ics.oop;


public class World {
    public static void main(String[] args) {

        try {
            MoveDirection[] directions = new OptionsParser().parse(args);
            Vector2d[] positions = {new Vector2d(0, 0), new Vector2d(3, 4)};

            GrassField field = new GrassField(10);
            IEngine engine = new SimulationEngine(directions, field, positions);

            engine.run();
        } catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }
}


// Comparator.comparing(Vector 2d::getX()) <- w TreeSet
// Timeline <- Event Handler do fx
















