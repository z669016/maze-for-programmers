package com.putoet.maze4programmers;

/**
 * Singleton maze generator using random binary tree algorithm
 */
public class SouthWestRandomBinaryTreeMazeGenerator implements MazeGenerator {
    private SouthWestRandomBinaryTreeMazeGenerator() {
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
        // Nothing to configure for the lower left corner
        if (isMinY(y, grid) && isMinX(x,grid))
            return;

        // On the bottom row, you can only move east
        if (isMinY(y, grid)) {
            openWest(x, y, grid);
            return;
        }

        // On the most left column, you can only move south
        if (isMinX(x, grid)) {
            openSouth(x, y, grid);
            return;
        }

        // There is a choice between west and south
        if (flipCoin())
            openSouth(x, y, grid);
        else
            openWest(x, y, grid);
    }

    private static final class InstanceHolder {
        private static final SouthWestRandomBinaryTreeMazeGenerator instance = new SouthWestRandomBinaryTreeMazeGenerator();
    }
}
