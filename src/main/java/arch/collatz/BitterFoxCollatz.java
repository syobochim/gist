package arch.collatz;

/**
 * Created by syobochim on 14/11/16.
 */
public class BitterFoxCollatz {
    private static class Resolver
    {
        int[] result;
        int length;
        int n;

        private Resolver(int n)
        {
            this.n = n;
            result = new int[n*4];
            length = result.length;
        }

        public int resolve()
        {
            int maxI = 1;
            int max = 1;

            for (int i = 2; i <= n; i++)
            {
                if (result[i] == 0) // エラトステネスの篩的な発想
                {
                    int count = resolveSub(i);
                    if (count > max)
                    {
                        maxI = i;
                        max = count;
                    }
                }
            }

            return maxI;
        }

        public int resolveSub(int i)
        {
            if (i < length)
            {
                int r = result[i];
                if (r != 0 || i == 1)
                {
                    return r;
                }
                else
                {
                    int j = (i & 1) == 0
                            ? i >> 1
                            : (i << 1) + i + 1;
                    return result[i] = resolveSub(j) + 1;
                }
            }
            else
            {
                int j = (i & 1) == 0
                        ? i >> 1
                        : (i << 1) + i + 1;
                return resolveSub(j) + 1;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long start = System.nanoTime();
        //new Resolver(100_000).resolve();
        System.out.println(new Resolver(100_000).resolve());
        System.out.println((System.nanoTime() - start) / 1_000_000);
    }

}
