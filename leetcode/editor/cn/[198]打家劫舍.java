//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
// Related Topics 动态规划 
// 👍 1194 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N];  //dp数组代表第i个房子时，能获得的最多钱

        // 特殊情况
        if (nums == null || N == 0) {
            return 0;
        }
        if (N == 1) {
            return nums[0];
        }

        // 第一个dp即第一家的钱
        dp[0] = nums[0];
        // 第二个dp为：一二家的较大数
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < N; i++) {
            // 第三个之后，dp 的选择策略为：因为第i家只能选择抢不抢，若抢，则钱为dp[i-2] + nums[i]；若不抢，钱为dp[i-1]。选择这两个中的最大值就是dp[1]
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }

        return dp[N - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
标准的动态规划，只需要考虑前两个dp情况即可。难点是考虑dp的状态转移函数。
*/