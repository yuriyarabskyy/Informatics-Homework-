/**
 * Created by yuriyarabskyy on 20/12/15.
 */
public class BinaryTreeNode {

    private BinaryTreeNode left = null, right = null;

    public BinaryTreeNode getLeft() { return left; }
    public BinaryTreeNode getRight() { return right; }
    public void setLeft(BinaryTreeNode left) { this.left = left; }
    public void setRight(BinaryTreeNode right) { this.right = right; }

    int data;

    BinaryTreeNode(int data) { this.data = data; }

    BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
        this.data =  data;
        this.left =  left;
        this.right = right;
    }

}
