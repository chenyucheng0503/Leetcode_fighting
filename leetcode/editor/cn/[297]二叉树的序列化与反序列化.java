//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 104] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 设计 
// 👍 519 👎 0


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

/** BFS */
public class Codec {
    // 原来的BFS结构需要修改一下,因为这次需要记录null节点的位置
    public String serialize(TreeNode root) {
        //base case
        if (root == null) return "";

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        String s = "";
        q.offer(root);

        /** 这里可以引申出去，标准的BFS框架本身是不带 q.size 的.但是为了确保遍历都在同一层内,所以需要size约束.但是这道题只需要按顺序遍历就好,不需要同层约束,所以不用.
         * 参考:https://stackoverflow.com/questions/49041309/importance-of-the-size-of-the-queue-in-bfs/49041581 */
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            
            /* 层级遍历代码位置 */
            if (cur == null) {
                s += "#,";
                continue;
            }

            s += cur.val + ",";

            q.offer(cur.left);
            q.offer(cur.right);
        }

        System.out.println(s);
        return s;
    }

    public TreeNode deserialize(String data) {
        //base case
        if(data == "")  return null;

        LinkedList<String> nodes = new LinkedList<String>();
        for (String s : data.split(",")){
            if (s == "") continue;
            nodes.addLast(s);
        }

        //第一件事是找到根节点的位置
        String first = nodes.removeFirst();
        TreeNode root = new TreeNode(Integer.parseInt(first));
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        while (!q.isEmpty()){
            TreeNode node = q.poll();
            if(node == null) continue;

            String left = nodes.removeFirst();
            if (!left.equals("#")) {
                node.left = new TreeNode(Integer.parseInt(left));
                q.offer(node.left);
            } else {
                node.left = null;
                q.offer(null);
            }

            String right = nodes.removeFirst();
            if (!right.equals("#")) {
                node.right = new TreeNode(Integer.parseInt(right));
                q.offer(node.right);
            } else {
                node.right = null;
                q.offer(null);
            }
        }
        return root;
    }
    // beat 17 time 14 memory
}


/** 后序遍历 */
/** 第一次提示Memory out of Limit,需要使用StringBuilder优化 / 采用分隔符,只占用一次内存 */
class postorderCodec {
    // 使用这两个直接调用,不占内存
    String nullNode = "#";
    String seperator = ",";

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    void serializeHelper(TreeNode root, StringBuilder sb) {
        //base case
        if (root == null) {
            sb.append(seperator).append(nullNode);
            return;
        }

        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);

        sb.append(seperator).append(root.val);
    }

    public TreeNode deserialize(String data) {
        if (data == null) return null;
        LinkedList<String> nodes = new LinkedList<String>();
        for (String s : data.split(seperator)){
            if(s == "") continue;           //去除一开始的逗号
            nodes.addLast(s);
        }
        return deserializeHelper(nodes);
    }

    /** 后序遍历的反序列化比较难, 因为正常来说还没有root节点,所以不能后序生成节点*/
    TreeNode deserializeHelper(LinkedList<String> nodes) {
        //base case
        if (nodes.isEmpty()) return null;     //后序遍历的第一个数肯定为空

        String val = nodes.removeLast();
        if (val.equals(nullNode)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        
        /** 注意:这里是先生成右子树,再生成左子树  */
        root.right = deserializeHelper(nodes);
        root.left = deserializeHelper(nodes);
        
        return root;
    }
    // beat 87 time 47 memory
}


/** 前序遍历 */
class preorderCodec {

    String serString = new String();
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        serializeHelper(root);
        return serString;
    }

    private void serializeHelper(TreeNode root) {
        if (root == null) {
            serString += "#,";
            return;
        }
        serString = serString + root.val + ",";
        serialize(root.left);
        serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(",")) {
            nodes.addLast(s);
        }
        return deserializeHelper(nodes);
    }

    /** 用前序遍历还原二叉树 */
    private TreeNode deserializeHelper(LinkedList<String> nodes) {
        if (nodes == null) return null;         //String 最后一个肯定是空的,去除掉

        //第一个节点是根节点
        String first = nodes.removeFirst();     //从列表中取出第一个作为根节点
        if (first.equals("#")) return null;     //判断是否为空
        TreeNode root = new TreeNode(Integer.parseInt(first));

        root.left = deserializeHelper(nodes);   //此时第nodes已经移除了一个节点
        root.right = deserializeHelper(nodes);

        return root;
    }
    // beat 18 time 13 memory
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)


/*解题思路
反序列化:用列表而不是char[],因为可能有二位数,三位数.比较麻烦
*/