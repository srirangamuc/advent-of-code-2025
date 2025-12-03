package day3;

import java.io.*;

public class Part2 {

    private static String bestTwelve(String s) {
        int n = s.length();
        int k = 12;

        StringBuilder result = new StringBuilder(k);
        int start = 0;

        for (int i = 0; i < k; i++) {
            int end = n - (k - (i + 1)) - 1;

            char best = '0';
            int bestIndex = start;
            for (int j = start; j <= end; j++) {
                char c = s.charAt(j);
                if (c > best) {
                    best = c;
                    bestIndex = j;
                }
            }

            result.append(best);
            start = bestIndex + 1;
        }

        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java Part2 <input-file>");
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String line;
        long total = 0;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String best = bestTwelve(line);

            total += Long.parseUnsignedLong(best);
        }

        br.close();
        out.println(total);
        out.flush();
    }
}
