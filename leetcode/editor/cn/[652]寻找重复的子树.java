//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。 
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。 
//
// 示例 1： 
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
// 
//
// 下面是两个重复的子树： 
//
//       2
//     /
//    4
// 
//
// 和 
//
//     4
// 
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。 
// Related Topics 树 
// 👍 250 👎 0


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

/** 唯一标识符 */
class Solution {
    int t;
    Map<String, Integer> trees = new HashMap();     //trees保存三元组uid, 以及它的序号
    Map<Integer, Integer> count = new HashMap();    //count保存序号和出现的次数
    List<TreeNode> ans = new ArrayList();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        lookup(root);
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) return 0;

        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);

        //将三元组作为uid
        /** computeIfAbsent(key, function) 方法对 HashMap 中指定 key 的值进行重新计算，如果不存在这个 key，则添加到 hasMap 中。
         * 下面这个函数的作用是:如果存在serial, 那么它的值不变; 如果不存在serial,那么就等效于 hashMap.put(serial, t++)
         * 返回值为serial出现的序号*/
        int uid = trees.computeIfAbsent(serial, x-> t++);

        // 如果之前出现一次,那么put之后就变成了2,所以判断uid==2
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        if (count.get(uid) == 2)
            ans.add(node);

        return uid;
    }
    //beat 95 time, 93 memory
}


/** 序列化 + HashMap */
class StringSolution {
    HashMap<String, Integer> subTreeString = new HashMap<String, Integer>();      //用于存储子树序列化的字符串, Interger 表示出现的次数
    LinkedList<TreeNode> res = new LinkedList<TreeNode>();        //用于存储找到的重复节点

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse2String(root);
        return res;
    }

    private String traverse2String(TreeNode root) {
        if (root == null) return "#";

        String left = traverse2String(root.left);
        String right = traverse2String(root.right);

        String s =  left + "," + right + "," + root.val;        //s即root子树的序列化结果, 逗号用于分割子树,防止有时候序列化结果一致但是子树不一致

        int freq = subTreeString.getOrDefault(s, 0);      //返回字符串出现过的次数, 要是没有出现过默认为0

        if (freq == 1)
            res.add(root);  //如果出现过一次,那么加入res

        subTreeString.put(s, freq+1);       //否则的话, 出现的次数+1

//        如果使用HashSet的操作方式
//        if (subTreeString.contains(s))
//            res.add(root);
//        else
//            subTreeString.add(s);

        return s;
    }
    // beat 76 time 50 memory
}
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
因为需要知道左右节点的信息,所以需要后序遍历.
如何知道自己的子树长什么样 : 将子树序列化成为一串字符, 然后就可以通过比较字符来判断.
如果只使用HashSet来存储出现过的字符串,那么res必定会存在重复.而LinkedList去重很麻烦(存储的是TreeNode而不是int, 不能用Set去重),所以改用HashMap
*/