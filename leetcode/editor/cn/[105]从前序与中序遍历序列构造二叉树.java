//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 950 👎 0


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

/** 通过HashMap优化查找index */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // HashMap 用于存储中序遍历的节点, 防止每一次都查找rootVal
        HashMap<Integer, Integer> inmap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            inmap.put(inorder[i], i);
        }

        return buildTreeHelper(preorder, 0, preorder.length-1,
                inorder, 0 , inorder.length-1, inmap);
    }

    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
                       int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> inmap) {
        if (preStart > preEnd) {
            return null;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];

        /** 原本每次都需要查找, 现在直接用 HashMap 来 get.只有这里不一样 */
        int index = inmap.get(rootVal);

        int leftSize = index - inStart;

        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1, inmap);

        root.right = buildTreeHelper(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd, inmap);

        return root;
    }
    //beat 98 time 25 memory
}

class baseSolution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length-1,
                        inorder, 0 , inorder.length-1);
    }

    /** 辅助函数 */
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }

        // root 节点对应的值就是前序遍历数组的第一个元素
        int rootVal = preorder[preStart];
        // rootVal 在中序遍历数组中的索引
        int index = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;

        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = buildTree(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);

        root.right = buildTree(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);

        return root;
    }
    //beat 49 time 50 memory
}


/*解题思路
这种遍历的题目都可以变成 前中后/序 遍历的问题, 只需要理清楚怎么处理根节点, 然后怎么递归左右两个节点即可.

=============== 遍历方法 ===============
void traverse(TreeNode root) {
    // 前序遍历
    preorder.add(root.val);
    traverse(root.left);
    traverse(root.right);
}

void traverse(TreeNode root) {
    traverse(root.left);
    // 中序遍历
    inorder.add(root.val);
    traverse(root.right);
}
*/