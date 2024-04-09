import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import structures.tablasHash.HashTable;

import static org.junit.jupiter.api.Assertions.*;

public class TablasHashTest {
    private HashTable hashTable;

    @BeforeEach
    public void setUp() {
        // Inicialización de la tabla hash antes de cada prueba
        hashTable = new HashTable();
    }

    @Test
    public void testInsert() {
        // Caso de prueba estándar
        hashTable.insert("Carta 1", new CartaInfo());
        assertTrue(hashTable.containsKey("Carta 1"));
    }

    @Test
    public void testSearch() {
        // Caso de prueba estándar
        hashTable.insert("Carta A", new CartaInfo());
        assertNotNull(hashTable.search("Carta A"));
    }

    @Test
    public void testDelete() {
        // Caso de prueba estándar
        hashTable.insert("Carta 1", new CartaInfo());
        assertTrue(hashTable.containsKey("Carta 1"));
        hashTable.delete("Carta 1");
        assertFalse(hashTable.containsKey("Carta 1"));
    }

    @Test
    public void testIsEmpty() {
        // Caso de prueba estándar
        assertTrue(hashTable.isEmpty());

        // Caso de prueba después de insertar una carta
        hashTable.insert("Carta X", new CartaInfo());
        assertFalse(hashTable.isEmpty());
    }
}
