package day2;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String input = Files.readString(Paths.get("day2/input.txt")).trim();
        long sum = 0;
        int count = 0;
        for (String range : input.split(",")) {
            range = range.trim();
            if (range.isEmpty()) continue;

            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);

            for (long n = start; n <= end; n++) {
                if (isRepeated(n)) {
                    sum += n;
                    count++;
                }
            }
        }
        System.out.println("Total Sum = " + sum);
        System.out.println("Count = " + count);
    }
    static boolean isRepeated(long num) {
        String s = Long.toString(num);
        int len = s.length();
        for (int block = 1; block <= len / 2; block++) {
            if (len % block != 0) continue;
            String base = s.substring(0, block);
            int times = len / block;
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < times; i++) {
                sb.append(base);
            }
            if (sb.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
