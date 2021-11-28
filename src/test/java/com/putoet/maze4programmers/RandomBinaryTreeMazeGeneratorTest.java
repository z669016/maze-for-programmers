package com.putoet.maze4programmers;

import org.javatuples.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomBinaryTreeMazeGeneratorTest {

    @Test
    void of() {
        final Maze maze = RandomBinaryTreeMazeGenerator.getInstance().of(10, 5);
        assertEquals(Pair.with(0, 0), maze.position());
        assertEquals(Pair.with(10, 5), maze.size());

//        System.out.println(maze);
    }
}