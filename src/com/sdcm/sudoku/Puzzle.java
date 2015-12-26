package com.sdcm.sudoku;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by monica on 12/26/2015.
 */
public class Puzzle {
    private List<Cell> cells;
    public int size;
    public Puzzle(List<Cell> _cells, int size){
        this.cells = _cells;
        this.size = size;

        for(int i=0;i<this.size;i++){
            for (int j=0;j<this.size;j++){
                if (this.get_pos(i,j).value == 0){
                    this.cells.add(this.get_pos(i, j));
                }
            }
        }

    }

    public Cell get_pos(int i, int j){
        List<Cell> pos_list = this.cells.stream().filter(c -> c.pos.i == i && c.pos.j == j).collect(Collectors.toList());
        return pos_list.isEmpty() ? new Cell(i,j) : pos_list.get(0);
    }

    public Stream<Cell> get_line(int i){
        return this.cells.stream().filter(c -> c.pos.i == i);
    }

    public Stream<Cell> get_coll(int j){
        return this.cells.stream().filter(c -> c.pos.j == j);
    }
    public void print_line( int line_nr ) {
        get_line(line_nr).forEach(cell -> System.out.print(cell.value));
        System.out.println();
    }
    public void print_puzzle(){
        IntStream.range(0,8).forEach(this::print_line);
        System.out.println(this.size);
        System.out.println(this.cells);
    }

//    public Cell[] get_coll(int j){
//        Cell[] coll = new Cell[this.size];
//        for (int i = 0; i<this.size; i++){
//            coll[i] = this.get_line(i)[j];
//        }
//        return coll;
//    }
//
//    public Cell[] get_block(int i, int j){
//        int b_row = i/3;
//        int b_col = j/3;
//        Cell[] block = new Cell[9];
//        int index = 0;
//        for (int row = b_row+1; row< b_row+4; row++){
//            for( int col = b_col+1; col < b_col+4; col++){
//                block[index] = this.cells[row][col];
//                index++;
//            }
//        }
//        return block;
//    }
//
//    public boolean is_pos_known(int i, int j){
//        return this.get_pos(i,j) instanceof Nr;
//    }
//
//    public boolean is_unknown(int i, int j){
//        return this.get_pos(i, j) instanceof Unk;
//    }
//
//    public int[] get_constraints(int i, int j){
//        for (int test_nr=1;test_nr<=9;i++) {
//            boolean in_line = contains(this.get_line(i), test_nr );
//        }
//        return ret;
//    }
//
//    private boolean contains(final int[] array, final int key) {
//        return Arrays.asList(array).contains(key);
//    }
}
