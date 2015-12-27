package com.sdcm.sudoku;
import java.util.List;

/**
 * Created by Mihai on 12/26/2015.
 */
public class Solver {

    public static Puzzle get_values_for_unknowns(Puzzle p) {
        for (int i=0;i<p.size;i++) {
            for (int j = 0; j < p.size; j++) {
                Cell c = p.get_pos(i, j);
                if (!c.is_val_known()) {
                    List<Integer> possible_values = p.get_possible_values(i, j);
//                    System.out.println("Pos: [" + c.pos.i + ":" + c.pos.j +"]");
//                    possible_values.stream().forEach(System.out::print);
//                    try{System.in.read();} catch (IOException e) {e.printStackTrace();}
                    if (possible_values.size() == 1) {
                        System.out.println("Set [" + c.pos.i + ":" + c.pos.j + "] = " + possible_values.get(0));
                        c.value = possible_values.get(0);
                        p.set_cell(c);
                    } else {
                        c.possible_values = possible_values;
                        p.set_cell(c);
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
}

