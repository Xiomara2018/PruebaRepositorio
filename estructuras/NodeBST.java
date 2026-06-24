package estructuras;

public class NodeBST<T> {
	public T data;
	public NodeBST<T> right ;
	public NodeBST<T> left ;	
	
	public NodeBST(T data) {
		this.data = data;
		this.right = null;
		this.left = null;
	}
	
	
	//construtor para nodo hijos
	public NodeBST(T data, NodeBST<T> left, NodeBST<T> right) {
		this.data = data;
        this.left = left;
        this.right = right;
    }


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}

	
		
	
	
}

