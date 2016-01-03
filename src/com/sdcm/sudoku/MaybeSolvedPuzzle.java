package com.sdcm.sudoku;

/**
 * Created by mihai on 03.01.2016.
 *
 *
 */
public class MaybeSolvedPuzzle {
    public final Puzzle puzzle;
    public final boolean is_solved;
    public MaybeSolvedPuzzle(Puzzle puzzle, boolean is_solved) {
        this.puzzle = puzzle;
        this.is_solved = is_solved;
    }
}
