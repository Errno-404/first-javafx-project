package agh.ics.oop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrassField extends AbstractWorldMap {

    private final int grassCount;

//    protected List<Grass> grasses = new ArrayList<>();

    protected HashMap<Vector2d, Grass> grasses = new HashMap<>();


    public GrassField(int grassCount) {
        this.grassCount = grassCount;

        // TODO: check this
        this.rightCorner = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

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

            this.rightCorner = pos.upperRight(this.rightCorner);
            this.leftCorner = pos.lowerLeft(this.leftCorner);

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

    // jeśli dobrze zrozumiałem, to o to chodziło w dynamicznym obliczaniu rozmiaru mapy - zwierzę chce wyjść
    // poza dotychczasowe granice, to może to zrobić, za co odpowiada funkcja dynamicSizing()
    protected void dynamicSizing() {
        this.animals.forEach(
                (key, value) -> {
                    Vector2d pos = value.getPosition();
                    this.rightCorner = pos.upperRight(this.rightCorner);
                    this.leftCorner = pos.lowerLeft(this.leftCorner);
                });
    }
}
