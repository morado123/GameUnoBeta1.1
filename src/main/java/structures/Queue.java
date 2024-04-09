package structures;

import structures.GenericLinkendList.ListNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Clase para representar una cola, desde cero utilizando una lista enlazada genérica
public class Queue<T> implements Iterable<T> {
    private ListNode<T> front; // Referencia al primer elemento de la cola
    private ListNode<T> rear; // Referencia al último elemento de la cola


    public Queue() {
        this.front = null;
        this.rear = null;
    }

    // Método para verificar si la cola está vacía
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode<T> current = front;
        while (current != null) {
            sb.append(current.data);
            if (current != rear) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }


    // Método para agregar un elemento al final de la cola
    public void enqueue(T data) {
        ListNode<T> newNode = new ListNode<>(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    // Método para eliminar y devolver el primer elemento de la cola
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    // Método para obtener el primer elemento de la cola sin eliminarlo
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("La cola está vacía");
        }
        return front.data;
    }

    // Método para obtener el número de elementos en la cola
    public int size() {
        int count = 0;
        ListNode<T> current = front;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Implementación del método iterator para la interfaz Iterable
    @Override
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    // Clase interna para el iterador de la cola
    private class QueueIterator implements Iterator<T> {
        private ListNode<T> current = front;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
