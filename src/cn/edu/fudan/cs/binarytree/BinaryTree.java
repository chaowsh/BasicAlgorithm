package cn.edu.fudan.cs.binarytree;

import java.util.*;

/***
 * Traverse binary tree pre,inorder,post manners
 */
public class BinaryTree {
    private List<Integer> recrusiveResult = new ArrayList<>();

    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                result.add(node.value);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        System.out.println("preorder Traversal 1:");
        printResults(result);
        return result;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            result.add(node.value);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        System.out.println("preorder Traversal 2:");
        printResults(result);
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            result.add(node.value);
            node = node.right;
        }
        System.out.println("Inorder Traversal:");
        printResults(result);
        return result;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Stack<TreeNode> stack1 = new Stack<>();
        stack1.push(root);
        Stack<Integer> stack2 = new Stack<>();
        TreeNode node;
        while (!stack1.isEmpty()) {
            node = stack1.pop();
            stack2.push(node.value);
            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }

        while (!stack2.isEmpty()) {
            result.add(stack2.pop());
        }
        System.out.println("Postorder Traversal:");
        printResults(result);
        return result;
    }

    public List<Integer> postOrderTraversalRecursive(TreeNode root) {
        if (root == null)
            return recrusiveResult;
        postOrderTraversalRecursive(root.left);
        postOrderTraversalRecursive(root.right);
        recrusiveResult.add(root.value);
        return recrusiveResult;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            depth ++;
            for (int i=0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        System.out.println("Max depth : " + depth);
        return depth;
    }

    public int maxDepthRecursive(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepthRecursive(root.left);
        int rightDepth = maxDepthRecursive(root.right);

        return leftDepth >= rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    public void printResults(List<Integer> result) {
        if (result == null || result.size() == 0)
            return;
        System.out.println(result.toString());
    }

    /***
     * 从前序与中序遍历序列构造二叉树
     * @param preorder
     * @param inorder
     * @return
     */

    int []preorder;
    int []inorder;
    Map<Integer, Integer> value2index = new HashMap<>();
    int pre_inx = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;
        this.preorder = preorder;
        this.inorder = inorder;

        for (int i=0; i<inorder.length; i++) {
            value2index.put(inorder[i], i);
        }
        return helper( 0, inorder.length);
    }

    public TreeNode helper(int left, int right) {
        if (left == right)
            return null;
        TreeNode root = new TreeNode(preorder[pre_inx]);

        int index = value2index.get(preorder[pre_inx]);

        pre_inx ++;
        root.left = helper(left, index);
        root.right = helper(index + 1, right);

        return  root;
    }

    public static void main(String []args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.right = node1;
        node1.left = node2;
        BinaryTree bt = new BinaryTree();
        bt.inorderTraversal(root);
        bt.preorderTraversal1(root);
        bt.preorderTraversal2(root);
        bt.postorderTraversal(root);
        System.out.println("Post order traversal recursive: ");
        bt.printResults(bt.postOrderTraversalRecursive(root));
        System.out.println("Max depth recursive: " + bt.maxDepthRecursive(root));
        bt.maxDepth(root);
    }
}
