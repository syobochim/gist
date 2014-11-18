package arch.collatz;

import java.util.concurrent.TimeUnit;

public class Backpaper0Collatz {
    public static int[] resolve(final int size) {
        final int[] cache = new int[size + 1];
        final int cacheSize = cache.length;

        int m = 0;
        int max = 0;

        for (int i = 2; i != size; i++) {

            //1ステップ目は絶対に1ではないしキャッシュにない
            int n;
            int step;
            if ((i & 1) == 0) {
                n = i >> 1;
                step = 1;
            } else {
                n = ((i << 1) + i + 1) >> 1;
                step = 2;
            }

            while (n != 1) {
                final int o = n < cacheSize ? cache[n] : 0;
                if (o == 0) {
                    if ((n & 1) == 0) {
                        n = n >> 1;
                        step++;
                    } else {
                        n = ((n << 1) + n + 1) >> 1;
                        step += 2;
                    }
                } else {
                    //キャッシュにヒットしたらその分ステップを足して終わる
                    step += o;
                    break;
                }
            }
            cache[i] = step;
            max = max > step ? max : step;
            if (max == step) {
                m = i;
            }
        }
        return new int[] { m, max };
    }

    public static void main(String[] args) {

        final int size = 100_000;

        //何回か先に実行してJITコンパイルする(ズルい)
        for (int i = 0; i < 100; i++) {
            resolve(size);
        }

        //こっから本番
        long start = System.nanoTime();
        int[] result = resolve(size);
        long end = System.nanoTime();

        long nanos = end - start;
        long millis = TimeUnit.NANOSECONDS.toMillis(nanos);

        int n = result[0];
        int step = result[1];

        System.out.printf("%1$d %2$d(step) %3$d(msec) %4$d(nanosec)%n", n,
                step, millis, nanos);
    }
}
