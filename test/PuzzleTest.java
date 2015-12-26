import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.BeforeClass;
import org.junit.Test;
import com.sdcm.sudoku.Cell;
import com.sdcm.sudoku.Puzzle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by monica on 12/26/2015.
 */
public class PuzzleTest {

    private static Puzzle p;
    @BeforeClass
    public static void setUp(){
        List<Cell> cells = new ArrayList<Cell>();
        List<Cell> cells_to_add = Arrays.asList(
                new Cell(0, 0, 4),
                new Cell(0, 1, 8),
                new Cell(0, 6, 3),
                new Cell(0, 7, 9),
                new Cell(0, 8, 7),
                new Cell(1, 2, 6),
                new Cell(1, 5, 2),
                new Cell(1, 7, 4),
                new Cell(1, 8, 8),
                new Cell(2, 3, 4),
                new Cell(2, 7, 1),
                new Cell(2, 7, 2),
                new Cell(3, 2, 3),
                new Cell(3, 4, 5),
                new Cell(3, 6, 2),
                new Cell(3, 8, 1),
                new Cell(4, 3, 2),
                new Cell(4, 5, 1),
                new Cell(5, 0, 2),
                new Cell(5, 2, 8),
                new Cell(5, 4, 6),
                new Cell(5, 6, 9),
                new Cell(6, 0, 8),
                new Cell(6, 1, 3),
                new Cell(6, 5, 6),
                new Cell(7, 0, 5),
                new Cell(7, 1, 2),
                new Cell(7, 3, 8),
                new Cell(7, 6, 1),
                new Cell(8, 0, 7),
                new Cell(8, 1, 6),
                new Cell(8, 2, 1),
                new Cell(8, 7, 3),
                new Cell(8, 8, 4)
        );
        cells.addAll(cells_to_add);
        p = new Puzzle(cells, 9);
    }
//
//    @Test
//    public void testGetLine(){
//
//        Cell[] line = this.puzzle.get_line(0);
//        for (int i=0; i<line.length; i++){
//            assertEquals(line[i].get_nr(), i+1);
//        }
//    }

    @Test
    public void testGetFunctions(){

        assertEquals(p.get_pos(0,0).value, new Integer(4));
        assertEquals(p.get_pos(0,1).value, new Integer(8));

        Stream<Cell> line1 = p.get_line(0);
        line1.forEach(c -> System.out.println("Value at i:" + c.pos.i + " j:" + c.pos.j + " -> " + c.value));

        p.get_pos(0,2);
    }

    @Test
    public void testPrint(){
        p.print_puzzle();
    }
}
