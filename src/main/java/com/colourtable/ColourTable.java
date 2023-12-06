package com.colourtable;

import java.util.ArrayList;
import java.util.List;

public class ColourTable {
    // The maximum size of the colour palette.
    private final int paletteSize;
    // The list to store the colours in the palette.
    private final List<Integer> palette;

    /**
     * Constructs a ColourTable with the specified size.
     * The size must be a power of 2, between 2 and 1024 (inclusive).
     *
     * @param paletteSize The size of the palette.
     * @throws IllegalArgumentException If the palette size is not a power of 2 or is out of the valid range.
     */
    public ColourTable(int paletteSize) {
        if (!isValidPaletteSize(paletteSize)) {
            throw new IllegalArgumentException("Invalid palette size. Must be between 2 and 1024 and be a power of 2.");
        }
        this.paletteSize = paletteSize;
        this.palette = new ArrayList<>(paletteSize);
    }

    /**
     * Returns the size of the palette.
     *
     * @return The palette size.
     */
    public int getPaletteSize() {
        return paletteSize;
    }

    /**
     * Validates if the given size is a power of 2 and within the range [2, 1024].
     *
     * @param size The size to check.
     * @return true if the size is a valid palette size, false otherwise.
     */
    private boolean isValidPaletteSize(int size) {
        return size > 1 && (size & (size - 1)) == 0 && size < 1025;
    }

    /**
     * Validates if the given RGB colour is a valid 24-bit value.
     *
     * @param rgbColor The RGB colour to validate.
     * @return true if the RGB colour is valid, false otherwise.
     */
    private boolean isValidRGB(int rgbColor) {
        // Check if the RGB colour is a valid 24-bit value
        int red = (rgbColor >> 16) & 0xFF;
        int green = (rgbColor >> 8) & 0xFF;
        int blue = rgbColor & 0xFF;

        return red >= 0 && red <= 255 && green >= 0 && green <= 255 && blue >= 0 && blue <= 255 && rgbColor >= 0;
    }

    /**
     * Adds a colour to the palette.
     *
     * @param rgbColor The RGB colour to add.
     * @throws IllegalArgumentException If the RGB colour is invalid or a duplicate.
     * @throws IllegalStateException If the palette already contains the maximum number of colours.
     */
    public void add(int rgbColor) {
        // Validate RGB colour
        if (!isValidRGB(rgbColor)) {
            throw new IllegalArgumentException("Invalid RGB colour value added to ColourTable.");
        }
        if (palette.size() >= paletteSize) {
            throw new IllegalStateException("Exceeded the capacity of the ColourTable.");
        }

        // Check for duplicate colour
        if (palette.contains(rgbColor)) {
            throw new IllegalArgumentException("Duplicate RGB colour value added to ColourTable.");
        }

        // Add the RGB colour to the palette
        palette.add(rgbColor);
    }

    /**
     * Returns the number of colours currently in the palette.
     *
     * @return The number of colours in the palette.
     */
    public int getNumberOfColours() {
        return palette.size();
    }
}
