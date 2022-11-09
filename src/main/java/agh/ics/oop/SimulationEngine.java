package agh.ics.oop;

public class SimulationEngine implements IEngine{

    private final MoveDirection[] moves;
    public RectangularMap map;


    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = moves;
        this.map = (RectangularMap) map;

        for(Vector2d position : positions){
            Animal newAnimal = new Animal(this.map, position);
            this.map.place(newAnimal);
        }
    }


    @Override
    public void run(){

        int animalCount = map.animals.size();

        for(int i = 0; i < moves.length; i++){
            map.animals.get(i % animalCount).move(moves[i]);
            System.out.print(map);
        }
    }
}
