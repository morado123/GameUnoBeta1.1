import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ColasPrioritariasTest {
    private PriorityQueue priorityQueue;

    @BeforeEach
    public void setUp() {
        // Inicialización de la cola de prioridad antes de cada prueba
        priorityQueue = new PriorityQueue();
    }

    @Test
    public void testInsert() {
        // Caso de prueba estándar
        priorityQueue.insert("Jugador 1", 1);
        assertEquals(1, priorityQueue.size());
    }

    @Test
    public void testDeleteMin() {
        // Caso de prueba estándar
        priorityQueue.insert("Jugador A", 3);
        priorityQueue.insert("Jugador B", 1);
        assertEquals("Jugador B", priorityQueue.deleteMin());
        assertEquals(1, priorityQueue.size());
    }

    @Test
    public void testPeek() {
        // Caso de prueba estándar
        priorityQueue.insert("Jugador A", 3);
        priorityQueue.insert("Jugador B", 1);
        assertEquals("Jugador B", priorityQueue.peek());
    }

    @Test
    public void testIsEmpty() {
        // Caso de prueba estándar
        assertTrue(priorityQueue.isEmpty());

        // Caso de prueba después de insertar un jugador
        priorityQueue.insert("Jugador X", 2);
        assertFalse(priorityQueue.isEmpty());
    }
}
