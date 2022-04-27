package algorithm.computational_geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * 计算几何学之 Andrew 算法。
 * Andrew 算法用于解决二维凸包问题，其算法逻辑将凸包分为「上凸壳」和「下凸壳」，
 */
public class Andrew {

    public static void main(String[] args) {

    }

    static class Solution {

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        Random random;

        public Solution(int[] nums) {
            random = new Random(nums.length);
            for (int i = 0; i < nums.length; i++) {
                List<Integer> list = map.computeIfAbsent(nums[i], k -> new ArrayList<>());
                list.add(i);
            }
        }

        public int pick(int target) {
            List<Integer> list = map.get(target);
            int i = random.nextInt(list.size());
            return list.get(i);
        }
    }

}