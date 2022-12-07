package agh.ics.oop;


public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height){
        this.rightCorner = new Vector2d(width, height);
        this.leftCorner = new Vector2d(0, 0);
    }

    @Override
    public boolean canMoveTo(Vector2d position){
        return position.follows(this.leftCorner)
                && position.precedes(this.rightCorner)
                && !(objectAt(position) instanceof  Animal); // animal może wejść na Grass
    }
    @Override
    public void dynamicSizing() {
        // do nothing
    }
}
