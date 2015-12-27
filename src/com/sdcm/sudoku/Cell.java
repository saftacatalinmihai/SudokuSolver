package com.sdcm.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mihai on 12/26/2015.
 *
 */
public class Cell {
    public Pos pos;
    public List<Integer> possible_values;
    private List<Cell> observers = new ArrayList<>();

    public Cell(int i, int j) {
        this.pos = new Pos(i, j);
        this.possible_values = new ArrayList<>();
    }

    public Cell(int i, int j, int val) {
        this.pos = new Pos(i, j);
        this.possible_values = val == 0 ? new ArrayList<>() : Collections.singletonList(val);
    }

    public Cell(int i, int j, List<Integer> possible_values) {
        this.pos = new Pos(i, j);
        this.possible_values = possible_values;
    }

    public void update(Cell subject){
        this.possible_values = this.possible_values
                .stream()
                .filter(i -> !subject.value().equals(i))
                .collect(Collectors.toList());
        if (this.possible_values.size() == 1) {
            this.setValue(this.possible_values.get(0));
        }
    }

    public Integer value() {
        if (this.possible_values.size() == 1) {
            return this.possible_values.get(0);
        } else {
            return 0;
        }
    }

    public void setValue (Integer value){
//        System.out.println("Set [" + this.pos.i + ":" + this.pos.j + "] = " + value);
        this.possible_values = Collections.singletonList(value);
        notifyAllObservers();
    }

    public void attach(Cell observer) {
        if (!this.observers.contains(observer)) {
            this.observers.add(observer);
            observer.attach(this);
        }

    }

    public void detach(Cell observer) {
        if (this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    public void notifyAllObservers() {
        for (Cell observer : this.observers) {
            observer.detach(this);
        }

        for (Cell observer : this.observers) {
            observer.update(this);
        }
    }

    public boolean is_val_known(){
        return this.value() != 0;
    }
}
