package com.sdcm.sudoku;

/**
 * Created by mihai on 03.01.2016.
 *
 *
 */
public class MaybeSolvedPuzzle {
    public final Puzzle puzzle;
    public final boolean unsolvable;
    public MaybeSolvedPuzzle(Puzzle puzzle, boolean unsolvable) {
        this.puzzle = puzzle;
        this.unsolvable = unsolvable;
    }
}
