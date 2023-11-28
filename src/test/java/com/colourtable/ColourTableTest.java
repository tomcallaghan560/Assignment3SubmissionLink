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
    void testConstructorWithValidPaletteSizePowerofTwo() {
        int number = 2;
        while (number < 1025) {
            ColourTable colourTable = new ColourTable(number);
            assertNotNull(colourTable);
            number = number * 2;
        }
    }
}

