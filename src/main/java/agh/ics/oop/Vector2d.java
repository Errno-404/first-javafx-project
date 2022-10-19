package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }


    public String toString(){
        return String.format("(%d, %d)", this.x, this.y);
    }

    public boolean precedes(Vector2d other){
        return (this.x <= other.x && this.y <= other.y);
    }

    public boolean follows(Vector2d other){
        return (this.x >= other.x && this.y >= other.y);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2d opposite(){
        return new Vector2d(-1 * this.x, -1 * this.y);
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d that))
            return false;

        return that.x == this.x && that.y == this.y;
    }
    // zmiana funkcji hashCode powinna być wystarczająca, gdyż funkcja Objects.hash()
    // pobiera dwa jedyne pola obiektu klasy wektor i na ich podstawie tworzy hash -
    // warunek, żeby 2 obiekty, dla których one.equals(two) == True miały identyczny
    // hashCode jest spełniony, ponieważ jeśli equals zwraca True, to pola x i y
    // w obu instancjach muszą być identyczne.
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
