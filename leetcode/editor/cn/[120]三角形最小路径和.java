//给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。 
//
// 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。 
//
// 
//
// 例如，给定三角形： 
//
// [
//     [2],
//    [3,4],
//   [6,5,7],
//  [4,1,8,3]
//]
// 
//
// 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。 
//
// 
//
// 说明： 
//
// 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。 
// Related Topics 数组 动态规划 
// 👍 651 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int N = triangle.size();    //获取list的长宽高
        int[][] dp = new int[N][N];

        //初始状态
        dp[0][0] = triangle.get(0).get(0);

        //状态转移
        for (int i = 1; i < N; i++) {           //行遍历
            //第一个数永远是上一行第一个数+自己
            dp[i][0] = dp[i-1][0] + triangle.get(i).get(0);

            for (int j = 1; j < i; j++) {       //列遍历
                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]) + triangle.get(i).get(j);
            }

            //最后一个数
            dp[i][i] = dp[i-1][i-1] + triangle.get(i).get(i);
        }

        //获得最小数
        int result = dp[N-1][0];
        for (int i = 0; i < N; i++) {
            result = Math.min(result, dp[N-1][i]);
        }
        return result;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
标准动态规划，不过需要各个边界的处理很谨慎
*/