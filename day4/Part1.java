package day4;
import java.io.*;
import java.util.*;

public class Part1 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("day4/input.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        List<String> grid = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (!line.isEmpty()) {
                grid.add(line);
            }
        }

        br.close();

        int rows = grid.size();
        int cols = grid.get(0).length();
        int accessible = 0;

        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid.get(i).charAt(j) != '@') continue;

                int count = 0;

                for (int d = 0; d < 8; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];

                    if (ni < 0 || nj < 0 || ni >= rows || nj >= cols) continue;

                    if (grid.get(ni).charAt(nj) == '@')
                        count++;
                }

                if (count < 4)
                    accessible++;
            }
        }

        pw.println(accessible);
        pw.close();
    }
}
