//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组 
// 👍 471 👎 0


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
/** 利用HashMap优化 */
class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
    int[] post;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        post = postorder;
        return buildTreeHelper(0, inorder.length-1, 0, post.length-1);
    }

    private TreeNode buildTreeHelper(int inStart, int inEnd, int postStart, int postEnd) {
        // postStart > postEnd 说明了数组为null
        if (postStart > postEnd || inStart > inEnd) return null;

        // 找到根节点的值
        int rootVal = post[postEnd];
        // 确定根节点在inorder中的索引
        int index = map.get(rootVal);

        //递归左右节点
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeHelper(inStart, index-1,  postStart, postStart+index-inStart-1);
        root.right = buildTreeHelper(index+1, inEnd, postStart+index-inStart, postEnd-1);

        return root;
    }
    //beat 98 time 15 memory
}


/** 这是没有优化的版本,会爆出内存超过限制的错误 */
class baseSolution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTreeHelper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    private TreeNode buildTreeHelper(int[] inorder, int inStart, int inEnd,
                                     int[] postorder, int postStart, int postEnd) {
        // postStart > postEnd 说明了数组为null
        if (postStart > postEnd) return null;

        // 找到根节点的值
        int rootVal = postorder[postEnd];
        // 确定根节点在inorder中的索引
        int index = 0;
        for (int i = inStart; i < inEnd; i++) {
            if (inorder[i] == rootVal){
                index = i;
                break;
            }
        }

        //递归左右节点
        TreeNode root = new TreeNode(rootVal);
        int leftSize = index - inStart;
        root.left = buildTreeHelper(inorder, inStart, index-1, postorder, postStart, postStart + leftSize);
        root.right = buildTreeHelper(inorder, index+1, inEnd, postorder, postStart+leftSize, postEnd-1);

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路

*/