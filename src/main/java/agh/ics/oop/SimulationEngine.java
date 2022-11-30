package agh.ics.oop;


import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{

    private final List<Animal> animals = new ArrayList<>();
    private final MoveDirection[] moves;
//    public IWorldMap map;

    public AbstractWorldMap map;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = moves;
        this.map =  (AbstractWorldMap) map;

        for(Vector2d position : positions){
            Animal newAnimal = new Animal(this.map, position);
            this.map.place(newAnimal);
            this.animals.add(newAnimal);

        }
    }


    @Override
    public void run(){
        System.out.println(this.map);
        int animalCount = this.map.animals.size();
        for(int i = 0; i < moves.length; i++){
            this.animals.get(i% animalCount).move(moves[i]);
            System.out.println(this.map);
        }
    }
}
