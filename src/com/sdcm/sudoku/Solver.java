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
        MaybeSolvedPuzzle solved_candidate = _solve(p, cells_ordered_by_least_possibilities);
        if (!solved_candidate.is_solved){
            return new Puzzle();
        } else {
            return solved_candidate.puzzle;
        }
    }

    private static MaybeSolvedPuzzle _solve(Puzzle p, List<Cell> cells_to_try) {

        if(p.is_solved()) { return new MaybeSolvedPuzzle(p, true);}
        if (cells_to_try.size() == 0 ) {return  new MaybeSolvedPuzzle(null, false);}

        Cell c = cells_to_try.remove(0);
        for (Integer try_val : c.possible_values) {
            Puzzle test_puzzle = p.clone_puzzle();
            test_puzzle.get_pos(c.pos.i, c.pos.j).setValue(try_val);
            if (test_puzzle.is_solved()) {
                return new MaybeSolvedPuzzle(test_puzzle, true);
            }
        }

        for (Integer try_val : c.possible_values) {
            Puzzle test_puzzle = p.clone_puzzle();
            test_puzzle.get_pos(c.pos.i, c.pos.j).setValue(try_val);

            MaybeSolvedPuzzle solved_candidate = _solve(test_puzzle, get_cells_to_try(test_puzzle));
            if (!solved_candidate.is_solved){
                continue;
            }
            if (solved_candidate.puzzle.is_solved()){
                return new MaybeSolvedPuzzle(solved_candidate.puzzle, true);
            }
        }

        return new MaybeSolvedPuzzle(null, false);
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