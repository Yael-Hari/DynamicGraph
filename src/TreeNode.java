public class TreeNode {
    GraphNode GraphNode;
    TreeNode parent = null;
    TreeNode rightSibling = null;
    TreeNode leftChild = null;

    public TreeNode(){
        this.GraphNode = null;
    }


    public int getKey(){
        return this.GraphNode.getKey();
    }
}
