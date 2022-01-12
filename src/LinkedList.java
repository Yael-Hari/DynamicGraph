public class LinkedList<F> {
    LinkedListNode<F> head = null;

    public LinkedListNode<F> getHead() {
        return head;
    }

    public boolean isEmpty(){
        if (this.head == null) {
            return true;
        }
        return false;
    }

//    public <T> LinkedListNode<F> listSearch(T value) {
//        LinkedListNode x = this.head;
//        while (x != null && x.getValue() != value) {
//            x = x.getNext();
//        }
//        return x;
//    }

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

    public int getLength() {
        int length = 0;
        LinkedListNode<F> x = this.head;
        while (x != null) {
            length += 1;
            x = x.getNext();
        }
        return length;
    }

    public void insertAfter(LinkedListNode<F> toAdd, LinkedListNode<F> thePrev) {
        if (thePrev.next != null) {
            thePrev.next.prev = toAdd;
        }
        toAdd.next = thePrev.next;
        toAdd.prev = thePrev;
        thePrev.next = toAdd;
    }
}


