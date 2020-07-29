package treeTest;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Description:二叉树的相关方法
 * User:wanghuimin
 * Date:2020-04-19
 * Time:16:38
 * 一万年太久，只争朝夕，加油
 */

//先序遍历：根节点，左子树，右子树
    class Node{
        public char val;
        public Node left;
        public Node right;

    public Node(char val) {
        this.val = val;
        this.left=null;
        this.right=null;
    }
    @Override
    public String toString() {
        return  "Node{" +
                "val=" + val +
                '}';
    }
}

public class TreeTest {
    // 辅助我们构造测试数据的.
    static Node build() {
        // 通过 build 方法构建一棵树, 返回树的根节点.
        Node A = new Node('A');
        Node B = new Node('B');
        Node C = new Node('C');
        Node D = new Node('D');
        Node E = new Node('E');
        Node F = new Node('F');
        Node G = new Node('G');
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        E.left = G;
        C.right = F;
        return A;
    }

    public static void main(String[] args) {
        Node node = build();//得到这棵树的根节点
        FirstRoot(node);
        System.out.println(" ");
        FirstLeftRoot(node);
        System.out.println(" ");
        FirstLeftRight(node);
        int ret = SumNode(node);
        System.out.println();
        System.out.println("这棵二叉树的节点个数为" + ret);

        System.out.println();
        int ret2 = LeafNode(node);
        System.out.println("这棵树的叶子节点个数为" + ret2);

        System.out.println();
        Node result = find(node, 'G');
        System.out.println(result);

        FirstRoot(node);
        System.out.println();
        inorderTraversal(node);

    }


    //先序遍历
    public static void FirstRoot(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        FirstRoot(root.left);
        FirstRoot(root.right);
    }

    //中序遍历
    public static void FirstLeftRoot(Node root) {
        if (root == null) {
            return;
        }
        FirstLeftRoot(root.left);
        System.out.print(root.val + " ");
        FirstLeftRoot(root.right);
    }

    //后序遍历
    public static void FirstLeftRight(Node root) {
        if (root == null) {
            return;
        }
        FirstLeftRight(root.left);
        FirstLeftRight(root.right);
        System.out.print(root.val + " ");
    }

    public static int treeSize = 0;
//    public static void size(Node root) {
//        if (root == null) {
//            return;
//        }
//        treeSize++;  // 访问节点操作
//        size(root.left);
//        size(root.right);
//    }

    //求这个树中的节点个数
    //根节点+左子树的节点个数+右子树的节点个数
    public static int SumNode(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + SumNode(root.left) + SumNode(root.right);
    }

    //    public static int leafSize = 0;
//    public static void leafSize(Node root) {
//        if (root == null) {
//            return;
//        }
//        if (root.left == null && root.right == null) {
//            // 如果满足这两个条件, 说明 root 就是 叶子节点
//            leafSize++;
//            return;
//        }
//        leafSize(root.left);
//        leafSize(root.right);
//    }
    //求叶子节点的个数
    public static int LeafNode(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return LeafNode(root.left) + LeafNode(root.right);
    }

    //求第K层的节点数
    //第k层的节点数=第k-1层的左子树的节点数+第k-1层的右子树的节点数
    public static int Knode(Node root, int k) {
        if (k == 1) {
            return 1;
        }
        return Knode(root.left, k - 1) + Knode(root.right, k - 1);

    }
    //    public static Node result = null;
//    public static void find(Node root, char toFind) {
//        if (root == null) {
//            return;
//        }
//        if (root.val == toFind) {
//            result = root;
//            return;
//        }
//        find(root.left, toFind);
//        find(root.right, toFind);
//    }

    //求val所在的节点
    public static Node find(Node root, char toFind) {
        if (root == null) {
            return null;
        }
        if (root.val == toFind) {
            return root;
        }
        Node result = find(root.left, toFind);
        if (result != null) {
            return result;
        }
        return find(root.right, toFind);
    }

