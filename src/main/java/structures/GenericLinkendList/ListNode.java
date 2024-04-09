package structures.GenericLinkendList;

// Clase para representar un nodo en la lista enlazada
public class ListNode<T> {
    public T data; // Datos del nodo
    public ListNode<T> next; // Referencia al siguiente nodo

    // Constructor
    public ListNode(T data) {
        this.data = data;
        this.next = null;
    }
}