//给你二叉树的根结点 root ，请你将它展开为一个单链表： 
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
// Related Topics 树 深度优先搜索 
// 👍 755 👎 0


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

/** 前序遍历 */
class Solution {
    TreeNode parent = new TreeNode();
    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        parent.right = root;
        parent.left = null;
        parent = root;

        flatten(left);
        flatten(right);
    }
    // beat 100 time 44 memory
}

/** 后序遍历 */
class PostOrderSolution {
    public void flatten(TreeNode root) {
        // base case, 不需要返回
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        /** 开始后序遍历 */
        //保存 right 节点
        TreeNode right = root.right;

        // 将左子树作为右子树
        root.right = root.left;
        root.left = null;


        // 将原先的右子树接到当前右子树的末端,用原来保存的root.right
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;
    }
    // beat 100 time 77memory
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
难点是原地,分析之后，就是把左节点放到右节点，然后将右节点放到左节点之后.需要后序遍历，先操作叶子节点，然后操作根节点
前序遍历也有方法
*/