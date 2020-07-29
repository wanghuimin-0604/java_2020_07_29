package treeTest;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * Description:创建树并中序遍历这棵树
 * User:wanghuimin
 * Date:2020-04-24
 * Time:11:18
 * 一万年太久，只争朝夕，加油
 */
public class BuildTrees {
    static class Node {
        public char val;
        public Node left;
        public Node right;

        public Node(char val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
        //读到先序遍历结果，就可以创建树了
        String line=sc.next();
        TreeNodes root=buildTree(line);
        inOrder(root);//打印的每个节点用空格隔开
        System.out.println();
    }
}

    private static void inOrder(TreeNodes root) {
        if(root==null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.val+" ");
        inOrder(root.right);
    }

    private static int index=0;//记录当前处理到哪个字符了
    private static TreeNodes buildTree(String line) {
        index=0;//输入数据可能存在多组
        return createTreePreOrder(line);
    }
//辅助递归的方法
    //每递归一次处理一次节点（从字符串中取出一个指定字符）
    private static TreeNodes createTreePreOrder(String line) {
        char ch=line.charAt(index);
        if(ch=='#'){
            return null;
        }
        //如果节点非空，就可以访问这个节点，这个访问就是创建节点
        TreeNodes node=new TreeNodes(ch);
        index++;//为了处理下一个节点
        node.left=createTreePreOrder(line);
        index++;
        node.right=createTreePreOrder(line);
        return node;
    }
}
