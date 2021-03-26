//给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和
// targetSum 。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// Related Topics 树 深度优先搜索 
// 👍 551 👎 0


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
/** BFS */
//这样我们使用两个队列，分别存储将要遍历的节点，以及根节点到这些节点的路径和即可。
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        Queue<TreeNode> q = new LinkedList<TreeNode>();   //常规的BFS队列
        Queue<Integer> qVal = new LinkedList<Integer>();      //两个队列能存储父节点的值,保存父子信息
        q.offer(root);
        qVal.offer(root.val);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int val = qVal.poll();

            if (node.left == null && node.right == null) {
                if (val == targetSum) return true;
                continue;
            }

            if(node.left != null) {
                q.offer(node.left);
                qVal.offer(node.left.val + val);      //这样能够把父节点的信息加在队列中
            }
            if (node.right != null) {
                q.offer(node.right);
                qVal.offer(node.right.val + val);
            }
        }
        return false;
    }
    //beat 12 time 82 memory
}


/** 也是递归,但是不需要辅助函数 */
class dfsSolution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;

        if (root.left == null && root.right == null)
            return root.val == targetSum;

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
    //beat 100 time 5 memory
}


/** 自己做的 */
class MySolution {
    boolean flag = false;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        dfs(root, 0, targetSum);
        return flag;
    }

    void dfs(TreeNode node, int cur, int targetSum) {
        if (node == null) return;

        cur += node.val;
        //判断是叶子节点
        if (cur == targetSum && node.left == null && node.right == null) {
            flag = true;
            return;
        }

        dfs(node.left, cur, targetSum);
        dfs(node.right, cur, targetSum);
    }
    //beat 100 time 38 memory
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路

*/