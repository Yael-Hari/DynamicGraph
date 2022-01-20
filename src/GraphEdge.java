public class GraphEdge {
    GraphNode fromNode;
    GraphNode toNode;
    LinkedListNode<GraphEdge> refToEdgeList = null;
    LinkedListNode<GraphEdge> refToInList = null;
    LinkedListNode<GraphEdge> refToOutList = null;

    public GraphEdge(GraphNode newFrom, GraphNode newTo){
        this.fromNode=newFrom;
        this.toNode=newTo;
    }

    public void printinfo(){
        System.out.print("("+fromNode.key+","+toNode.key+"), ");
    }

    public void setRefToEdgeList(LinkedListNode<GraphEdge> refToEdgeList) {
        this.refToEdgeList = refToEdgeList;
    }

    public LinkedListNode<GraphEdge> getRefToEdgeList() {
        return this.refToEdgeList;
    }

    public GraphNode getFromNode() {
        return this.fromNode;
    }

    public GraphNode getToNode() {
        return this.toNode;
    }
}
