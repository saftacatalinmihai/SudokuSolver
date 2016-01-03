import com.sdcm.sudoku.Cell;
import com.sdcm.sudoku.Puzzle;
import com.sdcm.sudoku.ReadFromFile;
import com.sdcm.sudoku.Solver;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihai on 12/26/2015.
 * 
 */
public class PuzzleTest {

    private Puzzle p;
    @Before
    public void setUp(){
        List<Cell> cells = new ArrayList<>();
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
        this.p = new Puzzle(cells, 9);
    }

    @Test
    public void testGetFunctions(){

        assertEquals(p.get_pos(0, 4).value(), new Integer(3));
        assertEquals(p.get_pos(0, 1).value(), new Integer(4));

        List<Integer> block11 = Arrays.asList(6,3,5,1);

        List<Integer> test_block1 = this.p.get_block(3, 3).map(Cell::value).collect(Collectors.toList());
        assertTrue(test_block1.containsAll(block11));
        List<Integer> test_block2 = this.p.get_block(3, 4).map(Cell::value).collect(Collectors.toList());
        assertTrue(test_block2.containsAll(block11));

        List<Integer> block00 = Arrays.asList(4,3,1,5,9,8);

        List<Integer> test_block3 = p.get_block(0, 0).map(Cell::value).collect(Collectors.toList());
        assertTrue(test_block3.containsAll(block00));

        List<Integer> test_block4 = p.get_block(2, 2).map(Cell::value).collect(Collectors.toList());
        assertTrue(test_block4.containsAll(block00));

        List<Integer> block32 = Arrays.asList(1,7,8);

        List<Integer> test_block5 = p.get_block(8, 5).map(Cell::value).collect(Collectors.toList());
        assertTrue(test_block5.containsAll(block32));

        List<Integer> test_block6 = p.get_block(6, 4).map(Cell::value).collect(Collectors.toList());
        assertTrue(test_block6.containsAll(block32));
    }

    @Test
    public void testPrint(){
        p.print_puzzle();
    }

    @Test
    public void testSolveEasy() {
        Puzzle p_solved = Solver.solve_dumb(this.p);
        p_solved.print_puzzle();
        assertEquals(p_solved.get_pos(0, 0).value(), new Integer(7));
    }

    @Test
    public void testSolve2Easy() {
        Puzzle p_solved = Solver.solve(this.p);
        p_solved.print_puzzle();
        assertEquals(p_solved.get_pos(0, 0).value(), new Integer(7));
    }

    @Test
    public void testSolveMedium() {
        Puzzle p = ReadFromFile.readPuzzle("test/puzzle2.txt");
        Puzzle solved = Solver.solve(p);
        solved.print_puzzle();
        assertEquals(true, solved.is_solved());
    }

    @Test
    public void testCellConstructor() {
        Cell c = new Cell(0, 0, 1);
        assertEquals(new Integer(1), c.value());
    }

    @Test
    public void testSolve2Medium() {
        Puzzle p = ReadFromFile.readPuzzle("test/puzzle2.txt");
        Puzzle p_solved = Solver.solve(p);
        p_solved.print_puzzle();
        assertEquals(new Integer(9), p_solved.get_pos(0, 0).value());
    }

    @Test
    public void testSolve2Evil(){
        Puzzle p = ReadFromFile.readPuzzle("test/puzzle_evil.txt");
        Puzzle p_solved = Solver.solve(p);
        p_solved.print_puzzle();
        assertEquals(true, p_solved.is_solved());
        assertEquals(new Integer(8), p_solved.get_pos(0,0).value());
    }

    @Test
    public void testSolveEvil2(){
        Puzzle p = ReadFromFile.readPuzzle("test/puzzle_evil2.txt");
        Puzzle p_solved = Solver.solve(p);
        p_solved.print_puzzle();
        assertEquals(true, p_solved.is_solved());
    }

    @Test
    public void testSolveExpert(){
        Puzzle p = ReadFromFile.readPuzzle("test/puzzle_expert.txt");
        Puzzle p_solved = Solver.solve(p);
        p_solved.print_puzzle();
        assertEquals(true, p_solved.is_solved());
    }


    @Test
    public void testSolveHardest(){
        Puzzle p = ReadFromFile.readPuzzle("test/puzzle_hardest.txt");
        Puzzle p_solved = Solver.solve(p);
        p_solved.print_puzzle();
        assertEquals(true, p_solved.is_solved());
        assertEquals(new Integer(8), p_solved.get_pos(0,0).value());
    }

    @Test
    public void testSolveHard2(){
        Puzzle p = ReadFromFile.readPuzzle("test/puzzle_hard2.txt");
        Puzzle p_solved = Solver.solve(p);
        p_solved.print_puzzle();
        assertEquals(true, p_solved.is_solved());
        assertEquals(new Integer(1), p_solved.get_pos(0,0).value());
    }

    @Test
    public void testEmpty(){
        Puzzle p = new Puzzle(new ArrayList<>(), 9);
        Puzzle p_solved = Solver.solve(p);
        p_solved.print_puzzle();
        assertEquals(true, p_solved.is_solved());
    }

    @Test
    public void testSolve2Hard() {
        Puzzle p = ReadFromFile.readPuzzle("test/puzzle_hard.txt");
        Puzzle p_solved = Solver.solve(p);
        p_solved.print_puzzle();
        assertEquals(true, p_solved.is_solved());
        assertEquals(new Integer(3), p_solved.get_pos(0, 0).value());
    }

    @Test
    public void testReadPuzzleFromFile() {
        Puzzle p = ReadFromFile.readPuzzle("test/puzzle3.txt");
        Puzzle p_solved = Solver.solve(p);
        p_solved.print_puzzle();
        assertEquals(true, p_solved.is_solved());
    }

    // Don't need this anymore
    @Ignore
    @Test
    public void testEqualityPuzzles(){
        Cell cell1 = new Cell(0,0,1);
        Cell cell2 = new Cell(0,0,1);
        assertTrue(cell1.equals(cell2));

        List<Cell> c1 = Arrays.asList(
                new Cell(0,0,1), new Cell(0, 1, 2)
        );
        Puzzle p1 = new Puzzle(new ArrayList<>(c1), 2);

        List<Cell> c2 = Arrays.asList(
                new Cell(0,0,1), new Cell(0, 1, 2)
        );
        Puzzle p2 = new Puzzle(new ArrayList<>(c2), 2);
        System.out.println(p1.equals(p2));
        assertTrue(p1.equals(p2));

        ArrayList<Puzzle> tried = new ArrayList<>();
        tried.add(p1);
        assertTrue(tried.contains(p2));
    }

    @Test
    public void testListRemove(){
        List<Integer> int_list = new ArrayList<>();
        int_list.add(1);
        int_list.add(2);
        int_list.add(3);
        int_list.stream().forEach(System.out::print);
        System.out.println();

        Integer int0 = int_list.remove(0);
        System.out.println(int0);

        int_list.stream().forEach(System.out::print);
        System.out.println();

    }
}
