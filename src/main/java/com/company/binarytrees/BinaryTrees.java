/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.company.binarytrees;

/**
 *
 * @author kamau
 */
public class BinaryTrees {

    public class Node {

        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    Node root;

    public void insert(int data) {
        root = insertRec(root, data);
    }
//this is where recursion happens

    public Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
        } else if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        return root;
    }

    //now to print the values we are going to use the INORDER TRAVERSAL
    public void inOrder() {
        InorderRec(root);
    }

    //for recursion
    public void InorderRec(Node root) {
        if (root == null) {
            return;
        }
        InorderRec(root.left);
        System.out.print(root.data + " ");
        InorderRec(root.right);
    }

    //method to check if the tree contains a given value
    public boolean contains(int data) {
        return containsNodeRecursive(root, data);
    }

    //recursive function to check the available value
    public boolean containsNodeRecursive(Node root, int data) {
        //checking if the tree exists
        if (root == null) {
            return false;
        }
        //check if the value is the root
        if (root.data == data) {
            return true;
        }
        //determine if value is on left or right side of current node  
        if (data < root.data) {
            return containsNodeRecursive(root.left, data);
        } else {
            return containsNodeRecursive(root.right, data);
        }

    }

    //find the lowest value from BST
    // public Node minValue() {
    //    return minValueRec(root);
    //}
    //delete a value from the tree
    public void delete(int data) {
        root = deleteNode(root, data);
    }

    public static Node deleteNode(Node node, int data) {
        if (node == null) {                  //if the tree is empty
            return node;
        } else if (data < node.data) {       //if the data is in the left
            node.left = deleteNode(node.left, data);
        } else if (data > node.data) {            //if the data is in the right
            node.right = deleteNode(node.right, data);
        } else {
            //case 1: no child
            //  just set node to null (remove it) and return it
            if (node.left == null && node.right == null) {
                node = null;
            } //case 2:  one child, no left child
            else if (node.left == null) {
                Node temp = node;
                node = node.right;
                temp = null;
            } //case 2:  one child, no right child
            else if (node.right == null) {
                Node temp = node;
                node = node.left;
                temp = null;
            } // Case 3: 2 children
            else {
                // get minimum element in right subtree
                //  set it to `root` and remove it from its
                //   original spot
                node.data = minValueRec(node.right);
                node.right = deleteNode(node.right, node.data);

            }

        }
        return node;

    }

    //to check the minimum value by going to the left left of the root
    public static int minValueRec(Node node) {
        int min = node.data;
        while (node.left != null) {
            node = node.left;
            min = node.left.data;
        }
        return min;
    }
    //to get the maximum

    public static int maxValueRec(Node node) {
        int max = node.data;
        while (node.right != null) {
            node = node.right;
            max = node.right.data;
        }
        return max;
    }

    public static void main(String[] args) {
        BinaryTrees tree = new BinaryTrees();
        tree.insert(3);
        tree.insert(7);
        tree.insert(13);
        tree.insert(15);
        tree.insert(2);
        tree.insert(5);

        tree.inOrder();
        System.out.println("contains value 4 is " + tree.contains(4));
        // System.out.println("minimum value is "+ tree.minValue());

        tree.delete(7);
        tree.inOrder();
        
        
    }
}
