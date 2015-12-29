package com.sdcm.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Mihai on 12/26/2015.
 * )
 */
public class Solver {

    public static Puzzle solve_dumb(Puzzle p) {
        while (!p.is_solved()) {
            p.get_values_for_unknowns();
        }
        return p;
    }

    public static Puzzle solve(Puzzle p) {
        return _solve(p, new ArrayList<>());
    }

    private static Puzzle _solve(Puzzle p, ArrayList<Cell> tried) {
        p.print_puzzle();
        if(p.is_solved()) {return p;}

        List<Cell> cells_ordered_by_least_possibilities = p.cellStream()
                .filter((c) -> !c.is_val_known())
                .map( c -> {
//                    if (tried.stream().anyMatch(cell -> c.pos.equals(cell.pos))){
                    Stream<Integer> tried_vals_for_cell = tried.stream()
                            .filter(tried_cell -> tried_cell.pos.equals(c.pos))
                            .map(Cell::value);

                    tried_vals_for_cell.forEach(c::remove_possive_value);
//                    }
                    return c;
                })
//                .filter((c) -> tried.stream()
//                        .anyMatch( cell ->  c.pos.equals(cell.pos) && c.value().equals(cell.value())))
                .sorted((c1, c2) -> Integer.compare(c1.possible_values.size(), c2.possible_values.size()))
                .collect(Collectors.toList());

        if (cells_ordered_by_least_possibilities.size() > 0) {
            for (Cell c : cells_ordered_by_least_possibilities) {
                for (Integer try_val : c.possible_values) {
                    Puzzle test_puzzle = p.clone_puzzle();
                    test_puzzle.get_pos(c.pos.i, c.pos.j).setValue(try_val);
                    if (test_puzzle.is_solved()) {
                        return test_puzzle;
                    }
                }
            }
        }
        if(p.is_solved()) {return p;}

        if (cells_ordered_by_least_possibilities.size() > 0) {
            for (Cell c : cells_ordered_by_least_possibilities) {
                for (Integer try_val : c.possible_values) {
                    Puzzle test_puzzle = p.clone_puzzle();
                    test_puzzle.get_pos(c.pos.i, c.pos.j).setValue(try_val);
                    tried.add(new Cell(c.pos.i, c.pos.j, try_val));
                    Puzzle solved = _solve(test_puzzle, tried);
                    if (solved.is_solved()) {
                        return solved;
                    }
                }
            }
        }

        return p;
    }

}

