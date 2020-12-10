//给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 注意: 
//
// 
// 每个数组中的元素不会超过 100 
// 数组的大小不会超过 200 
// 
//
// 示例 1: 
//
// 输入: [1, 5, 11, 5]
//
//输出: true
//
//解释: 数组可以分割成 [1, 5, 5] 和 [11].
// 
//
// 
//
// 示例 2: 
//
// 输入: [1, 2, 3, 5]
//
//输出: false
//
//解释: 数组不能分割成两个元素和相等的子集.
// 
//
// 
// Related Topics 动态规划 
// 👍 619 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums){
            sum += num;
        }

        //奇数的时候无法满足
        if(sum % 2 != 0)
            return false;

        //dp数组定义为，前i个数能否恰好组成n
        boolean[][] dp = new boolean[n+1][sum/2];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < sum/2; j++) {
                if(j == 0) dp[i][j] = true;

                if(j - nums[i-1] < 0){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i-1][j] | dp[i-1][j-nums[i-1]];
                }
            }
        }
        return dp[n][sum/2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路

*/