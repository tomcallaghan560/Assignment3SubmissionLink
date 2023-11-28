package com.colourtable;


import java.util.ArrayList;
import java.util.List;

public class ColourTable {
    private final int paletteSize;
    private final List<Integer> palette;

    public ColourTable(int paletteSize) {
        if (!isValidPaletteSize(paletteSize)) {
            throw new IllegalArgumentException("Invalid palette size. Must be greater than 1");
        }
        this.paletteSize = paletteSize;
        this.palette = new ArrayList<>(paletteSize);
    }

    public int getPaletteSize() {
        return paletteSize;
    }

    private boolean isValidPaletteSize(int size) {
        return size > 1;
    }
}
