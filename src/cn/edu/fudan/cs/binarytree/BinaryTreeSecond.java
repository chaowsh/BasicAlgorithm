package cn.edu.fudan.cs.binarytree;

import java.util.*;

public class BinaryTreeSecond {

    //中->左->右
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ret.add(node.value);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        System.out.println("PreorderTravseral: " + ret);
        return  ret;
    }

    //左->中->右
    public List<Integer> orderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                ret.add(node.value);
                node = node.right;
            }
        }
        System.out.println("inOrderTravseral: " + ret);
        return ret;
    }

    //中->左->右 =>  中->右->左  => 左->右->中
    public List<Integer> postTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if (root == null) return ret;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> values = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            //ret.add(node.value);
            values.push(node.value);
            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        //Resverse
        while (!values.isEmpty()) {
            ret.add(values.pop());
        }

        System.out.println("PostorderTravseral: " + ret);
        return ret;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return -1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;
        int size;
        while (!queue.isEmpty()) {
            size = queue.size();
            depth += 1;
            for (int i=0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

        }
        return depth;
    }

    public TreeNode buildTreeFromPreAndInorder(int []pre, int []inorder) {
        if (pre == null || inorder == null) throw new IllegalArgumentException("Pre or Inorder should not be NULL.");
        if (pre.length != inorder.length) throw new IllegalArgumentException("Length is not the same.");
        int length = pre.length;
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < length; i++) {
            valueToIndex.put(inorder[i], i);
        }
        return buildTree(pre, valueToIndex, 0, length - 1, 0 , length - 1);
    }

    public TreeNode buildTree(int []pre, Map<Integer, Integer> value2Index, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(pre[preStart]);
        int orderIndex = value2Index.get(root.value);
        int leftLength = orderIndex - inStart;
        root.left = buildTree(pre, value2Index, preStart + 1, preStart + leftLength, inStart, orderIndex - 1);
        root.right = buildTree(pre, value2Index, preStart + leftLength + 1, preEnd, orderIndex + 1, inEnd);
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.right = node1;
        node1.left = node2;
        BinaryTreeSecond bt = new BinaryTreeSecond();
        bt.preorderTraversal(root);
        bt.preorderTraversal(root);
        bt.postTraversal(root);
    }
}
