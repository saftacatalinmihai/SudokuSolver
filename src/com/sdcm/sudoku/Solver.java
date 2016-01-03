package com.sdcm.sudoku;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mihai on 12/26/2015.
 * )
 */
public class Solver {

    public static Puzzle solve(Puzzle p) {
        List<Cell> cells_ordered_by_least_possibilities = get_cells_to_try(p);
        MaybeSolvedPuzzle solved = _solve(p, cells_ordered_by_least_possibilities);
        if (solved.unsolvable){
            return new Puzzle();
        } else {
            return solved.puzzle;
        }
    }

    private static MaybeSolvedPuzzle _solve(Puzzle p, List<Cell> cells_to_try) {

        if(p.is_solved()) { return new MaybeSolvedPuzzle(p, false);}
        if (cells_to_try.size() == 0 ) {return  new MaybeSolvedPuzzle(null, true);}

        Cell c = cells_to_try.remove(0);
        for (Integer try_val : c.possible_values) {
            Puzzle test_puzzle = p.clone_puzzle();
            test_puzzle.get_pos(c.pos.i, c.pos.j).setValue(try_val);
            if (test_puzzle.is_solved()) {
                return new MaybeSolvedPuzzle(test_puzzle, false);
            }
        }

        for (Integer try_val : c.possible_values) {
            Puzzle test_puzzle = p.clone_puzzle();
            test_puzzle.get_pos(c.pos.i, c.pos.j).setValue(try_val);

            MaybeSolvedPuzzle solved = _solve(test_puzzle, get_cells_to_try(test_puzzle));
            if (solved.unsolvable){
                continue;
            }
            if (solved.puzzle.is_solved()){
                return new MaybeSolvedPuzzle(solved.puzzle, false);
            }
        }

        return new MaybeSolvedPuzzle(null, true);
    }

    public static List<Cell>  get_cells_to_try(Puzzle p) {
        return  p.cellStream()
                .filter((c) -> !c.is_val_known())
                .sorted((c1, c2) -> Integer.compare(c1.possible_values.size(), c2.possible_values.size()))
                .collect(Collectors.toList());
    }

    public static Puzzle solve_dumb(Puzzle p) {
        while (!p.is_solved()) {
            p.get_values_for_unknowns();
        }
        return p;
    }

}