public class LinkedListNode<T> {
    T value;
    LinkedListNode<T> next = null;
    LinkedListNode<T> prev = null;

    public LinkedListNode(){}

    public LinkedListNode(T value){
        this.value = value;
    }

    public LinkedListNode<T> getPrev(){
        return this.prev;
    }

    public LinkedListNode<T> getNext(){
        return this.next;
    }

    public T getValue(){
        return value;
    }

    public void setValue(T value){
        this.value = value;
    }

    public void setNext(LinkedListNode<T> next){
        this.next = next;
    }

    public void setPrev(LinkedListNode<T> prev){
        this.prev = prev;
    }


}
