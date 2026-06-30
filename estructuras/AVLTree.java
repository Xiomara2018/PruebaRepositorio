package estructuras;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<E extends Comparable<E>> {

    private NodeAVL<E> root;

    // constructor
    public AVLTree() {
        this.root = null;
    }
    // ALTURA DEL NODO
    private int height(NodeAVL<E> node) {
        if (node == null) return 0;
        return node.height;
    }
    // FACTOR DE BALANCE
    // izquierda - derecha
    private int getBalance(NodeAVL<E> node) {
        if (node == null) return 0;
        return height(node.left) - height(node.right);
    }
    // ROTACIÓN DERECHA
    private NodeAVL<E> rightRotate(NodeAVL<E> y) {

        NodeAVL<E> x = y.left;
        NodeAVL<E> T2 = x.right;

        // rotación
        x.right = y;
        y.left = T2;

        // actualizar alturas
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }
    // ROTACIÓN IZQUIERDA
    private NodeAVL<E> leftRotate(NodeAVL<E> x) {

        NodeAVL<E> y = x.right;
        NodeAVL<E> T2 = y.left;

        // rotación
        y.left = x;
        x.right = T2;

        // actualizar alturas
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }
    // INSERTAR
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private NodeAVL<E> insertRec(NodeAVL<E> node, E data) throws ItemDuplicated {

        if (node == null) {
            return new NodeAVL<>(data);
        }

        int cmp = data.compareTo(node.data);

        if (cmp == 0) {
            throw new ItemDuplicated("Elemento duplicado");
        }

        if (cmp < 0) {
            node.left = insertRec(node.left, data);
        } else {
            node.right = insertRec(node.right, data);
        }

        // actualizar altura
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // balance
        int balance = getBalance(node);

        // LL
        if (balance > 1 && data.compareTo(node.left.data) < 0)
            return rightRotate(node);

        // RR
        if (balance < -1 && data.compareTo(node.right.data) > 0)
            return leftRotate(node);

        // LR
        if (balance > 1 && data.compareTo(node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && data.compareTo(node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    // BUSCAR
    public E search(E data) throws ItemNotfound {
        return searchRec(root, data);
    }

    private E searchRec(NodeAVL<E> node, E data) throws ItemNotfound {

        if (node == null) {
            throw new ItemNotfound("No encontrado");
        }

        int cmp = data.compareTo(node.data);

        if (cmp == 0) return node.data;

        if (cmp < 0)
            return searchRec(node.left, data);

        return searchRec(node.right, data);
    }
    // ENCONTRAR MENOR 
    private NodeAVL<E> minValueNode(NodeAVL<E> node) {
        NodeAVL<E> current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }
    // ELIMINAR
    public void delete(E data) {
        root = deleteRec(root, data);
    }

    private NodeAVL<E> deleteRec(NodeAVL<E> node, E data) {

        if (node == null) return null;

        int cmp = data.compareTo(node.data);

        if (cmp < 0) {
            node.left = deleteRec(node.left, data);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, data);
        } else {

            // caso 1: un hijo o ninguno
            if (node.left == null || node.right == null) {
                NodeAVL<E> temp = (node.left != null) ? node.left : node.right;

                if (temp == null) {
                    node = null;
                } else {
                    node = temp;
                }
            } else {

                // caso 2: dos hijos
                NodeAVL<E> temp = minValueNode(node.right);
                node.data = temp.data;
                node.right = deleteRec(node.right, temp.data);
            }
        }

        if (node == null) return null;

        // actualizar altura
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // balance
        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }
    // INORDEN
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(NodeAVL<E> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.data);
            inOrderRec(node.right);
        }
    }

    public List<E> obtenerListaLibros() {
        List<E> lista = new ArrayList<>();
        obtenerListaRec(root, lista);
        return lista;
    }

    // Método recursivo auxiliar para llenar la lista en In-Orden
    private void obtenerListaRec(NodeAVL<E> node, List<E> lista) {
        if (node != null) {
            obtenerListaRec(node.left, lista);   // Recorre el subárbol izquierdo
            lista.add(node.data);                // Agrega el dato (Libro) a la lista
            obtenerListaRec(node.right, lista);  // Recorre el subárbol derecho
        }
    }
    
    // CONTAR NODOS
    public int count() {
        return countRec(root);
    }

    private int countRec(NodeAVL<E> node) {
        if (node == null) return 0;
        return 1 + countRec(node.left) + countRec(node.right);
    }
    // VERIFICAR VACÍO
    public boolean isEmpty() {
        return root == null;
    }
}
