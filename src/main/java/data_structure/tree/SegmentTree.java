package data_structure.tree;

import java.util.function.BiFunction;

public class SegmentTree<T> {

    private final TreeNode<T> root;
    private final BiFunction<T, T, T> merger;

    static class TreeNode<T> {
        private T merge;
        private final int start;
        private final int end;
        private TreeNode<T> left;
        private TreeNode<T> right;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int mid() {
            return start + (end - start) / 2;
        }
    }

    public SegmentTree(T[] data, BiFunction<T, T, T> merger) {
        this.merger = merger;
        this.root = buildTree(data, 0, data.length - 1);
    }

    private TreeNode<T> buildTree(T[] data, int start, int end) {
        if (start > end) {
            throw new RuntimeException("It should not be happened");
        } else if (start == end) {
            TreeNode<T> node = new TreeNode<>(start, end);
            node.merge = data[start];
            return node;
        } else {
            TreeNode<T> node = new TreeNode<>(start, end);
            int mid = start + (end - start) / 2;

            node.left = buildTree(data, start, mid);
            node.right = buildTree(data, mid + 1, end);

            node.merge = merger.apply(node.left.merge, node.right.merge);
            return node;
        }
    }

    /**
     * @param start 闭区间
     * @param end 开区间
     */
    public T query(int start, int end) {
        return queryInternal(root, start, end);
    }

    private T queryInternal(TreeNode<T> node, int left, int right) {
        if (node.start == left && node.end == right) {
            return node.merge;
        } else {
            int mid = node.mid();
            if (right <= mid) {
                return queryInternal(node.left, left, right);
            } else if (left > mid) {
                return queryInternal(node.right, left, right);
            } else {
                return merger.apply(
                        queryInternal(node.left, left, mid),
                        queryInternal(node.right, mid + 1, right));
            }
        }
    }

    public void update(int idx, T val) {
        updateInternal(root, idx, val);
    }

    private void updateInternal(TreeNode<T> node, int idx, T val) {
        if (node.left == node.right) {
            node.merge = val;
        } else {
            int mid = node.mid();
            if (idx <= mid) {
                updateInternal(node.left, idx, val);
            } else {
                updateInternal(node.right, idx, val);
            }
            node.merge = merger.apply(node.left.merge, node.right.merge);
        }
    }
}
