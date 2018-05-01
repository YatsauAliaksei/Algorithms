package com.ttrlalgs.challenge;

public class IsBST {
    class Node {
        int data;
        Node left;
        Node right;
    }

    private static int max = -1;

    boolean checkBST(Node root) {
        if (root == null)
            return false; // or throw exception. IllegalState

        return goLeft(root);
    }

    private boolean goLeft(Node root) {

        if (root.left != null) {
            boolean left = goLeft(root.left);
            if (!left) {
                return false;
            }
        }

        if (root.data <= max) {
            return false;
        }
        max = root.data;

        if (root.right != null) {
            boolean right = goLeft(root.right);
            if (!right) {
                return false;
            }
        }
        return true;
    }

}
