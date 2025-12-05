package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("day5/input.txt"));
        List<long[]> ranges = new ArrayList<>();

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) break; 
            String[] p = line.split("-");
            ranges.add(new long[] {
                Long.parseLong(p[0]),
                Long.parseLong(p[1])
            });
        }
        ranges.sort((a, b) -> Long.compare(a[0], b[0]));
        List<long[]> merged = new ArrayList<>();
        long[] current = ranges.get(0);
        for (int i = 1; i < ranges.size(); i++) {
            long[] next = ranges.get(i);
            if (next[0] <= current[1] + 1) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                merged.add(current);
                current = next;
            }
        }
        merged.add(current);
        long totalFresh = 0;
        for (long[] r : merged) {
            totalFresh += (r[1] - r[0] + 1);
        }

        System.out.println(totalFresh);
    }
}
