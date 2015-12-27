import com.sdcm.sudoku.Rezolvator;
import org.junit.BeforeClass;
import org.junit.Test;
import com.sdcm.sudoku.Cell;
import com.sdcm.sudoku.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by Mihai on 12/26/2015.
 */
public class PuzzleTest {

    private static Puzzle p;
    @BeforeClass
    public static void setUp(){
        List<Cell> cells = new ArrayList<Cell>();
        List<Cell> cells_to_add = Arrays.asList(
                new Cell(0,1,4),
                new Cell(0,4,3),
                new Cell(0,8,6),
                new Cell(1,0,3),
                new Cell(1,2,1),
                new Cell(1,5,4),
                new Cell(1,8,8),
                new Cell(2,0,5),
                new Cell(2,1,9),
                new Cell(2,2,8),
                new Cell(2,5,1),
                new Cell(2,8,2),
                new Cell(3,0,2),
                new Cell(3,4,6),
                new Cell(3,6,3),
                new Cell(3,8,4),
                new Cell(4,2,4),
                new Cell(4,3,3),
                new Cell(4,5,5),
                new Cell(4,6,6),
                new Cell(5,0,9),
                new Cell(5,2,6),
                new Cell(5,4,1),
                new Cell(5,8,7),
                new Cell(6,0,4),
                new Cell(6,3,1),
                new Cell(6,6,8),
                new Cell(6,7,6),
                new Cell(6,8,3),
                new Cell(7,0,1),
                new Cell(7,3,7),
                new Cell(7,6,2),
                new Cell(7,8,5),
                new Cell(8,0,6),
                new Cell(8,4,8),
                new Cell(8,7,4)
        );
        cells.addAll(cells_to_add);
        p = new Puzzle(cells, 9);
    }

    @Test
    public void testGetFunctions(){

        assertEquals(p.get_pos(0,4).value, new Integer(3));
        assertEquals(p.get_pos(0,1).value, new Integer(4));

        List<Cell> line1 = p.get_line(0).collect(Collectors.toList());
        line1.forEach(c -> System.out.println("Value at i:" + c.pos.i + " j:" + c.pos.j + " -> " + c.value));

        p.get_pos(0,2);

        List<Integer> block11 = Arrays.asList(
                new Integer(6),
                new Integer(3),
                new Integer(5),
                new Integer(1)
        );
        List<Integer> test_block1 = p.get_block(3,3).map(c -> c.value).collect(Collectors.toList());
        assertTrue(block11.containsAll(test_block1));
        List<Integer> test_block2 = p.get_block(3,4).map(c -> c.value).collect(Collectors.toList());
        assertTrue(block11.containsAll(test_block2));

        List<Integer> block00 = Arrays.asList(
                new Integer(4),
                new Integer(3),
                new Integer(1),
                new Integer(5),
                new Integer(9),
                new Integer(8)
        );
        List<Integer> test_block3 = p.get_block(0,0).map(c -> c.value).collect(Collectors.toList());
        assertTrue(block00.containsAll(test_block3));

        List<Integer> test_block4 = p.get_block(2,2).map(c -> c.value).collect(Collectors.toList());
        assertTrue(block00.containsAll(test_block3));

        List<Integer> block32 = Arrays.asList(
                new Integer(1),
                new Integer(7),
                new Integer(8)
        );
        List<Integer> test_block5 = p.get_block(8,5).map(c -> c.value).collect(Collectors.toList());
        assertTrue(block32.containsAll(test_block5));

        List<Integer> test_block6 = p.get_block(6,4).map(c -> c.value).collect(Collectors.toList());
        assertTrue(block32.containsAll(test_block6));
    }

    @Test
    public void testPrint(){
        p.print_puzzle();
    }

    @Test
    public void testSolve(){
        Puzzle p_rezolvat = Rezolvator.rezolva(p);
        p_rezolvat.print_puzzle();
        assertEquals(p_rezolvat.get_pos(0,0).value, new Integer(7));
    }
}
