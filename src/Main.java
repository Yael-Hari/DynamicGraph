import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.System.out;

class Main {
    public static void main(String[] args) throws IOException {
        DynamicGraph dg = new DynamicGraph();
        int [] vals = {7,4,2,1,5,9,8,11};
        GraphNode [] nodes = new GraphNode[vals.length];
        for(int i=0;i<vals.length;i++){
            nodes[i] = dg.insertNode(vals[i]);
        }
        int [][] concts = {{0,2},{0,1},{2,4},{4,2},{1,3},{3,6},{3,5}};
        for(int i=0;i<concts.length;i++){
            dg.insertEdge(nodes[concts[i][0]],nodes[concts[i][1]]);
        }
        RootedTree t = dg.bfs(nodes[0]);
        DataOutputStream outs = new DataOutputStream(out);
        t.printByLayer(outs);
        outs.writeBytes(System.lineSeparator());
        t.preorderPrint(outs);
        outs.writeBytes(System.lineSeparator());
        RootedTree t2 = dg.scc();
        t2.printByLayer(outs);
        outs.writeBytes(System.lineSeparator());
        t2.preorderPrint(outs);
        outs.writeBytes(System.lineSeparator());
        t2 = dg.scc();
        t2.printByLayer(outs);
        outs.writeBytes(System.lineSeparator());
        t2.preorderPrint(outs);
        outs.writeBytes(System.lineSeparator());
        outs.close();

    }
}