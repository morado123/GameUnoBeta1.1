import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import structures.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class ColasTest {
    private Queue queue;

    @BeforeEach
    public void setUp() {
        // Inicialización de la cola antes de cada prueba
        queue = new Queue();
    }

    @Test
    public void testEnqueue() {
        // Caso de prueba estándar
        queue.enqueue("Carta X");
        assertEquals(1, queue.size());
    }

    @Test
    public void testDequeue() {
        // Caso de prueba estándar
        queue.enqueue("Carta 1");
        queue.enqueue("Carta 2");
        assertEquals("Carta 1", queue.dequeue());
        assertEquals(1, queue.size());
    }

    @Test
    public void testPeek() {
        // Caso de prueba estándar
        queue.enqueue("Carta A");
        queue.enqueue("Carta B");
        assertEquals("Carta A", queue.peek());
    }

    @Test
    public void testIsEmpty() {
        // Caso de prueba estándar
        assertTrue(queue.isEmpty());

        // Caso de prueba después de agregar una carta
        queue.enqueue("Carta X");
        assertFalse(queue.isEmpty());
    }
}
