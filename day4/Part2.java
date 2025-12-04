package day4;
import java.io.*;
import java.util.*;

public class Part2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day4/input.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) lines.add(line);
        }
        br.close();

        if (lines.isEmpty()) {
            pw.println(0);
            pw.flush();
            pw.close();
            System.out.println(0);
            return;
        }

        int rows = lines.size();
        int cols = lines.get(0).length();
        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            String s = lines.get(i);
            if (s.length() != cols) {
                throw new IllegalArgumentException("All input lines must have same length");
            }
            grid[i] = s.toCharArray();
        }

        // 8 directions
        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};

        int totalRemoved = 0;

        while (true) {
            List<int[]> toRemove = new ArrayList<>();

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] != '@') continue;
                    int neighbors = 0;
                    for (int d = 0; d < 8; d++) {
                        int ni = i + dx[d];
                        int nj = j + dy[d];
                        if (ni < 0 || nj < 0 || ni >= rows || nj >= cols) continue;
                        if (grid[ni][nj] == '@') neighbors++;
                    }
                    if (neighbors < 4) {
                        toRemove.add(new int[]{i, j});
                    }
                }
            }

            if (toRemove.isEmpty()) break;

            // remove them simultaneously
            for (int[] p : toRemove) {
                grid[p[0]][p[1]] = '.';
            }
            totalRemoved += toRemove.size();
        }

        pw.println(totalRemoved);
        pw.flush();
        pw.close();
    }
}
