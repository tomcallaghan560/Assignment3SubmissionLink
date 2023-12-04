package com.colourtable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColourTableTest {
    /**
     * Test that the constructor throws an IllegalArgumentException for a palette size of 1,
     * which is invalid because a valid palette size must be greater than 1.
     */
    @Test
    void testConstructorWithValidPaletteSizeGreaterThan1() {
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1));
    }

    /**
     * Test that the constructor throws an IllegalArgumentException for a palette size of 1025 or more,
     * which is invalid because a valid palette size must be less than 1025.
     */
    @Test
    void testConstructorWithValidPaletteSizeLessThan1025() {
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1025));
    }

    /**
     * Test that the constructor successfully creates a ColourTable object for all palette sizes
     * that are powers of two and less than 1025.
     */
    @Test
    void testConstructorWithValidPaletteSizePowerOfTwo() {
        int number = 2;
        while (number < 1025) {
            ColourTable colourTable = new ColourTable(number);
            assertNotNull(colourTable);
            number = number * 2;
        }
    }

    /**
     * Test that the constructor throws an IllegalArgumentException for palette sizes
     * that are not powers of two and less than 1025.
     */
    @Test
    void testConstructorWithInvalidPaletteSizeNotPowerOfTwo() {
        int number = 1;

        while (number < 1025) {
            if ((number & (number - 1)) != 0) {
                int finalNumber = number;
                assertThrows(IllegalArgumentException.class, () -> new ColourTable(finalNumber));
            }
            number++;
        }
    }

    /**
     * Test that adding a valid RGB colour to the ColourTable is successful.
     */
    @Test
    void testAddValidRGBColour() {
        ColourTable colourTable = new ColourTable(4);
        colourTable.add(0xFF0000); // Red
        assertNotNull(colourTable);
    }

    /**
     * Test that the ColourTable correctly tracks the number of colours added.
     */
    @Test
    void testNumberOfColoursAddedValid() {
        ColourTable colourTable = new ColourTable(4);
        colourTable.add(0xFF0000); // Red
        colourTable.add(0x00FF00); // Green
        assertEquals(2, colourTable.getNumberOfColours());
    }

    /**
     * Test that adding a colour to a full ColourTable throws an IllegalStateException.
     */
    @Test
    void testAddExceedingCapacity() {
        ColourTable colourTable = new ColourTable(2);
        colourTable.add(0x00FF00); // Green
        colourTable.add(0xFF0000); // Red
        assertThrows(IllegalStateException.class, () -> colourTable.add(0x0000FF)); // Blue
    }

    /**
     * Test that adding an invalid RGB colour (outside the valid range) to the ColourTable
     * throws an IllegalArgumentException.
     */
    @Test
    void testAddInvalidRGBColour() {
        ColourTable colourTable = new ColourTable(8);
        assertThrows(IllegalArgumentException.class, () -> colourTable.add(-0x1FFFFFF));
    }

    /**
     * Test that adding a duplicate colour to the ColourTable is not allowed and
     * throws an IllegalArgumentException.
     */
    @Test
    void testAddDuplicateColourNotAllowed() {
        ColourTable colourTable = new ColourTable(8);
        colourTable.add(0xFF0000); // Red
        assertThrows(IllegalArgumentException.class, () -> colourTable.add(0xFF0000)); // Same Red again
    }
}
