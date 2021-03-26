//给定一个二叉树，它的每个结点都存放着一个整数值。 
//
// 找出路径和等于给定数值的路径总数。 
//
// 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。 
//
// 示例： 
//
// root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//      10
//     /  \
//    5   -3
//   / \    \
//  3   2   11
// / \   \
//3  -2   1
//
//返回 3。和等于 8 的路径有:
//
//1.  5 -> 3
//2.  5 -> 2 -> 1
//3.  -3 -> 11
// 
// Related Topics 树 
// 👍 790 👎 0


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
/** 前缀和解法 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();

        // 加入前缀和为0的一条路径
        prefixSumCount.put(0, 1);

        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.递归终止条件
        if (node == null) return 0;

        // 2.本层要做的事情
        int res = 0;

        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);

        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量 (回溯)
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
    //beat 99 time 30 memory
}

/** 这个解只是看着简洁，实际上这个没用到前缀和，直接暴力搜索的，时间复杂度高得多O(n^2) */
class baseSolution {
    public int pathSum(TreeNode root, int sum) {
        //base case
        if (root == null) return 0;

        // 当前节点加上左右节点的路径数 即为 满足路径总和的路径总数(最核心的递归)
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    /** 明确dfs的作用 */
    int dfs(TreeNode root, int sum) {
        //base case
        if (root == null) return 0;

        int count = 0;

        if (root.val == sum) {
            // 到当前节点的一条路径满足条件
            count = 1;
        }

        // 递归左子树和右子树，返回可能的路径总数
        count += dfs(root.left, sum - root.val);        //不会修改原来的树结构
        count += dfs(root.right, sum - root.val);

        return  count;
    }
    // beat 25 time 29 memory
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
1. 暴力查找,对于每个节点都一路向下找子树,知道找到为止
2. 前缀和

*/