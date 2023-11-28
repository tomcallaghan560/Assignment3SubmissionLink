package com.colourtable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColourTableTest {
    @Test
    void testConstructorWithValidPaletteSizeGreaterThan1() {
        assertThrows(IllegalArgumentException.class, () -> new ColourTable(1));
    }
}

