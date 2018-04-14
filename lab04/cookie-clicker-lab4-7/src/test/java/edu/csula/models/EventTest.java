package edu.csula.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {
	@Test
	public void testEquals() throws Exception {
		Event e1 = new Event(1, "name", "description", 10);
		Event e2 = new Event(1, "name", "description", 10);
		assertEquals(e1, e2);
	}


	@Test
	public void testNotEquals() throws Exception {
		Event e1 = new Event(1, "name", "description", 10);
		Event e2 = new Event(1, "different name", "different description", 15);
		assertFalse(e1.equals(e2));
	}
}
