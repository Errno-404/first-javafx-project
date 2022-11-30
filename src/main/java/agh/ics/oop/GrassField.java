package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap {
    private final int grassCount;

    protected HashMap<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        this.grassCount = grassCount;


        // ustalamy maxRange na n = sqrt(10 * grassCount)
        int n = (int) Math.sqrt(10 * grassCount);

        int x;
        int y;

        // mój pierwszy do-while w życiu

        for (int i = 0; i < grassCount; i++) {
            do {
                x = (int) (Math.random() * n);
                y = (int) (Math.random() * n);

                Grass newGrass = new Grass(new Vector2d(x, y));
            } while (grasses.containsKey(new Vector2d(x, y)));


            Vector2d pos = new Vector2d(x, y);
            grasses.put(pos, new Grass(pos));

            this.mapBoundary.addCordsXY(pos);
        }

    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !(objectAt(position) instanceof Animal); // animal może wejść na Grass
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object o = super.objectAt(position);
        if (o != null) return o;
        else {
            return grasses.getOrDefault(position, null);
        }
    }


    protected void dynamicSizing() {
        this.leftCorner = this.mapBoundary.cordsX.first().lowerLeft(this.mapBoundary.cordsY.first());
        this.rightCorner = this.mapBoundary.cordsX.last().upperRight(this.mapBoundary.cordsY.last());
    }
}
