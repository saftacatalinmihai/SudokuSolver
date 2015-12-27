package com.sdcm.sudoku;
import java.util.List;

/**
 * Created by monica on 12/26/2015.
 */
public class Rezolvator {

    public static Puzzle get_vals_for_unks(Puzzle p) {
        for (int i=0;i<p.size;i++) {
            for (int j = 0; j < p.size; j++) {
                Cell c = p.get_pos(i, j);
                if (!c.is_val_knwon()) {
                    List<Integer> posible_vals = p.get_possible_values(i, j);
//                    System.out.println("Pos: [" + c.pos.i + ":" + c.pos.j +"]");
//                    posible_vals.stream().forEach(System.out::print);
//                    try{System.in.read();} catch (IOException e) {e.printStackTrace();}
                    if (posible_vals.size() == 1) {
                        System.out.println("Set [" + c.pos.i + ":" + c.pos.j + "] = " + posible_vals.get(0));
                        c.value = posible_vals.get(0);
                        p.set_cell(c);
                    } else {
                        c.posible_vals = posible_vals;
                        p.set_cell(c);
                    }
                }
            }
        }
        return p;
    }

    public static Puzzle rezolva(Puzzle p) {
        p = get_vals_for_unks(p);
        while (!p.is_solved()) {
            p = get_vals_for_unks(p);

        }
        return p;
    }
}

