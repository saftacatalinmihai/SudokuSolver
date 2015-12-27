package com.sdcm.sudoku;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mihai on 12/26/2015.
 * )
 */
public class Solver {

    public static Puzzle get_values_for_unknowns(Puzzle p) {
        for (int i=0;i<p.size;i++) {
            for (int j = 0; j < p.size; j++) {
                Cell c = p.get_pos(i, j);
                if (!c.is_val_known()) {
                    List<Integer> possible_values = p.get_possible_values(i, j);
                    if (possible_values.size() == 1) {
                        c.setValue(possible_values.get(0));
                    } else {
                        c.possible_values = possible_values;
                    }
                }
            }
        }
        return p;
    }

    public static Puzzle solve(Puzzle p) {
        p = get_values_for_unknowns(p);
        while (!p.is_solved()) {
            p = get_values_for_unknowns(p);
        }
        return p;
    }

    public static Puzzle solve2(Puzzle p) {
        p.print_puzzle();
        p = get_values_for_unknowns(p);

        List<Cell> cells_ordered_by_least_possibilities = p.get_cells()
                .stream()
                .filter((c) -> !c.is_val_known())
                .sorted((c1, c2) -> Integer.compare(c1.possible_values.size(), c2.possible_values.size()))
                .collect(Collectors.toList());

        for (Cell c : cells_ordered_by_least_possibilities) {
            for (Integer try_val : c.possible_values) {
                Puzzle test_puzzle = p.clone_puzzle();
                test_puzzle.get_pos(c.pos.i, c.pos.j).setValue(try_val);
                if (test_puzzle.is_solved()) {
                    return test_puzzle;
                }
            }
        }
        return p;
    }

}

