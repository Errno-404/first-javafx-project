package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height){
        this.rightCorner = new Vector2d(width, height);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return position.follows(this.leftCorner)
                && position.precedes(this.rightCorner)
                && !(objectAt(position) instanceof  Animal); // animal może wejść na Grass
    }
    @Override
    public Object objectAt(Vector2d position){
        for(Animal animal: this.animals){
            if(animal.isAt(position)){
                return animal;
            }
        }
        return null;
    }
    @Override
    protected void dynamicSizing() {
        // do nothing
    }
}
