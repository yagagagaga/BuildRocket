package algorithm.math;

public final class Maths {
    /**
     * 最大公约数(Greatest Common Divisor)
     */
    public static int gcd(int a, int b) {
        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }
        while (b != 0) {
            if (a == b) {
                return a;
            } else {
                int k = a % b;
                a = b;
                b = k;
            }
        }
        return a;
    }

    /**
     * 求最小公倍数(Least Common Multiple)
     */
    public static int lcm(int a, int b) {
        int gcd = gcd(a, b);
        return a * b / gcd;
    }

    /**
     * 判断是否质数
     */
    public static boolean isPrime(int a) {
        double sqrt = Math.sqrt(a);
        for (int i = 2; i <= sqrt; i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否质数：
     * 质数分布的规律：大于等于5的质数一定和6的倍数相邻。例如5和7，11和13，17和19等等；
     */
    public static boolean isPrime2(int a) {
        if (a == 2 || a == 3) return true;
        if (a % 6 != 1 && a % 6 != 5) return true;
        double sqrt = Math.sqrt(a);
        for (int i = 5; i <= sqrt; i += 6) {
            if (a % i == 0 || a % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从n个不同元素中取出m（m≤n）个元素的所有排列的个数，叫做从n个不同元素中取出m个元素的排列数，用符号 A（n,m）表示。
     * @param m 取 m 个元素
     * @param n 有 n 个不同的元素
     */
    public static long A(int m, int n) {
        long ans = 1;
        for (int i = n; i > m; i--) {
            ans *= i;
        }
        return ans;
    }

    /**
     * 从n个不同元素中取出m（m≤n）个元素的所有组合的个数，叫做从n个不同元素中取出m个元素的组合数。用符号 C（n,m） 表示。
     * @param m 取 m 个元素
     * @param n 有 n 个不同的元素
     */
    public static long C(int m, int n) {
        return A(m, n) / factorial(m);
    }

    /**
     * 计算阶乘
     * @param n n 必须大于0
     */
    public static long factorial(int n) {
        long ans = 1;
        for (int i = n; i > 1; i--) {
            ans *= i;
        }
        return ans;
    }
}
