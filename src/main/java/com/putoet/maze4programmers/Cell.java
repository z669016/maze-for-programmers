package com.putoet.maze4programmers;

public class Cell {
    public static byte VISITED_MASK    = 0b0000001;
    public static byte UNVISIT_MASK    = 0b1111110;
    public static byte NORTH_OPEN_MASK = 0b0001000;
    public static byte EAST_OPEN_MASK  = 0b0010000;
    public static byte SOUTH_OPEN_MASK = 0b0100000;
    public static byte WEST_OPEN_MASK  = 0b1000000;

    public static boolean visited(byte cell) {
        return (cell & VISITED_MASK) != 0;
    }

    public static byte unvisit(byte cell) {
        return (byte) (cell & UNVISIT_MASK);
    }

    public static byte setVisited(byte cell) {
        return (byte) (cell | VISITED_MASK);
    }

    public static boolean northOpen(byte cell) {
        return (cell & NORTH_OPEN_MASK) != 0;
    }

    public static byte openNorth(byte cell) {
        return (byte) (cell | NORTH_OPEN_MASK);
    }

    public static boolean eastOpen(byte cell) {
        return (cell & EAST_OPEN_MASK) != 0;
    }

    public static byte openEast(byte cell) {
        return (byte) (cell | EAST_OPEN_MASK);
    }

    public static boolean southOpen(byte cell) {
        return (cell & SOUTH_OPEN_MASK) != 0;
    }

    public static byte openSouth(byte cell) {
        return (byte) (cell | SOUTH_OPEN_MASK);
    }

    public static boolean westOpen(byte cell) {
        return (cell & WEST_OPEN_MASK) != 0;
    }

    public static byte openWest(byte cell) {
        return (byte) (cell | WEST_OPEN_MASK);
    }
}
