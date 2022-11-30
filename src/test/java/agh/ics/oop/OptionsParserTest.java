package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        OptionsParser parser = new OptionsParser();


        String[] inputOne = {"f", "k", "f"};
        String[] inputTwo = {"f", "backward", "forward", "left","r"};
        String[] inputThree = {"f", "backaward", "forward", "left", "r"};
        String[] inputFour = {"left", "up", "r"};
        String[] inputFive = {"None", "l", "r"};
        String[] inputSix = {"", "r"};
        String[] inputSeven = {" ", "r"};
        String[] inputEight = {};


        // inputOne
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> parser.parse(inputOne),
                "expected parse to throw IllegalArgumentException but it didn't");
        assertEquals("k is not legal argument", thrown.getMessage());


        //inputTwo
        MoveDirection[] movesTwo = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.LEFT,
                MoveDirection.RIGHT};

        assertArrayEquals(movesTwo, parser.parse(inputTwo));



        //inputThree
        IllegalArgumentException thrownThree = assertThrows(IllegalArgumentException.class,
                () -> parser.parse(inputThree),
                "expected parse to throw IllegalArgumentException but it didn't");
        assertEquals("backaward is not legal argument", thrownThree.getMessage());




        //inputFour
        IllegalArgumentException thrownFour = assertThrows(IllegalArgumentException.class,
                () -> parser.parse(inputFour),
                "expected parse to throw IllegalArgumentException but it didn't");
        assertEquals("up is not legal argument", thrownFour.getMessage());



        //inputFive
        IllegalArgumentException thrownFive = assertThrows(IllegalArgumentException.class,
                () -> parser.parse(inputFive),
                "expected parse to throw IllegalArgumentException but it didn't");
        assertEquals("None is not legal argument", thrownFive.getMessage());



        //inputSix
        IllegalArgumentException thrownSix = assertThrows(IllegalArgumentException.class,
                () -> parser.parse(inputSix),
                "expected parse to throw IllegalArgumentException but it didn't");
        assertEquals(" is not legal argument", thrownSix.getMessage());



        //inputSeven
        IllegalArgumentException thrownSeven = assertThrows(IllegalArgumentException.class,
                () -> parser.parse(inputSeven),
                "expected parse to throw IllegalArgumentException but it didn't");
        assertEquals("  is not legal argument", thrownSeven.getMessage());



        //inputEight
        MoveDirection[] movesEight = {};
        assertArrayEquals(movesEight, parser.parse(inputEight));



    }



}