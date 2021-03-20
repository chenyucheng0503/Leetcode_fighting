//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 105] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 474 👎 0


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
    public int minDepth(TreeNode root) {
        //特殊情况
        if (root == null) return 0;

        // 核心数据结构
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        int depth = 1;      //初始就是1

        while (!q.isEmpty()) {
            int sz = q.size();
            // 将当前队列中的所有节点 向 每个节点的邻居 扩散
            for (int i = 0; i < sz; i++) {
                TreeNode currNode = q.poll();

                //判断是否为叶子
                if (currNode.left == null && currNode.right == null)
                    return depth;

                //将邻居加入队列
                if (currNode.left != null)
                    q.offer(currNode.left);
                if (currNode.right != null)
                    q.offer(currNode.right);
            }
            // 上述for循环会遍历一层, 所以现在层数加一
            depth++;
        }

        return depth;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
使用 BFS 查找最小路径
*/