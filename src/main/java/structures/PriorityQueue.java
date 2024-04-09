package structures;

import java.util.ArrayList;
import java.util.Comparator;

// Clase para representar una cola de prioridad, desde cero utilizando un montículo binario el famoso (heap)
public class PriorityQueue<T> {
    private final ArrayList<T> heap; // Montículo binario como tal
    private final Comparator<T> comparator; // Comparador para determinar la prioridad de los elementos


    public PriorityQueue(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    //  para verificar si la cola de prioridad está vacía
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    //  para agregar un elemento a la cola de prioridad
    public void add(T element) {
        heap.add(element);
        bubbleUp(heap.size() - 1);
    }

    //  para eliminar y devolver el elemento de mayor prioridad de la cola de prioridad
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola de prioridad está vacía");
        }
        T removedElement = heap.get(0);
        int lastIndex = heap.size() - 1;
        heap.set(0, heap.get(lastIndex));
        heap.remove(lastIndex);
        if (!isEmpty()) {
            bubbleDown(0);
        }
        return removedElement;
    }

    //  para "subir" un elemento en el montículo binario
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parentIndex)) < 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    //  para "bajar" un elemento en el montículo binario
    private void bubbleDown(int index) {
        int lastIndex = heap.size() - 1;
        while (true) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallestChildIndex = leftChildIndex;
            if (rightChildIndex <= lastIndex && compare(heap.get(rightChildIndex), heap.get(leftChildIndex)) < 0) {
                smallestChildIndex = rightChildIndex;
            }
            if (leftChildIndex <= lastIndex && compare(heap.get(smallestChildIndex), heap.get(index)) < 0) {
                swap(index, smallestChildIndex);
                index = smallestChildIndex;
            } else {
                break;
            }
        }
    }

    //  para comparar dos elementos utilizando el comparador
    private int compare(T a, T b) {
        return comparator.compare(a, b);
    }

    //  para intercambiar dos elementos en el montículo binario
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
