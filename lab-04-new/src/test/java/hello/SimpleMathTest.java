package hello;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A simple text as demonstration to show how Java JUnit test works
 */
public class SimpleMathTest {
    @Test
    public void add() throws Exception {
        assertEquals(SimpleMath.add(1, 2), 3);
    }
}
