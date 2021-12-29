public class DynamicGraph {
    // linked list for graphNodes
    LinkedList graphNodesList = new LinkedList();
    // linked list for edges

    public DynamicGraph(){
    }

    public GraphNode insertNode(int nodeKey){}

    public void deleteNode(GraphNode node){}
    public GraphEdge insertEdge(GraphNode from, GraphNode to){}
    public void deleteEdge(GraphEdge edge){}
    public RootedTree scc(){}
    public RootedTree bfs(GraphNode source){}

}
