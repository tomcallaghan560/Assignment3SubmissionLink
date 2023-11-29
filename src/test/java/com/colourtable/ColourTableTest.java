package com.colourtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColourTableTest {
    @Test
    void testConstructorWithValidPaletteSizeGreaterThan1() {
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1));
    }

    @Test
    void testConstructorWithValidPaletteSizeLessThan1025() {

        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1025));
    }

    @Test
    void testConstructorWithValidPaletteSizePowerOfTwo() {
        int number = 2;
        while (number < 1025) {
            ColourTable colourTable = new ColourTable(number);
            assertNotNull(colourTable);
            number = number * 2;
        }
    }

    @Test
    void testConstructorWithInvalidPaletteSizeNotPowerOfTwo() {
        int number = 1;

        while (number < 1025) {
            // Check if the number is not a power of two
            if ((number & (number - 1)) != 0) {
                int finalNumber = number;
                assertThrows(IllegalArgumentException.class, () -> new ColourTable(finalNumber));
            }
            number++;
        }
    }

    @Test
    void testAddValidRGBColour() {
        ColourTable colourTable = new ColourTable(4);
        colourTable.add(0xFF0000); // Red
        assertNotNull(colourTable);
    }

    @Test
    void testNumberOfColoursAddedValid() {
        ColourTable colourTable = new ColourTable(4);
        colourTable.add(0xFF0000); // Red
        colourTable.add(0x00FF00); // Green
        assertEquals(2, colourTable.getNumberOfColours());
    }

    @Test
    void testAddExceedingCapacity() {
        ColourTable colourTable = new ColourTable(2);
        colourTable.add(0x00FF00); // Green
        colourTable.add(0xFF0000); // Red
        assertThrows(IllegalStateException.class, () -> colourTable.add(0x0000FF)); // Blue
    }


    @Test
    void testAddInvalidRGBColour() {
        ColourTable colourTable = new ColourTable(8);
        assertThrows(IllegalArgumentException.class, () -> colourTable.add(-0x1FFFFFF));
    }


    @Test
    void testAddDuplicateColourNotAllowed() {
        ColourTable colourTable = new ColourTable(8);
        colourTable.add(0xFF0000); // Red
        assertThrows(IllegalArgumentException.class, () -> colourTable.add(0xFF0000)); // Same Red again
    }
}
