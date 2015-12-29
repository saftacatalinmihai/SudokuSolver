package com.sdcm.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mihai on 12/26/2015.
 * )
 */
public class Solver {

    public static Puzzle solve(Puzzle p) {
        p.get_values_for_unknowns();
        while (!p.is_solved()) {
            p.get_values_for_unknowns();
        }
        return p;
    }

    public static Puzzle solve2(Puzzle p) {
        p.print_puzzle();
        p.get_values_for_unknowns();
        if(p.is_solved()) {return p;}

        List<Cell> cells_ordered_by_least_possibilities = p.get_cells()
                .stream()
                .filter((c) -> !c.is_val_known())
                .sorted((c1, c2) -> Integer.compare(c1.possible_values.size(), c2.possible_values.size()))
                .collect(Collectors.toList());

        if (cells_ordered_by_least_possibilities.size() > 0) {
            for (Cell c : cells_ordered_by_least_possibilities) {
                for (Integer try_val : c.possible_values) {
                    Puzzle test_puzzle = p.clone_puzzle();
                    test_puzzle.get_pos(c.pos.i, c.pos.j).setValue(try_val);
                    if (test_puzzle.is_solved()) {
                        return test_puzzle;
//                    }
                    } else {
                        Puzzle test_puzzle2 = p.clone_puzzle();
                        test_puzzle2.get_pos(c.pos.i, c.pos.j).remove_possive_value(try_val);
                        Puzzle solved = solve2(test_puzzle2);
                        if (solved.is_solved()) {return solved;}
                    }
                }
            }
        }
        return p;
//        return new Puzzle(new ArrayList<>(), 9);
    }

}

