package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        // tablica argumentów zawierająca również argument niepoprawny

        String [] args = {"f", "forward", "h", "backward", "l", "r"};
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD,
        MoveDirection.LEFT, MoveDirection.RIGHT};

        OptionsParser parser = new OptionsParser();
        assertArrayEquals(moves, parser.parse(args));

        // pusta tablica argumentów

        args = Arrays.copyOfRange(args,0, 0);
        moves = Arrays.copyOfRange(moves, 0, 0);
        assertArrayEquals(moves, parser.parse(args));

        // tablica argumentów niepoprawnych ( wynikowa powinna być pusta )

        args = new String[]{"a", "h", "g", "i", "k"};
        assertArrayEquals(moves, parser.parse(args));

        // tablica argumentów poprawnych

        args = new String[]{"l", "forward", "left", "b", "right", "f", "f", "l","r"};
        moves = new MoveDirection[]{MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.LEFT,
                MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.RIGHT};
        assertArrayEquals(moves, parser.parse(args));
    }
}