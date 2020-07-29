package treeTest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * Description:判断是不是完全二叉树
 * User:wanghuimin
 * Date:2020-04-23
 * Time:19:21
 * 一万年太久，只争朝夕，加油
 */
class TreeNodes {
    char val;
    TreeNodes left;
    TreeNodes right;

    TreeNodes( char val) {
        this.val = val;
    }
}

    public class BuildTree {
        static TreeNodes build() {
            // 通过 build 方法构建一棵树, 返回树的根节点.
            TreeNodes A = new TreeNodes('A');
            TreeNodes B = new TreeNodes('B');
            TreeNodes C = new TreeNodes('C');
            TreeNodes D = new TreeNodes('D');
            TreeNodes E = new TreeNodes('E');
            TreeNodes F = new TreeNodes('F');
            TreeNodes G = new TreeNodes('G');
            A.left = B;
            A.right = C;
            B.left = D;
            B.right = E;
            E.left = G;
            C.right = F;
            return A;
        }

        public static void main(String[] args) {
            TreeNodes node = build();
            System.out.println(isComplete(node));
            System.out.println(tree2str(node));


        }

        private static boolean isComplete(TreeNodes root) {
                if (root == null) {
                    return true;
                }
                //true在第一阶段。false在第二阶段
                boolean isFirstStep = true;
                Queue<TreeNodes> queue = new LinkedList<>();
                queue.offer(root);

                while (!queue.isEmpty()) {
                    TreeNodes cur = queue.poll();
                    //如果在第一阶段
                    if (isFirstStep) {
                        //任意节点必须具备两个子树
                        if (cur.left != null && cur.right != null) {
                            queue.offer(cur.left);
                            queue.offer(cur.right);
                        } else if (cur.left == null && cur.right != null) {
                            return false;
                            //任意节点必须没有子树
                        } else if (cur.left != null && cur.right == null) {
                            //如果左子树为空，右子树不为空，那么就进入第二阶段的判断
                            isFirstStep = false;
                            queue.offer(cur.left);
                        } else {
                            //左右子树都为空,还是进入第二阶段
                            isFirstStep = false;
                        }
                    } else {
                        if (cur.left != null || cur.right != null) {
                            return false;
                        }
                    }
                }
                //遍历完了，还是没有找到不符合情况的树，那么就是完全二叉树
                return true;
            }

        // 把结果作为一个成员变量来使用.
        // 递归过程中每层递归方法都可以很方便的操作同一个结果.
        private List<List<Integer>> result = new ArrayList<>();

        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return result;
            }
            // 对树进行先序遍历, 递归时需要加上一个 层数 作为参数(层数直接从 0 开始算, 方便和 List 下标对应)
            result.clear(); // 最好加上 clear 防止多组数据的时候, 上次操作 result 中的残留值, 对本次操作造成干扰
            levelOrderHelper(root, 0);
            return result;
        }

        private void levelOrderHelper(TreeNode root, int level) {
            if (level == result.size()) {
                // 当前 level 在 result 中没有对应的行. 加入一行
                // 防止下面的 get 操作出现下标越界
                result.add(new ArrayList<>());
            }
            List<Integer> curRow = result.get(level);
            //curRow.add(root.val); // 先序遍历的 "访问" 操作.
            if (root.left != null) {
                levelOrderHelper(root.left, level + 1);
            }
            if (root.right != null) {
                levelOrderHelper(root.right, level + 1);
            }
        }
        public static  StringBuilder sb=new StringBuilder();
        public static String tree2str(TreeNodes t) {
            if (t == null) {
                return "";
            }
            helper(t);
            sb.deleteCharAt(0);
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        }

        private  static void helper(TreeNodes root) {
            if(root==null){
                return;
        }
            //访问
            sb.append("(");
            sb.append(root.val);
            if(root.left==null&&root.right!=null){
                sb.append("()");
            }
            helper(root.left);
            helper(root.right);
            sb.append(")");

    }
}