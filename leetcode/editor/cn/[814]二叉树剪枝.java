//给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。 
//
// 返回移除了所有不包含 1 的子树的原二叉树。 
//
// ( 节点 X 的子树为 X 本身，以及所有 X 的后代。) 
//
// 
//示例1:
//输入: [1,null,0,0,1]
//输出: [1,null,0,null,1]
// 
//解释: 
//只有红色节点满足条件“所有不包含 1 的子树”。
//右图为返回的答案。
//
//
// 
//
// 
//示例2:
//输入: [1,0,1,0,0,0,1]
//输出: [1,null,1,null,1]
//
//
//
// 
//
// 
//示例3:
//输入: [1,1,0,1,1,0,1,0]
//输出: [1,1,0,1,1,null,1]
//
//
//
// 
//
// 说明: 
//
// 
// 给定的二叉树最多有 100 个节点。 
// 每个节点的值只会为 0 或 1 。 
// 
// Related Topics 树 
// 👍 146 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */


class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;

        root.left = pruneTree(root.left);   // 左子树剪枝，得到剪枝后左子树
        root.right = pruneTree(root.right); // 右子树剪枝，得到剪枝后右子树
        // 判断决定root结点是否需要剪掉：
        if (root.left == null && root.right == null && root.val == 0) return null;
        // 返回root这棵树剪枝后的结果
        return root;
    }
    //beat 100 time 44 memory
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
从叶子节点开始移除0,一遍一遍后序遍历递归就可以
*/