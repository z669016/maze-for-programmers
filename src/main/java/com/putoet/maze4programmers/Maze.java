package com.putoet.maze4programmers;

import org.javatuples.Pair;

/**
 * Class representing a rectangle maze. A maze can be navigated and walked through. The current position of the maze if
 * remembered. The Cell class is being used to navigate the grid.
 */
public class Maze {
    private final byte[][] grid;
    private final Pair<Integer,Integer> startingPosition;;
    private final String separator;
    private Pair<Integer,Integer> position;

    /**
     * Create a maze from the byte grid (which must be a rectangle), with starting position (0, 0). The status of the
     * cell at the starting position will be set to 'visited'.
     * @param grid The maze grid
     */
    public Maze(byte[][] grid) {
        this(grid, Pair.with(0, 0));
    }

    /**
     * Create a maze from the byte grid (which must be a rectangle), with the specified starting position.
     * The height and width of the maze must be greater than 1. The starting position must be in the maze.
     * The status of the cell at the starting position will be set to 'visited'.
     * @param grid The maze grid
     */
    public Maze(byte[][] grid, Pair<Integer,Integer> position) {
        assert grid != null;
        assert grid.length > 1;

        for (byte[] row : grid) {
            assert row != null;
            assert row.length > 1;
            assert row.length == grid[0].length;
        }

        assert position.getValue1() >= 0 && position.getValue1() < grid.length;
        assert position.getValue0() >= 0 && position.getValue0() < grid[0].length;

        this.grid = grid;
        this.separator = "+" + "-+".repeat(grid[0].length);
        this.startingPosition = this.position = position;
        this.grid[position.getValue1()][position.getValue0()] =
                Cell.setVisited(this.grid[position.getValue1()][position.getValue0()]);
    }

    /**
     * Returns the size of the grid as a pair with width, and height
     * @return Pair<Integer,Integer> (width, height)
     */
    public Pair<Integer,Integer> size() {
        return Pair.with(grid[0].length, grid.length);
    }

    /**
     * Returns the current position in the maze as a pair with x,and y coordinate
     * @return Pair<Integer,Integer> (x, y)
     */
    public Pair<Integer,Integer> position() {
        return position;
    }

    /**
     * Move the current position to the north
     * @return the updated maze
     */
    public Maze goNorth() {
        return go(Direction.NORTH);
    }

    /**
     * Move the current position to the east
     * @return the updated maze
     */
    public Maze goEast() {
        return go(Direction.EAST);
    }

    /**
     * Move the current position to the south
     * @return the updated maze
     */
    public Maze goSouth() {
        return go(Direction.SOUTH);
    }

    /**
     * Move the current position to the west
     * @return the updated maze
     */
    public Maze goWest() {
        return go(Direction.WEST);
    }

    /**
     * Move the current position into the specified direction, and sets the status of cell at the
     * new position to 'visited'.
     * @throws IllegalStateException in case the current cell is not open into the specified direction
     * @param direction (NORTH, EAST,SOUTH, WEST)
     * @return the updated maze
     */
    public Maze go(Direction direction) {
        int x = position.getValue0();
        int y = position.getValue1();

        switch (direction) {
            case NORTH -> {
                if (!canGoNorth(x, y))
                    throw new IllegalStateException("Cannot go north from " + position);
                y += 1;
            }
            case EAST -> {
                if (!canGoEast(x, y))
                    throw new IllegalStateException("Cannot go east from " + position);
                x += 1;
            }
            case SOUTH -> {
                if (!canGoSouth(x, y))
                    throw new IllegalStateException("Cannot go south from " + position);
                y -= 1;
            }
            case WEST -> {
                if (!canGoWest(x, y))
                    throw new IllegalStateException("Cannot go west from " + position);
                x -= 1;
            }
        }
        position = Pair.with(x, y);
        grid[y][x] = Cell.setVisited(grid[y][x]);

        return this;
    }

    /**
     * Clears the 'visited' state of all cells of the grid, and sets the position to the original starting position
     * specified during creation of the maze.
     * @return the updated maze
     */
    public Maze clear() {
        for (int y = 0; y < grid.length;y++)
            for (int x = 0; x < grid[y].length; x++)
                grid[y][x] = Cell.unvisit(grid[y][x]);

        position = startingPosition;
        grid[position.getValue1()][position.getValue0()] =
                Cell.setVisited(grid[position.getValue1()][position.getValue0()]);

        return this;
    }

    /**
     * Checks if from the specified position the route to the north is open.
     * The provided coordinates must be in the maze
     * @param x x-coordinate to check
     * @param y y-coordinate to check
     * @return true if the route to the north is open, false otherwise
     */
    public boolean canGoNorth(int x, int y) {
        contains(x, y);

        return Cell.northOpen(grid[y][x]);
    }

    /**
     * Checks if from the specified position the route to the east is open.
     * The provided coordinates must be in the maze
     * @param x x-coordinate to check
     * @param y y-coordinate to check
     * @return true if the route to the east is open, false otherwise
     */
    public boolean canGoEast(int x, int y) {
        contains(x, y);

        return Cell.eastOpen(grid[y][x]);
    }

    /**
     * Checks if from the specified position the route to the south is open.
     * The provided coordinates must be in the maze
     * @param x x-coordinate to check
     * @param y y-coordinate to check
     * @return true if the route to the south is open, false otherwise
     */
    public boolean canGoSouth(int x, int y) {
        contains(x, y);

        return Cell.southOpen(grid[y][x]);
    }

    /**
     * Checks if from the specified position the route to the west is open.
     * The provided coordinates must be in the maze
     * @param x x-coordinate to check
     * @param y y-coordinate to check
     * @return true if the route to the west is open, false otherwise
     */
    public boolean canGoWest(int x, int y) {
        contains(x, y);

        return Cell.westOpen(grid[y][x]);
    }

    private void contains(int x, int y) {
        assert x >= 0;
        assert y >= 0;
        assert y < grid.length;
        assert x < grid[y].length;
    }

    /**
     * Returns a multi line string (each line ends with a newline character) representing the maze. The current
     * position is indicated with the character 'o'. A visited cell is indicated with an '*', and a not visited
     * cell is represented with a ' ' (blank).
     * @return a printable version  of the maze
     */
    @Override
    public String toString() {
        final StringBuilder maze = new StringBuilder(separator).append("\n");
        for (int y = 0; y < grid.length; y++) {
            final StringBuilder cellLine = new StringBuilder("|");
            final StringBuilder mazeLine = new StringBuilder("+");
            for (int x = 0; x < grid[y].length; x++) {
                final byte cell = grid[y][x];
                final String visited = Cell.visited(cell) ? "*" : " ";
                cellLine.append(y == position.getValue1() && x == position.getValue0() ? "o" : visited);
                cellLine.append(Cell.eastOpen(cell) ? (Cell.visited(grid[y][x+1]) ? visited : " ") :"|");

                mazeLine.append(Cell.northOpen(cell) ? (Cell.visited(grid[y+1][x]) ? visited : " ") : "-");
                mazeLine.append("+");
            }
            cellLine.append("\n");
            mazeLine.append("\n");

            maze.insert(0, cellLine);
            maze.insert(0, mazeLine);
        }

        return maze.toString();
    }
}
