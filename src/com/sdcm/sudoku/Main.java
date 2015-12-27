package com.sdcm.sudoku;

public class Main {

    public static void main(String[] args) {
        Solver.solve(ReadFromFile.readPuzzle("test/puzzle1.txt")).print_puzzle();
    }
}
