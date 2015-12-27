package com.sdcm.sudoku;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihai on 12/26/2015.
 */
public class Cell {
    public Pos pos;
    public Integer value;
    public List<Integer> possible_values;
    private List<Cell> observers = new ArrayList<>();

    public void update(Cell subject){
        this.possible_values.remove(subject.value);
        if (this.possible_values.size() == 1) {
            this.setValue(this.possible_values.get(0));
        }
    }

    public void setValue (Integer value){
        this.value = value;
        notifyAllObservers();
    }

//    public void attach(Cell observer){
//        observers.add(observer);
//    }

    public void notifyAllObservers(){
        for (Cell observer : observers) {
            observer.update(this);
        }
    }
    public Cell(int i, int j) {
        this.pos = new Pos(i, j);
        this.value = 0;
        this.possible_values = new ArrayList<>();
    }

    public Cell(int i, int j, int val){
        this.pos = new Pos(i,j);
        this.value = val;
        this.possible_values = val == 0 ? new ArrayList<>() : new ArrayList<>(val);
    }

    public Cell(int i, int j, List<Integer> possible_values){
        this.pos = new Pos(i,j);
        this.value = 0;
        this.possible_values = possible_values;
    }

    public boolean is_val_known(){
        return this.value != 0;
    }
}
