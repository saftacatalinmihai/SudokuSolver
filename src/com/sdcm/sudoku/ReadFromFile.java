package com.sdcm.sudoku;

import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Mihai on 12/27/2015.
 */
public class ReadFromFile {
    public static Puzzle readPuzzle(String p){
        List<Cell> cell_list = new ArrayList<>();
        try {
            List<String> lines = Files.lines(Paths.get(p)).collect(Collectors.toList());
            for (int i=0;i< lines.size();i++){
                String line = lines.get(i);
                String[] split = line.split(",");
                for (int j=0;j<split.length; j++){
                    cell_list.add(new Cell(i,j, Integer.parseInt(split[j])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Puzzle(cell_list, 9);
    }
}
