package cs2321;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JosephusTest {
    DoublyLinkedList<String> target;
    String[] persons;
    Josephus game;

	@Before
	public void setUp() throws Exception {
	    persons = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30".split("\\s+");
	    target = new DoublyLinkedList<>();
        game = new Josephus();
	}

	@Test
	public void testOrder1() {
		int k = 5;
		String[] expected = "5 10 15 20 25 30 6 12 18 24 1 8 16 23 2 11 21 29 13 26 7 22 9 28 19 17 27 4 14 3".split("\\s+");
		DoublyLinkedList<String> outcome = game.order(persons, k);

		for(String person : expected) {
		    assertEquals(person, outcome.removeFirst());
        }
	}

	@Test
	public void testOrder2() {
        int k = 1;
        String[] expected = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30".split("\\s+");
        DoublyLinkedList<String> outcome = game.order(persons, k);

        for(String person : expected) {
            assertEquals(person, outcome.removeFirst());
        }
	}

	@Test
	public void testOrder3() {
        int k = 7;
        String[] expected = "7 14 21 28 5 13 22 30 9 18 27 8 19 1 12 25 10 24 11 29 17 6 3 2 4 16 26 15 20 23".split("\\s+");
        DoublyLinkedList<String> outcome = game.order(persons, k);

        for(String person : expected) {
            assertEquals(person, outcome.removeFirst());
        }
	}

	@Test
	public void testOrder4() {
        int k = 11;
        String[] expected = "11 22 3 15 27 9 23 6 20 5 21 8 26 14 2 25 17 12 7 4 10 16 24 1 29 30 19 13 18 28".split("\\s+");
        DoublyLinkedList<String> outcome = game.order(persons, k);

        for(String person : expected) {
            assertEquals(person, outcome.removeFirst());
        }
	}

}
