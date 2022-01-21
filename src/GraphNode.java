public class GraphNode{
    LinkedList<GraphEdge> outEdges = new LinkedList<>();
    LinkedList<GraphEdge> inEdges = new LinkedList<>();
    LinkedListNode<GraphNode> refToNodeList = null;
    int key;
    int d; //BFS - distances. DFS = time of discovery
    int color;  //0 = white, 1 = grey, 2 = black
    int f; //time of retraction
    TreeNode TreeNode = null;
    GraphNode transposeNode = null;

    public GraphNode(int key){
        this.key = key;
    }

    public void printinfo(){
        System.out.println("key is " + this.key);
        System.out.print("inListEdges: ");
        this.inEdges.printinfo();
        System.out.print("outListEdges: ");
        this.outEdges.printinfo();
        System.out.println();
    }

    public void setRefToNodeList(LinkedListNode<GraphNode> refToNodeList) {
        this.refToNodeList = refToNodeList;
    }

    public LinkedListNode<GraphNode> getRefToNodeList() {
        return this.refToNodeList;
    }

    public int getOutDegree(){ return outEdges.getLength(); }
    public int getInDegree(){ return inEdges.getLength(); }
    public int getKey(){ return this.key; }

}
