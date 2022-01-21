import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Deque;

public class RootedTree {
    public TreeNode source;

    public RootedTree(TreeNode source) {
        this.source = source;
    }

    public void printinfo() {
        System.out.println("source:");
        source.printinfo();
        if (source.leftChild != null) {
            System.out.println("left child:");
            source.leftChild.printinfo();
        }
        if (source.rightSibling != null) {
            System.out.println("right sibling:");
            source.rightSibling.printinfo();
        }
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        if (this.source == null) {
            return;
        }
        LinkedList<Queue<TreeNode>> levelsList = new LinkedList<>();
        levelsList.head = new LinkedListNode<>(new Queue<>());
        LinkedListNode<Queue<TreeNode>> curLocationInQList = levelsList.head; //pointer
        curLocationInQList.value.Enqueue(new LinkedListNode<>(this.source));
        boolean from = true; // came from parent_sibling
        TreeNode x = this.source;
        boolean first = true;
        while (x != null) {
            if (from) {
                first = false;
                if (x.leftChild != null) {
                    x = x.leftChild;
                    if (curLocationInQList.next == null){
                        levelsList.insertAfter(new LinkedListNode<>(new Queue<>()), curLocationInQList);
                    }
                    curLocationInQList = curLocationInQList.next;
                    curLocationInQList.value.Enqueue(new LinkedListNode<>(x));
                } else {
                    if (x.rightSibling != null) {
                        x = x.rightSibling;
                        curLocationInQList.value.Enqueue(new LinkedListNode<>(x));
                    } else {
                        from = false; // came from child
                        x = x.parent;
                        curLocationInQList = curLocationInQList.prev;
                    }
                }
            } else {
                if (x.rightSibling != null) {
                    from = true;
                    x = x.rightSibling;
                    curLocationInQList.value.Enqueue(new LinkedListNode<>(x));
                } else {
                    x = x.parent;
                    curLocationInQList = curLocationInQList.prev;
                }
            }
        }
        // print:
        curLocationInQList = levelsList.head;
        while (curLocationInQList != null) {
            Queue<TreeNode> Q = curLocationInQList.value;
            first = true;

            while (!Q.L.isEmpty()) {
                if (!first) {
                    out.writeBytes(",");
                }
                first = false;
                LinkedListNode<TreeNode> y = Q.Dequeue();
                out.writeBytes(String.valueOf(y.value.getKey()));
            }
            curLocationInQList = curLocationInQList.next;
            if (curLocationInQList != null) {
                out.writeBytes(String.valueOf('\n'));
            }
        }
    }



    public void preorderPrint(DataOutputStream out){}
}