    //两个二叉树是否相等：值相等并且树的结构相同
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            //如果两个都是空树
            return true;
        }
        if (p == null || q == null) {
            //如果一个为空，一个为非空
            //由于前面还有一个p q均为空的条件的限制，因此走到这里两个不可能同时为空
            return false;
        }
        //递归拆分
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }

    //是否是子树
    public boolean isSubtree(TreeNode s, TreeNode t) {
        //先序遍历s
        if (s == null) {
            return false;
        }
        //s中是否包含t||s.left是否包含t||s.right是否包含t
        //访问当前节点
        return isSameTree(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    //二叉树的最大深度：根节点到最远叶子节点的最长路径上的节点数
    //1+max(左子树的深度，右子树的深度）
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            //左子树为空，右子树为空，说明这个根节点也是个叶子节点
            return 1;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + (leftDepth > rightDepth ? leftDepth : rightDepth);
    }

    //平衡二叉树：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
    //依次遍历每个节点，求这个节点的左右子树的高度，然后计算差值，看看这个差值是否符合要求
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        //求当前节点的左右子树的高度
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        //当前节点的左右子树高度差<=1&&左子树平衡&&右子树平衡
        return (leftDepth - rightDepth <= 1 && leftDepth - rightDepth >= -1)
                && isBalanced(root.left) && isBalanced(root.right);
    }

    //对称二叉树:对应节点的值相同&&左右子树要刚好相反
    //和根节点没有关系，看左右子树是否对称:值相等&&左子树.left和右子树.right是否对称&&左子树.right和右子树.left是否对称
    public boolean isSymmetric(TreeNode root) {
        //判断root
        if(root==null){
            return true;
        }
        //判定root是否为对称转换成root.left和root.right是否对称


       return isMirror(root.left,root.right);
    }
    private boolean isMirror(TreeNode t1,TreeNode t2){
        if(t1==null&&t2==null){
            return true;
        }
        if(t1==null||t2==null){
            return false;
        }
        //t1和t2是否对称=》t1.val==t2.val&&t1.left和t2.right对称&&t1.right和t2.left对称
        return (t1.val==t2.val) &&isMirror(t1.left,t2.right)&&isMirror(t1.right,t2.left);
    }
    //层序遍历，左右孩子表示法
    //先将根节点入队列，取出队首元素，打印；再把左子树和右子树入队列，取队首元素，打印；
    //队列为空，遍历完成
    public static void levelOrder(TreeNode root){
        if(root==null){
            return;
        }
        //1、创建一个队列
        Queue<TreeNode> queue=new LinkedList<>();
        //2、将根节点入队列
        queue.offer(root);
        //如果队列不为空，就把队首元素出队列，并打印它的值
        while(!queue.isEmpty()){
            TreeNode cur=queue.poll();
            System.out.print(cur.val+" ");
            //如果左子树不为空，将左子树入队列
            if(cur.left!=null){
                queue.offer(cur.left);
            }
            //如果右子树不为空，将右子树入队列
            if(cur.right!=null){
                queue.offer(cur.right);
            }
        }


    }
public static void FirstOrder(Node root) {
    if (root == null) {
        return;
    }
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        Node cur = stack.pop();
        System.out.print(cur.val + " ");
        if (cur.right != null) {
            stack.push(cur.right);
        }
        if (cur.left != null) {
            stack.push(cur.left);
        }
    }
}
        public static List<Character> inorderTraversalhko9(Node root){
            List<Character> ret = new ArrayList<>();
            Stack<Node> stack = new Stack<>();
            Node cur = root;
            while (cur != null || !stack.isEmpty()) {
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                cur = stack.pop();
                ret.add(cur.val);
                cur = cur.right;
            }
            return ret;
        }

    public static void inorderTraversal(Node root) {
        if(root==null){
            return;
        }
        Stack<Node> stack = new Stack<Node>();
        Node prev=null;
        Node cur=root;
            //不断往左子树方向走，每走一次就将当前节点保存到栈中
            //这是模拟递归的调用
        while(true){
            while(cur!=null){
                stack.push(cur);
                cur=cur.left;
            }
            if(stack.isEmpty()) {
                break;
            }
                Node top=stack.peek();
                if(top.right==null||top.right==prev){
                    System.out.print(top.val+" ");
                    stack.pop();
                    prev=top;
                }else{
                    cur=top.right;
                }
            }
    }
}