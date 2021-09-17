import java.util.*;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(){}
    TreeNode(int val){
        this.val = val;
    }
    TreeNode(int val, TreeNode left,TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class Tree {
    public static final int[] TREE_VALUE = new int[]{2,0,0};
    public static int index = 0;
    //创建树
    public static TreeNode CreateTree(TreeNode node,int i){
        if(TREE_VALUE[i] == 0){
            return null;
        }else{
            node.val = TREE_VALUE[i];
        }
        TreeNode leftChild = new TreeNode();
        node.left = CreateTree(leftChild,++index);
        TreeNode rightChild = new TreeNode();
        node.right = CreateTree(rightChild,++index);
        return node;
    }
    //中序遍历
    public static void inorderTraversalLocal(TreeNode root){
        if(root != null){
            inorderTraversalLocal(root.left);
            System.out.print(root.val + "->");
            inorderTraversalLocal(root.right);
        }
    }
    //先序遍历
    public static void preorderTraversal(TreeNode root){
        if(root != null){
            System.out.print(root.val + "->");
            preorderTraversal(root.left);
            preorderTraversal(root.right);
        }
    }
    //后序遍历
    public static void postorderTraversal(TreeNode root){
        if(root != null){
            postorderTraversal(root.left);
            postorderTraversal(root.right);
            System.out.print(root.val + "->");
        }
    }
    //先序遍历 非递归版
    public static void inorderStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else{
                TreeNode tem = stack.pop();
                System.out.print(tem.val + "->");
                node = tem.right;
            }
        }
    }
    //先序遍历 非遍历版
    public static void preorderStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            if(node != null){
                System.out.print(node.val + "->");
                stack.push(node);
                node = node.left;
            }else{
                TreeNode tem = stack.pop();

                node = tem.right;
            }
        }
    }
    //层级遍历
    public static void levelOrderTraversal(TreeNode root){
        if(root == null) return;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.val +"->");
            if(node.left != null)
                queue.add(node.left);
            if(node.right != null)
                queue.add(node.right);
        }

    }

    //leetcode94
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root;
        while(node != null || !stack.isEmpty()){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else{
                TreeNode tem = stack.pop();
                list.add(tem.val);
                node = tem.right;
            }
        }
        return list;
    }
    //leetcode100
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q != null || p !=null  && q == null || p != null && q != null && p.val != q.val)
            return false;
        if(p == q)
            return true;
        return  isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
    //leetcode101 给定一个二叉树，检查它是否是镜像对称的
    public boolean isSymmetric(TreeNode root) {
        if(root == null)
            return true;
        return in(root.left,root.right);
    }
    public boolean in(TreeNode t1,TreeNode t2){
        if(t1 == null && t2 == null)
            return true;
        if(t1 == null || t2 == null)
            return false;
        return t1.val == t2.val && in(t1.left,t2.right) && in(t1.right,t2.left);
    }
    //leetcode104 给定一个二叉树，找出其最大深度
    public int maxDepth(TreeNode root) {
        if(root == null)
            return  0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
    //leetcode 108 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums,0,nums.length-1);
    }
    //分治思路 二分查找
    public TreeNode helper(int[]nums,int left,int right){
        if(left > right){
            return null;
        }else{
            TreeNode node = new TreeNode();
            int mid = (left+right)/2;
            node.val = nums[mid];
            node.left = helper(nums,left,mid-1);
            node.right = helper(nums,mid+1,right);
            return node;
        }
    }

    //leetcode 110 判断是否是平衡二叉树
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        if(Math.abs(getDepth(root.left)-getDepth(root.right))<=1)
            return  isBalanced(root.left)&&isBalanced(root.right);
        return false;
    }
    //思路:计算树的深度，利用其计算左子树和右子树的深度，进行相减判断是否平衡
    public int getDepth(TreeNode root){
        if(root == null) return 0;
        return Math.max(getDepth(root.left),getDepth(root.right))+1;
    }
    //leetcode111 二叉树最小深度

    public int minDepth(TreeNode root) {
//        if(root == null)return  0;
//        int minLeft = minDepth(root.left);
//        int minRight = minDepth(root.right);
//        if(minLeft == 0 || minRight == 0)
//            return Math.max(minLeft,minRight)+1;
//        else
//            return  Math.min(minLeft,minRight)+1;

        if(root == null)return  0;
        int minLeft = minDepth(root.left);
        int minRight = minDepth(root.right);
        if(minLeft == 0 && minRight != 0)
            return minRight+1;
        if(minLeft != 0 && minRight == 0)
            return minLeft +1;
        return  Math.min(minRight,minLeft)+1;
    }
    //leetcode95 不同的搜索二叉树 分治思想
    public List<TreeNode> generateTrees(int n) {
        return generateTree(1,n);
    }
    public List<TreeNode> generateTree(int left,int right){
        List<TreeNode> curRes = new LinkedList<TreeNode>();
        if(left > right){
            curRes.add(null);
            return curRes;
        }
        for(int i=left;i<=right;i++){
            List<TreeNode> leftNode = generateTree(left,i-1);
            List<TreeNode> rightNode = generateTree(i+1,right);
            for (TreeNode lNode:leftNode) {
                for (TreeNode rNode : rightNode) {
                    curRes.add(new TreeNode(i, lNode, rNode));
                }
            }
        }
        return curRes;
    }
    //动态规划 1.
    public int fic(int n){
        if(n==1 || n==2) return 1;
        int pre=1,after=1;
        int result = 0;
        for(int i=0;i<n-2;i++){
            result = pre + after;
            pre = after;
            after = result;
        }
        return  result;
    }
    //2.
    public int CoinAmount(int[] coins,int amount){
        if(amount == 0) return  0;
        if(amount < 0) return  -1;
        int res = Integer.MAX_VALUE;
        for(int coin:coins){
            int cures = CoinAmount(coins,amount-coin);
            if(cures == -1)continue;
            res = Math.min(res,cures)+1;
        }
        return res == Integer.MAX_VALUE?-1:res;
    }

    public static void main(String[] args) {
        System.out.println(new Tree().fic(12));
        System.out.println(new Tree().CoinAmount(new int[]{1,2,5},10));
//        List<TreeNode> treeNodes = new Tree().generateTrees(10);
//        for(TreeNode treeNode:treeNodes){
//            preorderTraversal(treeNode);
//            System.out.println();
//        }

//        TreeNode root = new TreeNode();
//        root = CreateTree(root,index);
//        inorderTraversalLocal(root);
//        System.out.println();
//        preorderTraversal(root);
//        System.out.println();
//        postorderTraversal(root);
//        System.out.println();
//        inorderStack(root);
//        System.out.println();
//        Tree tree = new Tree();
//        List<Integer> list = tree.inorderTraversal(root);
//        for (int number:list) {
//            System.out.print(number+"->");
//        }
//        System.out.println();
//        preorderStack(root);
//        System.out.println();
//        levelOrderTraversal(root);
//        System.out.println();
//        index = 0;
//        TreeNode root1 = new TreeNode();
//        root1 = CreateTree(root1,index);
//        System.out.println("the result is "+tree.isSameTree(root,root1));
//        System.out.println("this result is "+tree.isSymmetric(root));
//        System.out.println(tree.maxDepth(root));

//        Tree tree = new Tree();
//        int[] nums = new int[]{-10,-3,0,5,9};
//        TreeNode root = tree.sortedArrayToBST(nums);
//        inorderStack(root);
    }
}
