package cn.edu.fudan.cs.others;

import java.util.*;

public class Solution {
    /**
     * 寻找最长公共前缀
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0];
        for (int i=1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i=0; i < strs[0].length(); i++) {
            char a = strs[0].charAt(i);
            for (int j=1; j < strs.length; j++) {
                if (strs[j].length() == i || strs[j].charAt(i) != a) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return "";
    }

    /***
     * 寻找最长连续递增序列 - DP Solution
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int ans = 0, target = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i-1] >= nums[i]) {
                target = i;
            }
            ans = Math.max(ans, i - target + 1);
        }
        return ans;
    }

    /***
     * 寻找最长的无重复字符的字串 - 滑动窗口
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        int ans = 0, start = 0, end = 0;
        Set<Character> set = new HashSet<>();
        while (start < s.length()) {
            if (end < s.length() && !set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end++;
            } else {
                ans = Math.max(ans, end - start);
                set.remove(s.charAt(start));
                start ++;
            }
        }
        return ans;
    }

    /***
     * 斐波那契堆问题 - DP solution
     * 同理爬梯子问题
     * @param n
     * @return
     */
    public int fibonacci(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int result = 0;
        int pre = 1;
        int next = 2;
        for (int i = 3; i <= n; i ++) {
            result = pre + next;
            pre = next;
            next = result;
        }
        return  result;
    }

    /***
     * 斐波那契数列 - 解决重复问题
     */
    private Map<Integer, Integer> map = new HashMap<>();
    public int fibonacciRecu(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (map.get(n) != null) return map.get(n);
        int result = fibonacciRecu(n-1) + fibonacciRecu(n-2);
        map.put(n, result);
        return result;
    }


    /***
     * 全排列问题
     * 输入: num
     * 输出: 全排列
     */
    public List<List<Integer>> fullPermutation (int num) {
        List<List<Integer>> res = new ArrayList<>();
        if (num <= 0) return res;
        //1. convert num to arrays
        int []nums = new int[num];
        //2. define visitor
        boolean []visited = new boolean[num];
        for (int i= 1; i <= num; i++) {
            nums[i-1] = i;
            visited[i-1] = false;
        }
        //3. define path
        List<Integer> path = new ArrayList<>();

        //4. 递归回溯
        dfs(nums, num, 0, res, path, visited);

        return res;
    }

    public void dfs(int []nums, int length, int depth,
                    List<List<Integer>> res, List<Integer> path, boolean[] visited) {
        //5. 如果树深度==数组长度说明遍历到了叶子节点
        if (depth == length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < length; i ++) {
            if (visited[i] == false) {
                visited[i] = true;
                path.add(nums[i]);
                //6. 继续深度遍历
                dfs(nums, length, depth + 1, res, path, visited);
                //7. 回溯
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    /***
     * 最长公共子序列 - 经典DP问题
     */

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        if (text1.isEmpty() || text2.isEmpty()) return 0;
        int m = text1.length();
        int n = text2.length();
        int [][]matrix = new int[m+1][n+1];
        for (int i=1; i <= m; i++)
            for (int j=1; j <=n; j++) {
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    matrix[i][j] = matrix[i-1][j-1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i][j-1], matrix[i-1][j]);
                }
            }
        return  matrix[m][n];
    }

    /***
     * 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
     * 使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     */

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3) return ret;
        //1. sort firstly
        Arrays.sort(nums);
        for (int i=0; i<nums.length; i++) {
            //2. first number > 0, break directly, since its the sorted nums
            if (nums[i] > 0) break;
            //3. 去重
            if (i > 0 && nums[i-1] == nums[i]) continue;
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    ret.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left+1]) left++; //去重
                    while (left < right && nums[right] == nums[right-1]) right--;//去重
                    left++;
                    right--;
                } else if (sum < 0) {
                    left ++;
                } else if (sum > 0) {
                    right --;
                }
            }
        }
        return ret;
    }

    /***
     * 连续子数组的最大和
     * dp[i] = dp[i-1] + nums[i], if (dp[i-1] > 0)
     * dp[i] = nums[i], if (dp[i-1]<=0)
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int result = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (nums[i-1] > 0)
                nums[i] = nums[i-1] + nums[i];
            result = Math.max(result, nums[i]);
        }
        return result;
    }

    public static void main(String []args) {
        Solution s = new Solution();
        /*String [] strs = {"flower","f", "flow", "flight"};
        System.out.println("The longest common prefix is :" + s.longestCommonPrefix(strs));
        int [] nums = {2,3,5,4,7};
        System.out.println("The length of CIS is: " + s.findLengthOfLCIS(nums));
        String strs2 = "bbbb";
        System.out.println("The length of longest sub string is : " + s.lengthOfLongestSubstring(strs2));
        List<List<Integer>> ret = s.fullPermutation(5);
        for (List<Integer> l : ret) {
            System.out.println(l.toString());
        }*/
        int []nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(s.maxSubArray(nums));
    }
}
