//在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。 
//
// 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。 
//
// 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。 
//
// 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。 
//
// 
//
// 示例 1： 
// 
//
// 
//输入：root = [1,2,3,4], x = 4, y = 3
//输出：false
// 
//
// 示例 2： 
// 
//
// 
//输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
//输出：true
// 
//
// 示例 3： 
//
// 
//
// 
//输入：root = [1,2,3,null,4], x = 2, y = 3
//输出：false 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点数介于 2 到 100 之间。 
// 每个节点的值都是唯一的、范围为 1 到 100 的整数。 
// 
//
// 
// Related Topics 树 广度优先搜索 
// 👍 121 👎 0


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
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        // 将起点加入队列
        q.offer(root);
        // 需要记录左右节点的信息和父节点信息
        TreeNode xNode = null, yNode = null, xFather = null, yFather = null;

        while (!q.isEmpty()) {
            int sz = q.size();
            // 扩散到相邻节点
            for (int i = 0; i < sz; i++) {
                TreeNode n = q.poll();

                //将相邻节点加入队列,同时判断x/y
                if (n.left != null){
                    q.offer(n.left);
                    // 判断是否为x/y
                    if (n.left.val == x){
                        xNode = n.left;
                        xFather = n;
                    }
                    if (n.left.val == y){
                        yNode = n.left;
                        yFather = n;
                    }
                }

                if (n.right != null) {
                    q.offer(n.right);
                    // 判断是否为x/y
                    if (n.right.val == x) {
                        xNode = n.right;
                        xFather = n;
                    }
                    if (n.right.val == y) {
                        yNode = n.right;
                        yFather = n;
                    }
                }

                /** 开始判断 */
                //如果两个节点都为空,说明没找到,继续下一次查找
                if (xNode == null && yNode == null){}
                //都找到了,就判断父节点是否相同
                else if (xNode != null && yNode != null)
                    return xFather!=yFather;
                //本层的所有元素都遍历完了,找到了一个节点,没找到另外一个,肯定不是
                else if (i == sz - 1)
                    return false;
            }
        }
        // 遍历完了还没有就false
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
自己想的: BFS 使用HashSet判断是否为同一层 难点是判断不是同一父节点.
*/