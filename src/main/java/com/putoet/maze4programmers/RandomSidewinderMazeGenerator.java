package com.putoet.maze4programmers;

/**
 * Singleton maze generator using random sidewinder algorithm
 */
public class RandomSidewinderMazeGenerator implements MazeGenerator {
    private RandomSidewinderMazeGenerator() {
    }

    /**
     * Gets the instance of the maze generator
     * @return generator instance
     */
    public static MazeGenerator getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void configCell(int x, int y, byte[][] grid) {
        // Nothing to configure for the upper right corner
        if (isMaxY(y, grid) && isMaxX(x,grid))
            return;

        // On the top row, you can only move east
        if (isMaxY(y, grid)) {
            openEast(x, y, grid);
            return;
        }

        // On the most right column, you can only move north
        if (isMaxX(x, grid)) {
            x = randomInt(minRandom(x, y, grid), x);
            openNorth(x, y, grid);
            return;
        }

        // There is a choice etween east and north
        if (flipCoin()) {
            x = randomInt(minRandom(x, y, grid), x);
            openNorth(x, y, grid);
        } else
            openEast(x, y, grid);
    }

    private int minRandom(int x, int y, byte[][] grid) {
        while (Cell.westOpen(grid[y][x]))
            x--;

        return x;
    }

    private static final class InstanceHolder {
        private static final RandomSidewinderMazeGenerator instance = new RandomSidewinderMazeGenerator();
    }
}
