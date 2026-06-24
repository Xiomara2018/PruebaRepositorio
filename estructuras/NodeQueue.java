package estructuras;

public class NodeQueue<T>{
    private T data;
    NodeQueue<T> next;

    public NodeQueue(T daten){
        this.data = daten;
        this.next = null;
    }

    public T getdata(){return data;}
    public NodeQueue<T> getnext(){return next;}

}