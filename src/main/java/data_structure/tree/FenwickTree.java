package data_structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 树状数组（Binary Index Tree, BIT），其初衷是解决数据压缩里的累积频率（Cumulative Frequency）的计算问题，
 * 现多用于高效计算数列的前缀和，区间和。
 * @see <a href="https://zhuanlan.zhihu.com/p/93795692">https://zhuanlan.zhihu.com/p/93795692</a>
 */
public class FenwickTree {

    private final List<Integer> elems = new ArrayList<>();

    public FenwickTree() {}

    public FenwickTree(int[] data) {
        for (int datum : data) {
            add(datum);
        }
    }

    private int lowbit(int x) {return x & (-x);}

    public void add(int val) {
        if (elems.isEmpty()) {
            elems.add(val);
        } else {
            elems.add(0);
            int n = elems.size();
            int query = query(n - 1, n - 2);
            elems.set(n - 1, query + val);
        }
    }

    public void update(int idx, int val) {
        int old = query(idx - 1, idx);
        int diff = val - old;

        while (idx < elems.size()) {
            int query = query(idx);
            elems.set(idx, query + diff);
            idx += lowbit(idx + 1);
        }
    }

    public int query(int start, int end) {
        return query(end) - query(start);
    }

    public int query(int n) {
        int ans = 0;
        for (int pos = n + 1; pos > 0; pos -= lowbit(pos)) {
            ans += elems.get(pos - 1);
        }
        return ans;
    }
}
