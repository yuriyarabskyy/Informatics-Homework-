/**
 * Created by yuriyarabskyy on 14/12/15.
 */
public class BinaryTree {

    private BinaryTreeNode root;

    BinaryTree() { root = null; }

    //recursive implementation with a separate private method insert(BinaryTreeNode, int)
    public void insert(int newElem) {

        root = insert(root, newElem);

    }

    private BinaryTreeNode insert(BinaryTreeNode current, int newElem) {
        //if the node is null, we insert the element hear
        if (current == null) return new BinaryTreeNode(newElem);
        //if the element is less than the node, look recursively to the left
        if (current.data > newElem)  current.setLeft(insert(current.getLeft(), newElem));
        //analogically for the greater
        else if (current.data < newElem) current.setRight(insert(current.getRight(), newElem));

        return current;

    }

    //iterative implementation
    public boolean contains(int elem) {

        BinaryTreeNode current = root;

        while (current != null) {

            if (current.data == elem) return true;

            if (current.data > elem) current = current.getLeft();

            else current = current.getRight();

        }

        return false;

    }

    @Override
    public String toString() {

        StringBuffer output = new StringBuffer("digraph g {\ngraph [ordering=out]\n");

        //'toString' goes recursively through the tree
        output.append(toString(root));

        output.append("}");

        return output.toString();

    }

    private String toString(BinaryTreeNode node) {

        if (node == null) return "";

        String output = new String();

        if(node.getLeft() != null)
            output += "  " + node.data + " -> " + node.getLeft().data + " [label=left]\n";

        if(node.getRight() != null)
            output += "  " + node.data + " -> " + node.getRight().data + " [label=right]\n";
        //look at first recursively to the right and then to the left
        output += toString(node.getRight());
        output += toString(node.getLeft());

        return output;
    }

    public int size() {

        return size(root);

    }

    private int size(BinaryTreeNode node) {

        if (node == null) return 0;
        //add one for the current node and count recursively for both children
        return 1 + size(node.getLeft()) + size(node.getRight());

    }

    public int depth() {

        return depth(root, 0);

    }

    private int depth(BinaryTreeNode node, int level) {
        //if the node at the current depth is null, than we go one step back
        if (node == null) return level - 1;
        //go recursively one level deeper for both parts
        int nLeft = depth(node.getLeft(), level + 1);
        int nRight = depth(node.getRight(), level + 1);
        //now we compare which part is deeper
        if (nLeft > nRight) return nLeft;
        else                return nRight;

    }

    public String toStringDescending() {

        String output = toStringDescending(root);
        //there was one comma too many
        return output.substring(0, output.length() - 1);

    }

    private String toStringDescending(BinaryTreeNode node) {

        if (node == null) return "";

        return toStringDescending(node.getRight()) +
                node.data + "," + toStringDescending(node.getLeft());

    }

    //I am using the toStringDescending method to get the list of all the node data
    //and then I compare, it should be strictly descending
    public boolean isBinarySearchTree() {

        String[] listDesc = toStringDescending().trim().split(",");

        int i = 0, n = size();
        while(++i < n)
            if (Integer.parseInt(listDesc[i - 1]) <= Integer.parseInt(listDesc[i])) return false;

        return true;

    }


    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        tree.insert(5);
        tree.insert(7);
        tree.insert(2);
        tree.insert(1);
        tree.insert(9);
        tree.insert(1);
        tree.insert(6);

        System.out.println(tree);

        System.out.println("Contains 9: \t" + tree.contains(9));

        System.out.println("Size: \t" + tree.size());

        System.out.println("Depth: \t" + tree.depth());

        System.out.println("Desc: \t" + tree.toStringDescending());

        System.out.println("IsBinarySearchTree: \t" + tree.isBinarySearchTree());

    }

}
