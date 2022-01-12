public class DynamicGraph {
    // linked list for graphNodes
    LinkedList<GraphNode> graphNodesList = new LinkedList<>();
    // linked list for edges
    LinkedList<GraphEdge> graphEdgesList = new LinkedList<>();

    public DynamicGraph(){
    }

    public GraphNode insertNode(int nodeKey){
        GraphNode addedNode = new GraphNode(nodeKey);
        this.graphNodesList.insertToStart(new LinkedListNode<>(addedNode));
        addedNode.setRefToNodeList(this.graphNodesList.getHead());
        return addedNode;
    }

    public void deleteNode(GraphNode node){
        if (node.inEdges.isEmpty() && node.outEdges.isEmpty()){
            graphNodesList.listDelete(node.getRefToNodeList());
        }
    }

    public GraphEdge insertEdge(GraphNode from, GraphNode to){
        GraphEdge addedEdge = new GraphEdge(from, to);
        this.graphEdgesList.insertToStart(new LinkedListNode<>(addedEdge));
        addedEdge.setRefToEdgeList(this.graphEdgesList.getHead());

        from.outEdges.insertToStart(graphEdgesList.getHead());
        to.inEdges.insertToStart(graphEdgesList.getHead());

        addedEdge.refToOutList = from.outEdges.getHead();
        addedEdge.refToInList = to.inEdges.getHead();
        return addedEdge;
    }

    public void deleteEdge(GraphEdge edge) {
        edge.fromNode.outEdges.listDelete(edge.refToOutList);
        edge.toNode.inEdges.listDelete(edge.refToInList);
        this.graphEdgesList.listDelete(edge.refToEdgeList);
    }

    public RootedTree scc(){}

    public void dfs(){}

    public RootedTree bfs(GraphNode source){
        RootedTree treePi = new RootedTree();
        TreeNode lastAssigned = new TreeNode();
        Queue<GraphNode> Q = new Queue<>();
        bfs_initialization(source, Q);
        while (!Q.L.isEmpty()){
            lastAssigned=null;
            LinkedListNode<GraphNode> u = Q.Dequeue(); //change
            LinkedListNode<GraphEdge> uEdgeNeighbor = u.value.outEdges.getHead();
            while (uEdgeNeighbor != null) {
                GraphNode v = uEdgeNeighbor.getValue().toNode;
                if (v.color == 0) {
                    v.color = 1;
                    v.d = u.value.d + 1;
                    v.parent = u.value;
                    Q.Enqueue();

                    if(lastAssigned==null){
                        v.parent.leftChild = v;
                    }
                    else{
                        lastAssigned.rightSibling=v;
                    }
                    lastAssigned=v;
                }




                uEdgeNeighbor = uEdgeNeighbor.getNext();
            }
        }
    }

    public void bfs_initialization(GraphNode source, Queue<GraphNode> Q){
        LinkedListNode<GraphNode> v = this.graphNodesList.getHead();
        while (v != null) {
            v.value.color = 0;
            v.value.d = Integer.MAX_VALUE;
            v.value.parent = null;
            v = v.getNext();
        }
        source.color = 1;
        source.d = 0;
        source.parent = null;
        Q.Enqueue(source.getRefToNodeList());
    }
}












