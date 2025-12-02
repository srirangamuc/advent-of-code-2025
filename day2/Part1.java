package day2;

import java.nio.file.*;
import java.math.BigInteger;
import java.io.*;

public class Part1 {
    public static void main(String[] args) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get("day2/input.txt"))).trim();
        if (text.isEmpty()) {
            System.out.println("0");
            return;
        }

        // Input: one long line with comma-separated ranges (may contain newlines)
        text = text.replace("\n", "").replace("\r", "");
        String[] ranges = text.split(",");

        BigInteger total = BigInteger.ZERO;

        for (String r : ranges) {
            r = r.trim();
            if (r.isEmpty()) continue;
            String[] parts = r.split("-");
            if (parts.length != 2) throw new IllegalArgumentException("Bad range: " + r);

            BigInteger a = new BigInteger(parts[0]);
            BigInteger b = new BigInteger(parts[1]);
            if (a.compareTo(b) > 0) { // swap if reversed just in case
                BigInteger tmp = a; a = b; b = tmp;
            }

            // Determine maximum possible digit length to consider (digits of b)
            int maxDigits = b.toString().length();

            // Only even total lengths matter (2,4,6,...)
            for (int L = 2; L <= maxDigits; L += 2) {
                int k = L / 2;
                BigInteger pow = BigInteger.TEN.pow(k);        // 10^k
                BigInteger factor = pow.add(BigInteger.ONE);  // 10^k + 1

                // s must be k-digit: [10^(k-1), 10^k - 1]
                BigInteger sLo = BigInteger.TEN.pow(k - 1);
                BigInteger sHi = pow.subtract(BigInteger.ONE);

                // s must satisfy a <= s * factor <= b
                // => ceil(a / factor) <= s <= floor(b / factor)
                BigInteger sMin = divCeil(a, factor);
                BigInteger sMax = b.divide(factor);

                // intersect with k-digit bounds
                if (sMin.compareTo(sHi) > 0 || sMax.compareTo(sLo) < 0) continue;
                BigInteger sStart = sMin.max(sLo);
                BigInteger sEnd = sMax.min(sHi);

                // iterate s from sStart to sEnd (these intervals are usually small)
                for (BigInteger s = sStart;
                     s.compareTo(sEnd) <= 0;
                     s = s.add(BigInteger.ONE)) {
                    BigInteger x = s.multiply(factor); // concatenation of s twice
                    total = total.add(x);
                }
            }
        }

        System.out.println(total.toString());
    }

    private static BigInteger divCeil(BigInteger a, BigInteger b) {
        // ceil(a / b) for positive b
        if (a.signum() == 0) return BigInteger.ZERO;
        BigInteger[] dr = a.divideAndRemainder(b);
        if (dr[1].signum() == 0) return dr[0];
        return dr[0].add(BigInteger.ONE);
    }
}
