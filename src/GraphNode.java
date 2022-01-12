public class GraphNode{
    LinkedList<GraphEdge> outEdges = new LinkedList<>();
    LinkedList<GraphEdge> inEdges = new LinkedList<>();
    LinkedListNode<GraphNode> refToNodeList = null;
    int key;
    int d; //BFS - distances. DFS = time of discovery
    int color;  //0 = white, 1 = grey, 2 = black
    int f; //time of retraction
    GraphNode parent;

    public GraphNode(int key){
        this.key = key;
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
