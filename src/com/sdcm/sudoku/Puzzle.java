package com.sdcm.sudoku;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Mihai on 12/26/2015.
 *
 */
public class Puzzle {
    public final int size;
    private final List<Cell> cells;

    public Puzzle(){
        this.cells = new ArrayList<>();
        this.size = 9;
    }

    public Puzzle(List<Cell> _cells, int size){
        this.cells = _cells;
        this.size = size;

        if (!this.cells.stream().anyMatch(c -> c.value() == 0)) {
            this.cellStream().filter(c -> !c.is_val_known()).forEach(this::set_cell);
        }
        this.cellStream().filter(c -> !c.is_val_known()).forEach(this::link_dependent_cells);
        this.get_values_for_unknowns();
    }

    public Stream<Cell> cellStream (){
        if (!this.cells.stream().anyMatch(c -> c.value() == 0)) {
            List<Cell> cellList = new ArrayList<>();
            for (int i = 0; i < this.size; i++)
                for (int j = 0; j < this.size; j++)
                    cellList.add(this.get_pos(i, j));
            return cellList.stream();
        }
        return this.cells.stream();

    }

    public void get_values_for_unknowns() {
        this.cellStream()
                .filter(c -> !c.is_val_known())
                .forEach(
                (c) -> {
                    List<Integer> possible_values = this.get_possible_values(c.pos.i, c.pos.j);
                    if (possible_values.size() == 1) {
                        c.setValue(possible_values.get(0));
                    } else {
                        c.possible_values = possible_values;
                    }
                }
        );
    }

    public boolean is_solved(){
        return this.cellStream().allMatch(Cell::is_val_known);
    }

    public Puzzle clone_puzzle() {
        List<Cell> cloned_cells = this.cellStream()
                .map(c -> {
                    List<Integer> possible_values = new ArrayList<>();
                    c.possible_values.forEach(possible_values::add);
                    return new Cell(c.pos.i, c.pos.j, possible_values);
                })
                .collect(Collectors.toList());
        return new Puzzle(cloned_cells, this.size);
    }

    public void set_cell(Cell c){
        this.cells.add(c);
    }

    public Cell get_pos(int i, int j){
        List<Cell> cell_list = this.cells.stream().filter(c -> c.pos.i == i && c.pos.j == j).collect(Collectors.toList());
        return cell_list.isEmpty() ? new Cell(i, j) : cell_list.get(0);
    }

    public Stream<Cell> get_line(int i){
        return this.cellStream().filter(c -> c.pos.i == i);
    }

    public Stream<Cell> get_coll(int j){
        return this.cellStream().filter(c -> c.pos.j == j);
    }

    public Stream<Cell> get_block(int i, int j){
        int b_row = i/3;
        int b_col = j/3;
        return this.cellStream().filter(
                (c) ->  c.pos.i >= 3*b_row &&
                        c.pos.i < 3*b_row + 3 &&
                        c.pos.j >= 3*b_col &&
                        c.pos.j < 3*b_col + 3
        );
    }

    public void print_puzzle(){
        IntStream.range(0,this.size).forEach(
                (line_nr) -> {
                    if (line_nr % 3 == 0) { System.out.println("------------");}
                    IntStream.range(0,this.size).forEach(
                            (col_nr) -> {
                                Cell c = this.get_pos(line_nr, col_nr);
                                if (c.pos.j % 3 == 0) { System.out.print("|");}
                                System.out.print(c.value() == 0 ? " " : c.value());
                            }
                    );
                    System.out.println();
                });
        System.out.println();
    }

    public List<Integer> get_possible_values(int i, int j){
        Set<Integer> line = this.get_line(i).map(Cell::value).collect(Collectors.toSet());
        Set<Integer> column = this.get_coll(j).map(Cell::value).collect(Collectors.toSet());
        Set<Integer> block = this.get_block(i, j).map(Cell::value).collect(Collectors.toSet());

        Set<Integer> union = new HashSet<>();
        union.addAll(line);
        union.addAll(column);
        union.addAll(block);

        Set<Integer> possible_values = IntStream.range(1,10)
                .boxed()
                .collect(Collectors.toSet());

        possible_values.removeAll(union);

        return possible_values.stream().collect(Collectors.toList());
    }

    public void link_dependent_cells(Cell cell) {
        this.get_line(cell.pos.i)
                .filter(c -> !c.is_val_known())
                .filter(c -> c.pos.j != cell.pos.j)
                .forEach(c -> c.attach(cell));

        this.get_coll(cell.pos.j)
                .filter(c -> !c.is_val_known())
                .filter(c -> c.pos.i != cell.pos.i)
                .forEach(c -> c.attach(cell));

        this.get_block(cell.pos.i, cell.pos.j)
                .filter(c -> !c.is_val_known())
                .filter(c -> c.pos.i != cell.pos.i && c.pos.j != cell.pos.j)
                .forEach(c -> c.attach(cell));
    }

}