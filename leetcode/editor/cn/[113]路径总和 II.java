//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。 
//
// 叶子节点 是指没有子节点的节点。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
// 
// 
// Related Topics 树 深度优先搜索 
// 👍 450 👎 0


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
    List<List<Integer>> ans = new LinkedList<List<Integer>>();
    LinkedList<Integer> list = new LinkedList<Integer>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, 0, targetSum);
        return ans;
    }

    void dfs(TreeNode node, int cur, int targetSum) {
        if (node == null) return;

        cur += node.val;
        list.add(node.val);

        if (cur == targetSum && node.left == null && node.right == null){
            ans.add(new ArrayList(list));       //!要新建一个list,否则你把list添加进去，你后续修改list的话，因为res里面那个list和你修改的那个list指向的是同一块内存区域，所以你对list进行修改，也会把res里的结果给修改掉
        }

        dfs(node.left, cur, targetSum);
        dfs(node.right, cur, targetSum);

        //要恢复之前
        list.removeLast();
    }
    //beat 39 time 66 memory
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
跟上一次类似,不过由于要找出所有路径,所以不适合使用BFS
*/