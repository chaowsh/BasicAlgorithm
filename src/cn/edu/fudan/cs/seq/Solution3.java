package cn.edu.fudan.cs.seq;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Solution3 {

    /***
     * 给一个字符串S、一个字符串T，请在字符串S里找到: 包含T所有字母的最小字串
     *  s = "abobecodebanc"  t = "abc"
     *  输出: banc
     */

    public String minWindowsString(String s, String t) {
        if (s == null || t == null) return "";
        if (s.length() < t.length()) return "";
        int left = 0;
        int right = 0;
        int valid = 0;
        int start = 0;
        int length = Integer.MAX_VALUE;
        Map<Character, Integer> targetCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            if (targetCount.containsKey(c)) {
                targetCount.put(c, targetCount.get(c) + 1);
            } else {
                targetCount.put(c, 1);
            }
        }
        Map<Character, Integer> sourceCount = new HashMap<>();
        while (right < s.length()) {
            Character ch = s.charAt(right);
            right ++;

            if (targetCount.containsKey(ch)) {
                if (!sourceCount.containsKey(ch)) {
                    sourceCount.put(ch, 1);
                } else {
                    sourceCount.put(ch, sourceCount.get(ch) + 1);
                }
                if (sourceCount.get(ch) == targetCount.get(ch)) valid ++;
            }

            //表明source里满足全部的target字符啦
            while (valid == targetCount.size()) {
                if (right - left < length) {
                    length = right - left;
                    start = left;
                }
                Character st = s.charAt(left);
                left ++;
                if (targetCount.containsKey(st)) {
                    if (sourceCount.get(st) == targetCount.get(st))  valid --;
                    sourceCount.put(st, sourceCount.get(st) - 1);
                }
            }
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
    }


    /***
     * 寻找最长的无重复字符的字串 - 滑动窗口
     * 输入: pwwkew
     * 输出: 3
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 1) return -1;
        int left = 0, right = 0, ans = 0;
        Map<Character, Integer> cache = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            right ++;
            if (!cache.containsKey(c)) {
                cache.put(c, 1);
            } else {
                cache.put(c, cache.get(c) + 1);
            }

            while (cache.get((c)) > 1) {
                char d = s.charAt(left);
                left ++;
                cache.put(d, cache.get(d) - 1);
            }
            //确保窗口中没有重复字符
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    /***
     *
     * 输入: [10, 9, 2, 5, 3, 7, 101, 18]
     * 输出: 4
     * 最长的上升子序列为: [2, 3, 7, 101], 所以值为 4
     * 动态规划问题
     * dp[i] = ?
     */

    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length <= 0) return -1;
        //1. 初始化dp[i]为1
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        //2. 计算dp[i]
        for (int i=0; i < nums.length; i++) {
            for (int j=0; j<i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        //3. sort dp[i] and get the largest element
        Arrays.sort(dp);
        return dp[dp.length - 1];
    }


    /***
     * 连续子数组的最大和
     * dp[i] = dp[i-1] + nums[i], if (dp[i-1] > 0)
     * dp[i] = nums[i], if (dp[i-1]<=0)
     *
     * 输入: [-1, 3, 5,-1, 3, -7]
     * 输出: 10
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int result = nums[0];
        for (int i=1; i < nums.length; i++) {
            if (nums[i-1] > 0) {
                nums[i] = nums[i-1] + nums[i];
            }
            result = Math.max(result, nums[i]);
        }
        return result;
    }


    /***
     * 给定一个排序数组，需要在原地删除重复出现的元素，使的每个元素只出现一次，返回移出后数组的新长度
     *
     * 输入: [0,1,1,1,1,2,2,3,3,4]
     * 输出: 5
     */
    public int removerDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int slow = 0, fast = 1;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                //[0....slow]无重复
                nums[slow] = nums[fast];
            }
            fast ++;
        }
        //因为是length所以
        return slow + 1;
    }


    public static void main(String []args) {
        //String s = "abobecodebanc";
        //String t = "abc";
        Solution3 solution = new Solution3();
        //System.out.println(solution.minWindowsString(s, t));
        //String s = "pww";
        //System.out.println(solution.lengthOfLongestSubstring(s));
        //int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        //System.out.println(solution.lengthOfLIS(nums));
        //int[] nums = {-1, 3, 5,-1, 3, -7};
        //System.out.println(solution.maxSubArray(nums));

        int[] nums = {0,1,1,1,1,2,2,3,3,4};
        System.out.println(solution.removerDuplicates(nums));

    }
}
