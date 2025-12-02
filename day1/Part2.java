package day1;

import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("day1/input.txt"));
        int pos = 50;        
        long total = 0L;
        for (String raw : lines) {
            if (raw == null) continue;
            String line = raw.trim();
            if (line.isEmpty()) continue;

            char dir = line.charAt(0);
            int d = Integer.parseInt(line.substring(1));

            if (dir == 'R') {
                total += (pos + d) / 100;
                pos = (pos + d) % 100;
            } else if (dir == 'L') {
                if (pos == 0) {
                    total += d / 100;
                } else {
                    if (d >= pos) {
                        total += ((d - pos) / 100) + 1;
                    } 
                }
                int newPos = (pos - (d % 100));
                newPos %= 100;
                if (newPos < 0) newPos += 100;
                pos = newPos;
            } else {
                throw new IllegalArgumentException("Bad direction: " + dir);
            }
        }
        System.out.println(total);
    }
}
