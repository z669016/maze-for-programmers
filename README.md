# Mazes for Programmers - Code Your Own Twisty Little Passages

This project provides classes to play with mazes. 

The code is based on the theory of the book [Mazes for Programmers](https://pragprog.com/titles/jbmaze/mazes-for-programmers/)

The ```Maze``` class allows a player to move through a maze and print the maze (showing a trail of visited
cells). The ```Direction``` class is used for navigation. The ```Cell``` class contains convenience methods
to manage the values of the cells (visited yes/no, directions which are open/closed).

## Generators
1. [Random Binary Tree generator](#random-binary-tree-generator)
2. [Random Sidewinder generator](#random-sidewinder-generator)

### Random Binary Tree generator
Implements the binary tree algorithm, and simply flips a coin, to decide to move north or east.

### Random Sidewinder generator
Implements the sidewinder algorithm, and flips a coin, to decide to move east or not, and if not randomly 
picks a cell to the left (in the open corridor) to open up to the north.
