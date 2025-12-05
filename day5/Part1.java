package day5;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part1{
    public static void main(String[] args) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(("day5/input.txt")));
        List<long[]> ranges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        boolean afterBlank = false;
        for(String line: lines){
            line = line.trim();
            if(line.isEmpty()){
                afterBlank = true;
                continue;
            }
            if(!afterBlank){
                String[] p = line.split("-");
                ranges.add(new long[]{
                    Long.parseLong(p[0]),
                    Long.parseLong(p[1])
                });
            }else{
                ids.add(Long.parseLong(line));
            }
        }
        int freshcount = 0;
        for(long id: ids){
            boolean fresh = false;
            for(long[] r: ranges){
                if(id >= r[0] && id <= r[1]){
                    fresh = true;
                    break;
                }
            }
            if(fresh) freshcount++;
        }
        System.out.println(freshcount);
    }
}