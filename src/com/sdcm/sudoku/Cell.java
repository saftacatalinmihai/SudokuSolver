package com.sdcm.sudoku;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        this.remove_possive_value(subject.value());
    }

    public void remove_possive_value(Integer value) {
        if (this.possible_values.contains(value)) {
            this.possible_values = this.possible_values
                    .stream()
                    .filter(i -> !value.equals(i))
                    .collect(Collectors.toList());
            if (this.possible_values.size() == 1) {
                this.setValue(this.possible_values.get(0));
            }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (pos != null ? !pos.equals(cell.pos) : cell.pos != null) return false;
        Set<Integer> set1 = new HashSet<>();
        set1.addAll(this.possible_values);
        Set<Integer> set2 = new HashSet<>();
        set2.addAll(cell.possible_values);
        return set1.equals(set2);

    }

    @Override
    public int hashCode() {
        int result = pos != null ? pos.hashCode() : 0;
        result = 31 * result + (possible_values != null ? possible_values.hashCode() : 0);
//        result = 31 * result + (observers != null ? observers.hashCode() : 0);
        return result;
    }
}
