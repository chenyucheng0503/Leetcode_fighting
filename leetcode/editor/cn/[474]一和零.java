//给你一个二进制字符串数组 strs 和两个整数 m 和 n 。 
//
// 
// 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。 
//
// 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
//输出：4
//解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
//其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 
//n 的值 3 。
// 
//
// 示例 2： 
//
// 
//输入：strs = ["10", "0", "1"], m = 1, n = 1
//输出：2
//解释：最大的子集是 {"0", "1"} ，所以答案是 2 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 600 
// 1 <= strs[i].length <= 100 
// strs[i] 仅由 '0' 和 '1' 组成 
// 1 <= m, n <= 100 
// 
// Related Topics 动态规划 
// 👍 289 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {ii
        // 初始dp
        int[][] dp = new int[m+1][n+1];

        for (String s: strs) {
            int[] count  = findZerOne(s);
            for (int zeroes = m; zeroes >= count[0]; zeroes--)
                for (int ones = n; ones >= count[1]; ones--)
                    //从后往前回退，每次碰到一个就判断是不是要加进子集中
                    dp[zeroes][ones] = Math.max(1 + dp[zeroes - count[0]][ones - count[1]], dp[zeroes][ones]);
        }
        return dp[m][n];
    }

    public int[] findZerOne(String str){
        int len = str.length();
        int[] res = new int[2];
        res[0] = 0; res[1] = 0;
        for (int i = 0; i < len; i++) {
            res[str.charAt(i)-'0']++;
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
将0、1都看成物品，本题可以看作是一个二维的背包问题。
*/