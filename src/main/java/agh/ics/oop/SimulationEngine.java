package agh.ics.oop;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimulationEngine implements IEngine, IPositionChangeObserver, Runnable{

    private final List<Animal> animals = new ArrayList<>();
    private MoveDirection[] moves;
//    public IWorldMap map;

    public AbstractWorldMap map;

    public List<IPositionChangeObserver> observers = new LinkedList<>();

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
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        this.observers.forEach( (obs) -> obs.positionChanged(oldPosition, newPosition));
    }

    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }



    @Override
    public void run(){
        int animalCount = this.map.animals.size();
        for(int i = 0; i < moves.length; i++){
            this.animals.get(i% animalCount).move(moves[i]);
            this.map.dynamicSizing();
            this.positionChanged(null, null);
        }
    }

    public void setDirections(MoveDirection[] moves){
        this.moves = moves;
    }
}
