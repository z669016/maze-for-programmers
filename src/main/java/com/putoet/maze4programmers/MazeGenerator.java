package com.putoet.maze4programmers;

import org.javatuples.Pair;

/**
 * A maze generator returns a Maze based on a generator algorithm,
 */
public interface MazeGenerator {
    /**
     * The configCell method sets the status of the cell at the specified (x,y) coordinates of the grid
     * @param x x-coordinate
     * @param y y-coordinate
     * @param grid the byte[][] grid with cells
     */
    void configCell(int x, int y, byte[][] grid);

    /**
     * Creates a maze with the specified size and a starting position of (0, 0) (south-west corner)
     * @param width
     * @param height
     * @return the newly created maze
     */
    default Maze of(int width, int height) {
        return of(width, height, Pair.with(0, 0));
    }

    /**
     * Creates a maze with the specified size and the specified starting position.
     * @param width
     * @param height
     * @return the newly created maze
     */
    default Maze of(int width, int height, Pair<Integer, Integer> startingPosition) {
        assert height >= 2;
        assert width >= 2;

        final byte[][] grid = new byte[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                configCell(x, y, grid);
            }
        }

        return new Maze(grid, startingPosition);
    }

    /**
     * checks if the y-coordinate corresponds to the north (top) row of the maze
     * @param y y-coordinate
     * @param grid the byte[][] grid
     * @return true if the y-coordinate is of the north (top) row, false otherwise
     */
    default boolean isMaxY(int y, byte[][] grid) {
        return y == grid.length - 1;
    }

    /**
     * checks if the x-coordinate corresponds to the east (right) column of the maze
     * @param y y-coordinate
     * @param grid the byte[][] grid
     * @return true if the x-coordinate is of the east (right) column, false otherwise
     */
    default boolean isMaxX(int x, byte[][] grid) {
        return x == grid[0].length - 1;
    }

    /**
     * Sets the status of the current cell to open to the north, and the cell above it open to the south
     * @param x x-coordinate
     * @param y y-coordinate
     * @param grid the byte[][] grid to be updated
     */
    default void openNorth(int x, int y, byte[][] grid) {
        grid[y][x] = Cell.openNorth(grid[y][x]);
        grid[y + 1][x] = Cell.openSouth(grid[y + 1][x]);
    }

    /**
     * Sets the status of the current cell to open to the east, and the cell at the right it open to the west
     * @param x x-coordinate
     * @param y y-coordinate
     * @param grid the byte[][] grid to be updated
     */
    default void openEast(int x, int y, byte[][] grid) {
        grid[y][x] = Cell.openEast(grid[y][x]);
        grid[y][x + 1] = Cell.openWest(grid[y][x + 1]);
    }

    /**
     * Random true/false generator
     * @return true or false
     */
    default boolean flipCoin() {
        return Math.random() < 0.5;
    }

    /**
     * Generates a random integer between min (inclusive) and max (inclusive)
     * @param min minimum value
     * @param max maximum value
     * @return random integer [min, max]
     */
    default int randomInt(int min, int max) {
        if (max == 0)
            return 0;

        return min + (int) (Math.random() * (max - min + 1));
    }
}
