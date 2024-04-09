package structures.GenericLinkendList;

// Clase para representar una lista enlazada genérica
public class LinkedList<T> {
    public ListNode<T> head; // Referencia al primer nodo de la lista

    // Constructor
    public LinkedList() {
        this.head = null;
    }

    // Método para agregar un elemento al final de la lista
    public void add(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        if (head == null) {
            head = newNode;
        } else {
            ListNode<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    }