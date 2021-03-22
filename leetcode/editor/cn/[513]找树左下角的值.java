//给定一个二叉树，在树的最后一行找到最左边的值。 
//
// 示例 1: 
//
// 
//输入:
//
//    2
//   / \
//  1   3
//
//输出:
//1
// 
//
// 
//
// 示例 2: 
//
// 
//输入:
//
//        1
//       / \
//      2   3
//     /   / \
//    4   5   6
//       /
//      7
//
//输出:
//7
// 
//
// 
//
// 注意: 您可以假设树（即给定的根节点）不为 NULL。 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 161 👎 0


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
    /** 先右后左加入节点 */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return -1;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        TreeNode n = null;     //需要在外部定义,否则最后无法返回
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                n = q.poll();
                // 加入相邻节点,先右后左!!!
                if (n.right != null)
                    q.offer(n.right);
                if (n.left != null)
                    q.offer(n.left);
            }
        }
        // n节点就是最左节点
        return n.val;
    }

    /** 保存最后一层的所有节点 */
    public int MySolution(TreeNode root) {
        if (root == null) return -1;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        while (!q.isEmpty()) {
            int sz = q.size();
            int[] q2 = new int[sz];
            for (int i = 0; i < sz; i++) {
                TreeNode n = q.poll();
                q2[i] = n.val;
                // 加入相邻节点,先左后右
                if (n.left != null)
                    q.offer(n.left);
                if (n.right != null)
                    q.offer(n.right);
            }
            /** 其实不需要判断有没有下一层,因为while运行结束就直接退出了,所以可以在while循环之后直接return,详见solution3 */
            // 一层遍历结束之后判断还有没有下一层
            if (q.isEmpty())
                return q2[0];
            else
                q2 = new int[sz];
        }
        // 应该不会运行到这一步,加个保险
        return -1;
    }

    /** 保存最后一层的第一个数 */
    int Solution3(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        int ans = 0;
        q.push(root);

        while (!q.empty()) {
            ans = q.front().val;
            int sz = q.size();
            while (sz--) {
                TreeNode tmp = q.front();
                q.pop();
                if (tmp->left) q.push(tmp->left);
                if (tmp->right) q.push(tmp->right);
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
================BFS方法================
自己的:BFS 保存每一层的val值.beat 69%time 80%memory
别人的:先右后左添加Node,这样就是队列里最后一个.beat 69 time, 83memory
solution3:类似自己的方法,只不过不需要保存一个数组,只是需要保存最前面的一个节点就好
*/