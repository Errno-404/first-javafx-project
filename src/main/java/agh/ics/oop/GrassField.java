package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap {
    private final int grassCount;

    protected HashMap<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        this.grassCount = grassCount;


        for (int i = 0; i < grassCount; i++) {

            Vector2d pos = pickOneRandomGrass();

            this.mapBoundary.addCordsXY(pos);
        }

    }

    private Vector2d pickOneRandomGrass(){
        int x;
        int y;
        int n = (int) Math.sqrt(10 * this.grassCount);

        Vector2d grassPosition;
        do {
            x = (int) (Math.random() * n);
            y = (int) (Math.random() * n);

            grassPosition = new Vector2d(x, y);
        } while (grasses.containsKey(grassPosition));


        Grass newGrass = new Grass(grassPosition);
        this.grasses.put(grassPosition, newGrass);
        return grassPosition;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(objectAt(position) instanceof  Grass){
            this.grasses.remove(position);
            Vector2d newGrassPosition = pickOneRandomGrass();

            // observer-like call
            this.mapBoundary.positionChanged(position, newGrassPosition);
        }
        return !(objectAt(position) instanceof Animal);
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object o = super.objectAt(position);
        if (o != null) return o;
        else {
            return grasses.getOrDefault(position, null);
        }
    }


    public void dynamicSizing() {
        this.leftCorner = this.mapBoundary.cordsX.first().lowerLeft(this.mapBoundary.cordsY.first());
        this.rightCorner = this.mapBoundary.cordsX.last().upperRight(this.mapBoundary.cordsY.last());
    }
}
