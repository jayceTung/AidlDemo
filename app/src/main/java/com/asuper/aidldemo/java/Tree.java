package com.asuper.aidldemo.java;

/**
 * 平衡二叉树
 * @author super
 * @date 2020/7/22
 */
public class Tree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int getHeight(TreeNode root) {
        int height = 0;
        if (root != null) {
            int left = getHeight(root.left);
            int right  = getHeight(root.right);
            height = 1 + (left >= right ? left : right);
        }
        return height;
    }


    //零树也是平衡树 满足左右子树都是平衡树切 左右子树深度差小于等于1
    public boolean isBalance(TreeNode root) {
        if (root == null) {
            return true;
        }
        //
        if (isBalance(root.left) && isBalance(root.right)
                && (getHeight(root.left) - getHeight(root.right) <= 1)) {
            return true;
        } else {
            return false;
        }
    }


}
