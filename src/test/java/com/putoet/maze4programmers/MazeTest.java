package com.putoet.maze4programmers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MazeTest {
    private static final String EMPTY_MAZE =
                    "+-+-+-+-+-+\n" +
                    "|         |\n" +
                    "+-+ +-+ + +\n" +
                    "|   |   | |\n" +
                    "+ +-+ +-+ +\n" +
                    "| |   |   |\n" +
                    "+-+ +-+ + +\n" +
                    "|   |   | |\n" +
                    "+-+-+ +-+ +\n" +
                    "|o    |   |\n" +
                    "+-+-+-+-+-+\n";

    private static final String VISITED_MAZE =
                    "+-+-+-+-+-+\n" +
                    "|        o|\n" +
                    "+-+ +-+ +*+\n" +
                    "|   |   |*|\n" +
                    "+ +-+ +-+*+\n" +
                    "| |   |***|\n" +
                    "+-+ +-+*+ +\n" +
                    "|   |***| |\n" +
                    "+-+-+*+-+ +\n" +
                    "|*****|   |\n" +
                    "+-+-+-+-+-+\n";

    private static final String PARTIALLY_VISITED_MAZE =
                    "+-+-+-+-+-+\n" +
                    "|         |\n" +
                    "+-+ +-+ + +\n" +
                    "|   |   | |\n" +
                    "+ +-+ +-+ +\n" +
                    "| |   |   |\n" +
                    "+-+ +-+ + +\n" +
                    "|   |**o| |\n" +
                    "+-+-+*+-+ +\n" +
                    "|*****|   |\n" +
                    "+-+-+-+-+-+\n";

    private byte[][] grid;
    private Maze maze;

    @BeforeEach
    void setup() {
        // beware, this grid can only be traversed in one direction, as the gates to the previous cell are not opened
        grid = new byte[][] {
                {Cell.EAST_OPEN_MASK, Cell.EAST_OPEN_MASK, Cell.NORTH_OPEN_MASK, Cell.EAST_OPEN_MASK, Cell.NORTH_OPEN_MASK},
                {Cell.EAST_OPEN_MASK, Cell.NORTH_OPEN_MASK, Cell.EAST_OPEN_MASK, Cell.NORTH_OPEN_MASK, Cell.NORTH_OPEN_MASK},
                {Cell.NORTH_OPEN_MASK, Cell.EAST_OPEN_MASK, Cell.NORTH_OPEN_MASK, Cell.EAST_OPEN_MASK, Cell.NORTH_OPEN_MASK},
                {Cell.EAST_OPEN_MASK, Cell.NORTH_OPEN_MASK, Cell.EAST_OPEN_MASK, Cell.NORTH_OPEN_MASK, Cell.NORTH_OPEN_MASK},
                {Cell.EAST_OPEN_MASK, Cell.EAST_OPEN_MASK, Cell.EAST_OPEN_MASK, Cell.EAST_OPEN_MASK, 0}
        };
        maze = new Maze(grid);
    }

    @Test
    void testToString() {
        assertEquals(EMPTY_MAZE, maze.toString());
    }

    @Test
    void visitTheMaze() {
        maze.goEast().goEast().goNorth().goEast().goNorth().goEast().goNorth().goNorth();
        assertEquals(VISITED_MAZE, maze.toString());
    }

    @Test
    void partiallyVisitTheMaze() {
        maze.goEast().goEast().goNorth().goEast();
        assertEquals(PARTIALLY_VISITED_MAZE, maze.toString());
    }

    @Test
    void invalidMoves() {
        assertThrows(IllegalStateException.class, () -> maze.goWest());
        assertThrows(IllegalStateException.class, () -> maze.goSouth());
        assertThrows(IllegalStateException.class, () -> maze.goNorth());

        maze.goEast();

        assertThrows(IllegalStateException.class, () -> maze.goWest());
        assertThrows(IllegalStateException.class, () -> maze.goSouth());
        assertThrows(IllegalStateException.class, () -> maze.goNorth());

        maze.goEast();

        assertThrows(IllegalStateException.class, () -> maze.goSouth());
        assertThrows(IllegalStateException.class, () -> maze.goEast());
    }
}