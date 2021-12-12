package com.putoet.maze4programmers;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SouthWestRandomBinaryTreeMazeGeneratorTest {

    @Test
    void of() {
        final Maze maze = SouthWestRandomBinaryTreeMazeGenerator.getInstance().of(10, 5, Pair.with(9, 4));
        assertEquals(Pair.with(9, 4), maze.position());
        assertEquals(Pair.with(10, 5), maze.size());

        System.out.println(maze);
    }
}