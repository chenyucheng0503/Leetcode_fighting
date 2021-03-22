//给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。 
//
// 其中，克隆树 cloned 是原始树 original 的一个 副本 。 
//
// 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）
//。 
//
// 
//
// 注意： 
//
// 
// 你 不能 对两棵二叉树，以及 target 节点进行更改。 
// 只能 返回对克隆树 cloned 中已有的节点的引用。 
// 
//
// 
// 
//
// 进阶：如果树中允许出现值相同的节点，你将如何解答？ 
//
// 
//
// 
// 
//
// 示例 1: 
//
// 
//
// 输入: tree = [7,4,3,null,null,6,19], target = 3
//输出: 3
//解释: 上图画出了树 original 和 cloned。target 节点在树 original 中，用绿色标记。答案是树 cloned 中的黄颜色的节点
//（其他示例类似）。 
//
// 示例 2: 
//
// 
//
// 输入: tree = [7], target =  7
//输出: 7
// 
//
// 示例 3: 
//
// 
//
// 输入: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
//输出: 4
// 
//
// 示例 4: 
//
// 
//
// 输入: tree = [1,2,3,4,5,6,7,8,9,10], target = 5
//输出: 5
// 
//
// 示例 5: 
//
// 
//
// 输入: tree = [1,2,null,3], target = 2
//输出: 2 
//
// 
//
// 提示： 
//
// 
// 树中节点的数量范围为 [1, 10^4] 。 
// 同一棵树中，没有值相同的节点。 
// target 节点是树 original 中的一个节点，并且不会是 null 。 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 递归 
// 👍 23 👎 0


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

class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        //创建队列
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Queue<TreeNode> cloned_q = new LinkedList<TreeNode>();

        //加入起点
        q.offer(original);
        cloned_q.offer(cloned);

        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                TreeNode node1 = q.poll();
                TreeNode node2 = cloned_q.poll();

                if (node1 == target){
                    return node2;
                }

                if (node1.left != null){
                    q.offer(node1.left);
                    cloned_q.offer(node2.left);
                }
                if (node2.right != null){
                    q.offer(node1.right);
                    cloned_q.offer(node2.right);
                }
            }
        }
        // 找不到的话,不过不可能
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
=============== BFS ===============
同时遍历两颗树,进行同样的操作.层次遍历,beat 7 time, 95 memory.要想时间快，需要用DFS.
*/