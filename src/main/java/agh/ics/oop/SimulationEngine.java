package agh.ics.oop;

public class SimulationEngine implements IEngine{

    private MoveDirection[] moves;
    private IWorldMap map;
    private Vector2d[] positions;


    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = moves;
        this.map = map;
        this.positions = positions;

        for(Vector2d position : this.positions){
            Animal newAnimal = new Animal(this.map, position);
            this.map.place(newAnimal);
        }
    }


    @Override
    public void run(){


        System.out.print(map);
    }
}
