public class LinkedList {
    private LinkedListNode head;

    public LinkedListNode getHead() {
        return head;
    }

    public boolean isEmpty(){
        if (this.head == null) {
            return true;
        }
        return false;
    }

    public LinkedListNode listSearch(int key){
        LinkedListNode x = this.head;
        while (x != null && x.getKey() != key){
            x = x.getNext();
        }
        return x;
    }

    public void insertToStart(LinkedListNode newNode){
        newNode.next = this.head;
        if (this.head != null){
            this.head.setPrev(newNode);
        }
        this.head = newNode;
        this.head.prev = null;
    }

//    didnt implement insert after, no need.

    public void listDelete(LinkedListNode nodeToDelete){
        if (nodeToDelete.getPrev() != null){
            nodeToDelete.getPrev().setNext(nodeToDelete.getNext());
        }
        else {
            this.head = nodeToDelete.getNext();
        }
        if (nodeToDelete.getNext() != null){
            nodeToDelete.getNext().setPrev(nodeToDelete.getPrev());
        }
    }
}


