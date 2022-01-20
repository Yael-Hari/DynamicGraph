import jdk.nashorn.api.tree.Tree;

public class DynamicGraph {
    // linked list for graphNodes
    LinkedList<GraphNode> graphNodesList = new LinkedList<>();
    // linked list for edges
    LinkedList<GraphEdge> graphEdgesList = new LinkedList<>();

    public void printinfo(){
        System.out.println("node list:");
        graphNodesList.printinfo();
        System.out.println("edge list:");
        graphEdgesList.printinfo();
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
        LinkedListNode<GraphEdge> addedEdgeAsLLN = new LinkedListNode<>(addedEdge);
        this.graphEdgesList.insertToStart(addedEdgeAsLLN);
        from.outEdges.insertToStart(new LinkedListNode<>(addedEdge));
        to.inEdges.insertToStart(new LinkedListNode<>(addedEdge));
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

    public RootedTree bfs(GraphNode source) {
        RootedTree GPi = new RootedTree(new TreeNode(source));
        TreeNode lastAssigned = null;
        Queue<GraphNode> Q = new Queue<>();
        bfs_initialization(source, Q);
        while (!Q.L.isEmpty()) {
            lastAssigned = null;
            LinkedListNode<GraphNode> uAsLLN = Q.Dequeue();
            LinkedListNode<GraphEdge> uEdgeNeighbor = uAsLLN.value.outEdges.getHead();
            while (uEdgeNeighbor != null) {
                GraphNode v = uEdgeNeighbor.getValue().toNode;
                if (v.color == 0) {
                    v.TreeNode = new TreeNode(v); // other direction
                    v.color = 1; //classic algorithm actions
                    v.d = uAsLLN.value.d + 1; // u.d + 1
                    v.TreeNode.parent = uAsLLN.value.TreeNode;
                    Q.Enqueue(v.getRefToNodeList());

                    if (lastAssigned == null) { //making it a l.c.r.s tree
                        uAsLLN.value.TreeNode.leftChild = v.TreeNode;

                    } else {
                        lastAssigned.rightSibling = v.TreeNode;
                    }
                    v.TreeNode.parent = uAsLLN.value.TreeNode; //u = linkedlistnode -> u.value=graphnode
                    lastAssigned = v.TreeNode;
                    uEdgeNeighbor = uEdgeNeighbor.getNext();
                }
            }
        }
        return GPi;
    }

    public void bfs_initialization(GraphNode source, Queue<GraphNode> Q){
        LinkedListNode<GraphNode> v = this.graphNodesList.getHead();
        while (v != null) {
            v.value.color = 0;
            v.value.d = Integer.MAX_VALUE;
            v = v.getNext();
        }
        source.color = 1;
        source.d = 0;
        source.parent = null;
        Q.Enqueue(source.getRefToNodeList());
    }
}












