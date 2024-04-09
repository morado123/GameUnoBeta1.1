import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import structures.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class PilasTest {
    private Stack stack;

    @BeforeEach
    public void setUp() {
        // Inicialización de la pila antes de cada prueba
        stack = new Stack();
    }

    @Test
    public void testPush() {
        // Caso de prueba estándar
        stack.push("Carta 1");
        assertEquals(1, stack.size());
    }

    @Test
    public void testPop() {
        // Caso de prueba estándar
        stack.push("Carta 1");
        stack.push("Carta 2");
        assertEquals("Carta 2", stack.pop());
        assertEquals(1, stack.size());
    }

    @Test
    public void testPeek() {
        // Caso de prueba estándar
        stack.push("Carta A");
        stack.push("Carta B");
        assertEquals("Carta B", stack.peek());
    }

    @Test
    public void testIsEmpty() {
        // Caso de prueba estándar
        assertTrue(stack.isEmpty());

        // Caso de prueba después de agregar una carta
        stack.push("Carta X");
        assertFalse(stack.isEmpty());
    }
}
