//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 题目数据保证答案肯定是一个 32 位的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：s = "1"
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：s = "2"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 566 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        //特殊情况
        if (s.charAt(0) == '0') {
            return 0;
        }

        //dp数组为：长度为n时的排列组合种类（不断向后滚动）
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;

        if (s.length() == 1) return dp[1];

        for(int i=2;i<=s.length();i++){
            if(s.charAt(i-1)=='0'){
                dp[i] = dp[i-2];
            } else if(s.charAt(i-2)=='1' || (s.charAt(i-2)=='2' && s.charAt(i-1)<'7')){
                dp[i] = dp[i-1]+dp[i-2];
            }else{
                dp[i] = dp[i-1];
            }
        }
        return dp[s.length()];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
动态规划，主要难点在于处理边界0
if 最后一位数是0：dp[i] = dp[i-2] 因为肯定是前一位的尾数10/20
elseif 最后两位数在1-26之间：dp[i] = dp[i-1] + dp[i-2] 因为有两种情况
else dp[i] = dp[i-1] 只能是两个分开的字符，而不是一个字符
 */