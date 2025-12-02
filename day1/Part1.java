package day1;

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("day1/input.txt"));
        int pos = 50;
        int count = 0;

        for (String line : lines) {
            if (line == null) continue;
            line = line.trim();
            if (line.isEmpty()) continue;

            char dir = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));

            if (dir == 'R') {
                pos = (pos + value) % 100;
            } else if (dir == 'L') {
                pos = ((pos - value) % 100 + 100) % 100; // safe modulo
            }

            if (pos == 0) {
                count++;
            }
        }

        System.out.println("Password: " + count);
    }
}
