public class DynamicGraph {
    // linked list for graphNodes
    LinkedList<GraphNode> graphNodesList = new LinkedList<>();
    // linked list for edges
    LinkedList<GraphEdge> graphEdgesList = new LinkedList<>();
    static int time;
    static int count=0;


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

    public RootedTree bfs(GraphNode sourceAsGraphNode) {
        RootedTree GPi = new RootedTree(new TreeNode(sourceAsGraphNode));
        //sourceAsGraphNode.TreeNode=GPi.source;
        TreeNode lastAssigned;
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
                    }
                    else {
                        lastAssigned.rightSibling = v.TreeNode;
                    }
                    v.TreeNode.parent = uAsLLN.value.TreeNode; //u = linkedlistnode -> u.value=graphnode
                    lastAssigned = v.TreeNode;
                }
                uEdgeNeighbor = uEdgeNeighbor.getNext();
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

    public RootedTree scc(){
        LinkedList<GraphNode> fSortedList = dfs1();
        DynamicGraph transposeGraph = transposeCopy(fSortedList, this.graphEdgesList);
        Queue<TreeNode> sourcesQueue = dfs2(transposeGraph);
        return makeVirtualTree(sourcesQueue);
    }

    public RootedTree makeVirtualTree(Queue<TreeNode> sourcesQueue){
        RootedTree result = new RootedTree(new TreeNode(new GraphNode(0)));
        TreeNode lastAssigned = null;
        while (!sourcesQueue.L.isEmpty()){
            LinkedListNode<TreeNode> dequeuedNode = sourcesQueue.Dequeue();
            if (lastAssigned == null) {
                result.source.leftChild = dequeuedNode.value;
            } else {
                lastAssigned.rightSibling = dequeuedNode.value;
            }
            lastAssigned = dequeuedNode.value;
//            lastAssigned = setChildOrSibling(result.source, dequeuedNode.value.GraphNode, lastAssigned);
        }
        return result;
    }

    public LinkedList<GraphNode> dfs1(){
        time = 0;
        LinkedList<GraphNode> fSortedList = new LinkedList<>();
        // initialization
        LinkedListNode<GraphNode> curNode = this.graphNodesList.getHead();
        while (curNode != null) {
            curNode.value.color = 0;
            curNode = curNode.getNext();
        }

        // go over all vertices:
        curNode = this.graphNodesList.getHead();
//        int ek = 0;
        while (curNode != null) {
            if (curNode.value.color == 0) {
//                ek++;
                dfsVisit1(curNode.value, fSortedList);
            }
            curNode = curNode.getNext();
        }
//        System.out.println("ek is " + ek);
//        System.out.println("###################" + this.graphNodesList.getLength());
//        System.out.println("@@@@@@@@@@@@@@@@@@@" + fSortedList.getLength());
//        System.out.println("********** count: " + count);
//        System.out.println("time:::::" + time);

        return fSortedList;
    }


    public void printColors(LinkedList<GraphNode> list){
        LinkedListNode<GraphNode> ind = list.getHead();
        if(ind==null){
            System.out.println("bye lol");
            return;
        }
        while(ind.getNext()!=null){
            System.out.print(ind.value.color);
            ind=ind.getNext();
        }
        System.out.println();
    }

    public void dfsVisit1(GraphNode u, LinkedList<GraphNode> fSortedList){
        // discovery
        time++;
        u.d = time;
        u.color = 1;
//        System.out.println(fSortedList.getLength());
//        printColors(this.graphNodesList);

        LinkedListNode<GraphEdge> edgeNeighbor = u.outEdges.getHead();
        while (edgeNeighbor != null) {
            GraphNode nodeNeighbor = edgeNeighbor.getValue().toNode;
            if (nodeNeighbor.color == 0) {
                count++;
                dfsVisit1(nodeNeighbor, fSortedList);
//                System.out.println(nodeNeighbor.color);
            }
            edgeNeighbor = edgeNeighbor.getNext();
        }
//        System.out.println(u.key);
        u.printinfo();
        u.color = 2;
//        u.added=true;
        time++;
        u.f = time;
        fSortedList.insertToStart(new LinkedListNode<>(u));

    }

    public Queue<TreeNode> dfs2(DynamicGraph G){
        time = 0;
        Queue<TreeNode> sourcesList = new Queue<>();
        // initialization
        LinkedListNode<GraphNode> curNode = G.graphNodesList.getHead(); //transposed
        while (curNode != null) {
            curNode.value.color = 0;
            curNode = curNode.getNext();
        }

        // go over all vertices:
        curNode = G.graphNodesList.getHead();
        while (curNode != null) {
            if (curNode.value.color == 0) {
//                THIS MEANS WE FOUND A NEW SUB-ROOT <===> WE FOUND A CHILD OF VIRTUAL VERTIX S
//                THIS MEANS WE NEED TO CREATE A NEW LINKEDLISTNODE
                TreeNode subSource = new TreeNode(curNode.value);
                sourcesList.Enqueue(new LinkedListNode<>(subSource));
                dfsVisit2(subSource);
            }
            curNode = curNode.getNext();
        }
        return sourcesList;
    }

    public void dfsVisit2(TreeNode s){
        // discovery
        time++;
        s.GraphNode.d = time;
        s.GraphNode.color = 1;
        TreeNode lastAssigned = null;

        LinkedListNode<GraphEdge> edgeNeighbor = s.GraphNode.outEdges.getHead();
        while (edgeNeighbor != null) {
            GraphNode nodeNeighbor = edgeNeighbor.getValue().toNode;
            if (nodeNeighbor.color == 0) {
                lastAssigned = setChildOrSibling(s, nodeNeighbor, lastAssigned);
                dfsVisit2(nodeNeighbor.TreeNode);
            }
            edgeNeighbor = edgeNeighbor.getNext();
        }
        s.GraphNode.color = 2;
        time++;
        s.GraphNode.f = time;
    }

    public TreeNode setChildOrSibling(TreeNode root, GraphNode potentialChild, TreeNode lastAssigned){
        TreeNode potentialChildAsTreeNode = new TreeNode(potentialChild);
        if(lastAssigned == null){
            root.leftChild=potentialChildAsTreeNode;
            potentialChildAsTreeNode.parent=root;
            lastAssigned = potentialChildAsTreeNode;
            return lastAssigned;
        }
        lastAssigned.rightSibling=potentialChildAsTreeNode;

        potentialChildAsTreeNode.parent=root;
        lastAssigned = potentialChildAsTreeNode;
        return lastAssigned;
    }

    public DynamicGraph transposeCopy(LinkedList<GraphNode> NodesList, LinkedList<GraphEdge> EdgesList){
        DynamicGraph transposeGraph = new DynamicGraph();

        // add all nodes fresh with only key to transpose nodesList:
        LinkedListNode<GraphNode> curOriginalNode = NodesList.getTail(); // O(n)
        while (curOriginalNode != null) {
            GraphNode newNode = transposeGraph.insertNode(curOriginalNode.getValue().getKey()); // inserting new node
            curOriginalNode.value.transposeNode = newNode;
            curOriginalNode = curOriginalNode.getPrev();
        }
//        System.out.println("$$$$$$$$$$" + this.graphNodesList.getLength());
//        System.out.println("%%%%%%%%%%" + NodesList.getLength());

        // add all edges left side right:
        LinkedListNode<GraphEdge> curOriginalEdge = EdgesList.getTail();
        while (curOriginalEdge != null) {
            GraphNode newFrom = curOriginalEdge.value.toNode.transposeNode;
            GraphNode newTo = curOriginalEdge.value.fromNode.transposeNode;
            transposeGraph.insertEdge(newFrom, newTo);
            curOriginalEdge = curOriginalEdge.getPrev();
        }
        return transposeGraph;
    }



}










