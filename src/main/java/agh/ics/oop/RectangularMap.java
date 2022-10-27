package agh.ics.oop;

public class RectangularMap implements IWorldMap {

    private int width;
    private int height;

    private Animal [][] rMap = new Animal[this.width][this.height];

    RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }



    // in any of these methods we know if position is correct! (not out of bounds)

    // animal cannot move to given position if it is occupied or out of map range
    @Override
    public boolean canMoveTo(Vector2d position){
        if(isOccupied(position)){
            return false;
        }
        return position.follows(new Vector2d(0, 0))
                && position.precedes(new Vector2d(this.width, this.height));
    }


    // assume animal position is within map range

    @Override
    public boolean place(Animal animal){
        int x = animal.getPosition().x;
        int y = animal.getPosition().y;

        // what if this place is out of bounds?
        if(!isOccupied(animal.getPosition())){
            rMap[x][y] = animal;
            return true;
        }

        return false;
    }

    // assume that position is within map range
    @Override
    public boolean isOccupied(Vector2d position){
        int x = position.x;
        int y = position.y;

        return rMap[x][y] == null;
    }

    // assume that position is within map range
    @Override
    public Object objectAt(Vector2d position){
        int x = position.x;
        int y = position.y;
        return rMap[x][y];
    }

    @Override
    public String toString(){

        IWorldMap map = new RectangularMap(this.width, this.height);
        MapVisualizer board = new MapVisualizer(map);
    }
}
