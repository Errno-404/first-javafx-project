package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap {

    private final int grassCount;

    protected List<Grass> grasses = new ArrayList<>();


    public GrassField(int grassCount) {
        this.grassCount = grassCount;

        this.rightCorner = new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);

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
            } while (grasses.contains(new Grass(new Vector2d(x, y))));


            Vector2d pos = new Vector2d(x, y);
            grasses.add(new Grass(pos));

            this.rightCorner = pos.upperRight(this.rightCorner);
            this.leftCorner = pos.lowerLeft(this.leftCorner);

        }

    }


    @Override
    public boolean canMoveTo(Vector2d position){
        return  !(objectAt(position) instanceof  Animal); // animal może wejść na Grass
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : this.animals) {
            if (animal.getPosition().equals(position)) {
                return animal;
            }
        }

        for (Grass grass : this.grasses) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    // dynamicSizing do poprawy, bo coś mi tu nie działa :/
    // reszta OK!
    protected void dynamicSizing() {
        for (Animal animal : animals) {
            this.rightCorner = animal.getPosition().upperRight(this.rightCorner);
            this.leftCorner = animal.getPosition().lowerLeft((this.leftCorner));
        }
    }

}
