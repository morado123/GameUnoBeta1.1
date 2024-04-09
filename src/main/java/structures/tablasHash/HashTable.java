package structures.tablasHash;

import structures.GenericLinkendList.LinkedList;
import structures.GenericLinkendList.ListNode;

import java.util.ArrayList;

// Clase para representar una tabla hash, desde cero utilizando listas enlazadas para manejar las colisiones
public class HashTable<K, V> {
    private ArrayList<LinkedList<Pair<K, V>>> buckets; // Arreglo de listas enlazadas


    public HashTable(int capacity) {
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new LinkedList<>());
        }
    }

    //  para obtener el índice de la tabla hash para una clave dada
    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.size());
    }

    // Método para agregar un par clave-valor a la tabla hash
    public void put(K key, V value) {
        int index = hash(key);
        buckets.get(index).add(new Pair<>(key, value));
    }

    //  para obtener el valor asociado a una clave de la tabla hash
    public V get(K key) {
        int index = hash(key);
        LinkedList<Pair<K, V>> bucket = buckets.get(index);
        for (ListNode<Pair<K, V>> current = bucket.head; current != null; current = current.next) {
            Pair<K, V> pair = current.data;
            if (pair.key.equals(key)) {
                return pair.value;
            }
        }
        return null; // Eyy no se encontro la clase
    }


}
