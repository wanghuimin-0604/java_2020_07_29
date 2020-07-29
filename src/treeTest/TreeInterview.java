package treeTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * Description:先序遍历
 * User:wanghuimin
 * Date:2020-04-19
 * Time:17:07
 * 一万年太久，只争朝夕，加油
 */
class TreeNode {
     char val;
     TreeNode left;
     TreeNode right;
     TreeNode(char val) {
         this.val = val; }
  }

public class TreeInterview {
    static TreeNode build() {
        // 通过 build 方法构建一棵树, 返回树的根节点.
        TreeNode A = new TreeNode('A');
        TreeNode B = new TreeNode('B');
        TreeNode C = new TreeNode('C');
        TreeNode D = new TreeNode('D');
        TreeNode E = new TreeNode('E');
        TreeNode F = new TreeNode('F');
        TreeNode G = new TreeNode('G');
        A.left = B;
        A.right = C;
        B.left = D;
        B.right = E;
        E.left = G;
        C.right = F;
        return A;
    }

    public static void main(String[] args) {
        TreeNode node = build();
        List<Character> list1 = preorderTraversal(node);
        for (Character c : list1) {
            System.out.print(c);
        }


        levelOrder(node);

    }

    public static List<Character> preorderTraversal(TreeNode root) {
        List<Character> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.val);
        result.addAll(preorderTraversal(root.left));
        result.addAll(preorderTraversal(root.right));
        return result;
    }

    //层序遍历，左右孩子表示法
    //先将根节点入队列，取出队首元素，打印；再把左子树和右子树入队列，取队首元素，打印；
    //队列为空，遍历完成
    public static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        //1、创建一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            System.out.print(cur.val + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    //这个变量是最近公共节点
    private TreeNode Lac = null;

    //判断公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        findNode(root, p, q);
        return Lac;
    }

    //如果再root中能找到p或者q，就返回true,否则返回false
    private boolean findNode(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        //查找：后序遍历形式进行查找
        int left = findNode(root.left, p, q) ? 1 : 0;
        int right = findNode(root.right, p, q) ? 1 : 0;
        //返回根节点
        int mid = (root == p || root == q) ? 1 : 0;

        if (left + right + mid == 2) {
            Lac = root;
        }

        return (left + right + mid) > 0;
    }

    //返回值为头节点
    public TreeNode Convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        //先递归处理左子树,相当于把左子树已经完整的转换成双向链表，返回值为左子树链表的头节点
        TreeNode left = Convert(root.left);
        //根节点追加到左子树链表的末尾(链表尾插)
        //先找到链表的末尾(right相当于是next,left相当于prev)
        TreeNode leftTail = left;
        while (leftTail != null && leftTail.right != null) {
            leftTail = leftTail.right;
        }
        if (leftTail != null) {
            leftTail.right = root;
            root.left = leftTail;
        }


        //最后递归处理右子树(得到右子树的头节点）
        TreeNode right = Convert(root.right);
        if (right != null) {
            root.right = right;
            right.left = root;
        }
        //left不为空返回left，为空的话就返回right
        return left != null ? left : root;
    }
    /*private int index=0;
    public  TreeNode buildTree(int[] preorder, int[] inorder) {
        index=0;
        return buildTreeHelper(preorder,inorder,0,inorder.length);

    }

    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int inorderLeft, int inorderRight) {
        if(inorderLeft>=inorderRight){
            return null;
        }
        if(index>=inorder.length){
            return null;
        }
        TreeNode newNode=new TreeNode(preorder[index]);
        //左子树对应的中序区间  inorderLeft到pos结束，右子树对应的中序区间
        int pos=find(inorder,inorderLeft,inorderRight,newNode.val);
        index++;
        newNode.left=buildTreeHelper(preorder,inorder,inorderLeft,pos);
        newNode.right=buildTreeHelper(preorder,inorder,pos+1,inorderRight);
        return newNode;
    }

    private int find(int[] inorder, int inorderLeft, int inorderRight, int val) {
        for(int i=inorderLeft;i<inorderRight;i++){
            if(inorder[i]==val){
                return i;
            }
        }
        return -1;
    }
}*/
}