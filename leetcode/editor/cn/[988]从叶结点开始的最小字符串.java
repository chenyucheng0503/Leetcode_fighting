//给定一颗根结点为 root 的二叉树，树中的每一个结点都有一个从 0 到 25 的值，分别代表字母 'a' 到 'z'：值 0 代表 'a'，值 1 代表 
//'b'，依此类推。 
//
// 找出按字典序最小的字符串，该字符串从这棵树的一个叶结点开始，到根结点结束。 
//
// （小贴士：字符串中任何较短的前缀在字典序上都是较小的：例如，在字典序上 "ab" 比 "aba" 要小。叶结点是指没有子结点的结点。） 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//
// 输入：[0,1,2,3,4,3,4]
//输出："dba"
// 
//
// 示例 2： 
//
// 
//
// 输入：[25,1,3,1,3,0,2]
//输出："adz"
// 
//
// 示例 3： 
//
// 
//
// 输入：[2,2,1,null,1,0,null,0]
//输出："abc"
// 
//
// 
//
// 提示： 
//
// 
// 给定树的结点数介于 1 和 8500 之间。 
// 树中的每个结点都有一个介于 0 和 25 之间的值。 
// 
// Related Topics 树 深度优先搜索 
// 👍 44 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/** DFS */
class Solution {
    String min = "~";            //用于存储最小的字符串,第一次肯定是单字母,肯定会被替换
    public String smallestFromLeaf(TreeNode root) {
        dfs(root, new StringBuilder());
        return min;
    }

    void dfs(TreeNode node, StringBuilder sb) {
        if (node == null) return;

        // 将值转换成字符串
        sb.append((char)(node.val+97));

        if (node.left == null && node.right == null) {
            sb.reverse();
            String S = sb.toString();
            sb.reverse();

            // 字符串比较就是字典符比较
            if (S.compareTo(min) < 0)
                min = S;
        }


        dfs(node.left, sb);
        dfs(node.right, sb);
        //删除最后一位, 返回上一级
        sb.deleteCharAt(sb.length() - 1);
    }
    //beat 100 time 80 memory
}

//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
1.优先找最短的,然后找到最小的叶子节点然后向上找就可以了
2.想用BFS,因为找到同一层的最小值就直接结束了,而DFS需要遍历所有的子路径,但是BFS需要保存所有的父节点,还是用DFS吧,直接暴力找到所有路径
*/