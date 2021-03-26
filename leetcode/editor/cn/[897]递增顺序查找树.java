//给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。 
//
// 
//
// 示例 ： 
//
// 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
//
//       5
//      / \
//    3    6
//   / \    \
//  2   4    8
// /        / \ 
//1        7   9
//
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
//
// 1
//  \
//   2
//    \
//     3
//      \
//       4
//        \
//         5
//          \
//           6
//            \
//             7
//              \
//               8
//                \
//                 9  
//
// 
//
// 提示： 
//
// 
// 给定树中的结点数介于 1 和 100 之间。 
// 每个结点都有一个从 0 到 1000 范围内的唯一整数值。 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 143 👎 0


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

/** 更改树的连接方式 */
class Solution {
    TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode ans = new TreeNode(0); //必须是带值的,否则会被认为是null
        cur = ans;
        inorder(root);
        return ans.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        node.left = null;
        cur.right = node;
        cur = node;
        inorder(node.right);
    }
    //beat 100 time 12 memory
}

/** 新建一棵树,保存中序遍历顺序 */
class SaveSolution {
    public TreeNode increasingBST(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        dfs(root, list);
        TreeNode node = new TreeNode();
        root = node;
        for (int i : list){
            node.left = null;   // 这句话可以省略
            node.right = new TreeNode(i);
            node = node.right;
        }
        return root.right;
    }

    void dfs(TreeNode root, LinkedList<Integer> list){
        if (root == null) return;

        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
    //beat 16 time 95 memory
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
1.先中序遍历出顺序,保存到列表中,然后根据列表创建新树
*/