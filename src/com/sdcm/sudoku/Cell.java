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
    private List<Cell> observers = new ArrayList<>();

    public void update(Cell subject){
        this.posible_vals.remove(subject.value);
        if (this.posible_vals.size() == 1) {
            this.setValue(this.posible_vals.get(0));
        }
    }

    public void setValue (Integer value){
        this.value = value;
        notifyAllObservers();
    }

    public void attach(Cell observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Cell observer : observers) {
            observer.update(this);
        }
    }
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
