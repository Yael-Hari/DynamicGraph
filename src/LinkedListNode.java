public class LinkedListNode {
    int key;
    LinkedListNode next = null;
    LinkedListNode prev = null;

    public LinkedListNode(){}

    public LinkedListNode(int key){
        this.key = key;
    }

    public LinkedListNode(int key, LinkedListNode next, LinkedListNode prev){
        this.key = key;
        this.next = next;
        this.prev = prev;
    }

    public LinkedListNode getPrev(){
        return this.prev;
    }

    public LinkedListNode getNext(){
        return this.next;
    }

    public int getKey(){
        return key;
    }

    public void setNext(LinkedListNode next){
        this.next = next;
    }

    public void setPrev(LinkedListNode prev){
        this.prev = prev;
    }

}
