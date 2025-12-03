package day3;

import java.io.*;

public class Part1{
    private static int maxTwoDigit(String s){
        int n = s.length();
        int[] freq = new int[10];

        // Count all digits
        for (int i = 0; i < n; i++) {
            freq[s.charAt(i) - '0']++;
        }

        int best = 0;

        for (int i = 0; i < n; i++) {
            int a = s.charAt(i) - '0';
            freq[a]--;  // remove 'a' from future

            // find largest b that actually exists to the right
            for (int b = 9; b >= 0; b--) {
                if (freq[b] > 0) {
                    int val = a * 10 + b;
                    if (val > best) best = val;
                    break;
                }
            }
        }

        return best;
    }
    public static void main(String[] args) throws Exception{
        if(args.length == 0){
            System.out.println("Usage: java Part1 <input-file>");
            return;
        }
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String line;
        long total = 0;
        while((line = br.readLine())!=null){
            line = line.trim();
            if(line.isEmpty()) continue;
            total += maxTwoDigit(line);
        }
        br.close();
        out.println(total);
        out.flush();
    }
}