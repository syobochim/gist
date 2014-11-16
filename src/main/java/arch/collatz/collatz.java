package arch.collatz;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class collatz {
    public static void main(String[] args) {
        long start = System.nanoTime();
        int[] result = new int[100_000];
        result[1] = 0;
        countCollatz(1, 0, result);

        /*
        int maxCount = 0;
        int maxI = 0;
        for (int i = 2; i <= 100000; i++) {
           int count = countCollatz(i);
            maxCount = Math.max(count, maxCount);
            if(maxCount == count) {
                maxI = i;
            }
        }
        */
//        int[] reduce = IntStream.rangeClosed(2, 100000).mapToObj(i -> new int[]{
//                i, countCollatz(i)
//        }).reduce(new int[]{0, 0}, (a, b) -> a[1] > b[1] ? a : b);
        long end = System.nanoTime();
        // System.out.printf("%d %d: %d(nano)%n", maxI, maxCount,(end - start));
    }
    public static int countCollatz(int target) {
        int count = 0;
        while (target != 1) {
            if ((target & 1)==0) {
                target = target >> 1;
            } else {
                target = (target * 3 + 1);
            }
            count++;
        }
        return count;
    }


    public void count(int length) {
        int[] result = new int[length];
        int count = 0;


    }

    private static void countCollatz(int after, int count, int[] result) {
        if (after > 4 && after % 2 == 0 && after % 3 == 1) {
            count++;
            if (after * 2 < result.length) {
                int before = after * 2;
                result[before] = count;
                countCollatz(before, count, result);
            }
            int before = (after - 1) / 3;
            result[before] = count;
            countCollatz(before, count, result);
        } else {
            int before = after * 2;
            if (before < result.length) {
                count++;
                result[before] = count;
                countCollatz(before, count, result);
            }
        }
    }
    private int[] reverseCollatz(int after) {
        if (after >= 4 && after % 3 == 1) {
            return new int[] {after * 2, (after - 1) / 3};
        } else {
            return new int[] {after * 2};
        }
    }
}
