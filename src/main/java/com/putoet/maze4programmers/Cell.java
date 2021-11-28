package com.putoet.maze4programmers;

/**
 * The Cell class is just a utility class that can set the right values on a grid cell which is coded
 * as a byte to save space (in case very big mazes need to be kept in memory
 */
public class Cell {
    public static byte VISITED_MASK    = 0b0000001;
    public static byte UNVISIT_MASK    = 0b1111110;
    public static byte NORTH_OPEN_MASK = 0b0001000;
    public static byte EAST_OPEN_MASK  = 0b0010000;
    public static byte SOUTH_OPEN_MASK = 0b0100000;
    public static byte WEST_OPEN_MASK  = 0b1000000;

    /**
     * Returns true is a cell has been visited,i.e.bit 0 is set
     * @param cell byte value
     * @return true if the cell was visited, false otherwise
     */
    public static boolean visited(byte cell) {
        return (cell & VISITED_MASK) != 0;
    }

    /**
     * Turns off the visited bit of the cell byte
     * @param cell current byte value
     * @return new byte value with the visited bit set to 0
     */
    public static byte unvisit(byte cell) {
        return (byte) (cell & UNVISIT_MASK);
    }

    /**
     * Sets the visited bit to 1
     * @param cell current byte value
     * @return new byte value with the visited bit set to 1
     */
    public static byte setVisited(byte cell) {
        return (byte) (cell | VISITED_MASK);
    }

    /**
     * checks if for the current set the north open bit is set
     * @param cell current byte value
     * @return true is the north bit is set, false otherwise
     */
    public static boolean northOpen(byte cell) {
        return (cell & NORTH_OPEN_MASK) != 0;
    }

    /**
     * SSets the north bit to one in the byte value and returns the new value
     * @param cell current byte value
     * @return byte value with the north bit set to 1
     */
    public static byte openNorth(byte cell) {
        return (byte) (cell | NORTH_OPEN_MASK);
    }

    /**
     * checks if for the current set the east open bit is set
     * @param cell current byte value
     * @return true is the east bit is set, false otherwise
     */
    public static boolean eastOpen(byte cell) {
        return (cell & EAST_OPEN_MASK) != 0;
    }

    /**
     * SSets the east bit to one in the byte value and returns the new value
     * @param cell current byte value
     * @return byte value with the east bit set to 1
     */
    public static byte openEast(byte cell) {
        return (byte) (cell | EAST_OPEN_MASK);
    }

    /**
     * checks if for the current set the south open bit is set
     * @param cell current byte value
     * @return true is the south bit is set, false otherwise
     */
    public static boolean southOpen(byte cell) {
        return (cell & SOUTH_OPEN_MASK) != 0;
    }

    /**
     * SSets the south bit to one in the byte value and returns the new value
     * @param cell current byte value
     * @return byte value with the south bit set to 1
     */
    public static byte openSouth(byte cell) {
        return (byte) (cell | SOUTH_OPEN_MASK);
    }

    /**
     * checks if for the current set the wesst open bit is set
     * @param cell current byte value
     * @return true is the west bit is set, false otherwise
     */
    public static boolean westOpen(byte cell) {
        return (cell & WEST_OPEN_MASK) != 0;
    }

    /**
     * SSets the west bit to one in the byte value and returns the new value
     * @param cell current byte value
     * @return byte value with the west bit set to 1
     */
    public static byte openWest(byte cell) {
        return (byte) (cell | WEST_OPEN_MASK);
    }
}
