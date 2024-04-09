package structures;

import structures.GenericLinkendList.ListNode;

import java.util.EmptyStackException;

// Clase para representar una pila, desde cero utilizando una lista enlazada genérica
public class Stack<T> {
    private ListNode<T> top; // Referencia al elemento superior de la pila


    public Stack() {
        this.top = null;
    }

    // Método para verificar si la pila está vacía
    public boolean isEmpty() {
        return top == null;
    }

    //  para insertar un elemento en la parte superior de la pila
    public void push(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        newNode.next = top;
        top = newNode;
    }

    //  para eliminar y devolver el elemento superior de la pila
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    //  para obtener el elemento superior de la pila sin eliminarlo
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }
}
