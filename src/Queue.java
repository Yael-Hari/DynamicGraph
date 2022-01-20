public class Queue<R>{
    LinkedListNode<R> tail = null;
    LinkedList<R> L = new LinkedList<>();

    public Queue(){}

    public void Enqueue(LinkedListNode<R> newDatumn){
        if (this.tail != null){
            L.insertAfter(newDatumn,this.tail);
        }
        else
            L.insertToStart(newDatumn);
        this.tail=newDatumn;
    }

    public LinkedListNode<R> Dequeue(){
        if(this.L.isEmpty()){
            return null;
        }
        LinkedListNode<R> toReturn = this.L.getHead();
        this.L.listDelete(toReturn);
    return toReturn;
    }
}

