package com.sdcm.sudoku;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by monica on 12/26/2015.
 */
public class Cell {
    public Pos pos;
    public Integer value;
    public List<Integer> posible_vals;

    public Cell(int i, int j) {
        this.pos = new Pos(i, j);
        this.value = 0;
        this.posible_vals = new ArrayList<>();
    }

    public Cell(int i, int j, int val){
        this.pos = new Pos(i,j);
        this.value = val;
        this.posible_vals = new ArrayList<>(val);
    }

    public Cell(int i, int j, List<Integer> possible_vals){
        this.pos = new Pos(i,j);
        this.value = 0;
        this.posible_vals = possible_vals;
    }

    public boolean is_val_knwon(){
        return this.value != 0;
    }
}
