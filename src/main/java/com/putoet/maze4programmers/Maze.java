package com.putoet.maze4programmers;

import org.javatuples.Pair;

public class Maze {
    private final byte[][] grid;
    private final String separator;
    private Pair<Integer,Integer> position;

    public Maze(byte[][] grid) {
        this(grid, Pair.with(0, 0));
    }

    public Maze(byte[][] grid, Pair<Integer,Integer> position) {
        assert grid != null;
        assert grid.length > 1;

        for (byte[] row : grid) {
            assert row != null;
            assert row.length == grid[0].length;
        }

        assert position.getValue1() >= 0 && position.getValue1() < grid.length;
        assert position.getValue0() >= 0 && position.getValue0() < grid[0].length;

        this.grid = grid;
        this.separator = "+" + "-+".repeat(grid[0].length);
        this.position = position;
        this.grid[position.getValue1()][position.getValue0()] =
                Cell.setVisited(grid[position.getValue1()][position.getValue0()]);
    }

    public Maze goNorth() {
        return go(Direction.NORH);
    }

    public Maze goEast() {
        return go(Direction.EAST);
    }

    public Maze goSouth() {
        return go(Direction.SOUTH);
    }

    public Maze goWest() {
        return go(Direction.WEST);
    }

    public Maze go(Direction direction) {
        int x = position.getValue0();
        int y = position.getValue1();

        switch (direction) {
            case NORH -> {
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
                    throw new IllegalStateException("Cannot go wesst from " + position);
                x -= 1;
            }
        }
        position = Pair.with(x, y);
        grid[y][x] = Cell.setVisited(grid[y][x]);

        return this;
    }

    public Maze clear() {
        for (int y = 0; y < grid.length;y++)
            for (int x = 0; x < grid[y].length; x++)
                grid[y][x] = Cell.unvisit(grid[y][x]);

        position = Pair.with(0, 0);
        grid[0][0] = Cell.setVisited(grid[0][0]);

        return this;
    }

    public boolean canGoNorth(int x, int y) {
        contains(x, y);

        return Cell.northOpen(grid[y][x]);
    }

    public boolean canGoEast(int x, int y) {
        contains(x, y);

        return Cell.eastOpen(grid[y][x]);
    }

    public boolean canGoSouth(int x, int y) {
        contains(x, y);

        return Cell.southOpen(grid[y][x]);
    }

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
