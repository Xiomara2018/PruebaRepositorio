package estructuras;

public class Cola<T> {
    private NodeQueue<T> first;
    private NodeQueue<T> last;
    private int size;

    public Cola(){
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public boolean IsEmpty(){
        return first == null;
    }

    public void enqueque(T x){
        NodeQueue<T> newnode = new NodeQueue<>(x);
        if (IsEmpty()){
            first = newnode;
            last = newnode;
        }
        else{
            last.next = newnode;
            last  = newnode;


        }
        size++;
    }

    public T dequeue(){
        if(IsEmpty()){
            System.out.println("La cola de solicitudes esta vacia");
        }
        T aux = this.first.getdata();
        this.first = this.first.next;
        if (this.first == null){
            this.last = null;
        }
        size--;

        return aux;
    }

    public T peek(){
        T daten = this.first.getdata();
        if (daten == null){
            return null;
        }
        return daten;
    }

    public int size(){
        return size;
    }

    public void mostrar(){
        NodeQueue<T> rec = first;
        
        if(IsEmpty()){
            System.out.println("La cola de solicitudes esta vacia");
        }
        System.out.print("Cola graficada");
        while (rec.next != null){
            System.out.print(rec.getdata() + " -> ");
            rec = rec.next;
        }

    }
    
}
