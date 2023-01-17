package deque;

import org.junit.Test;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        ArrayDeque<Character> lld1 = new ArrayDeque<>();

		assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
		lld1.addLast('a');

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

		lld1.addLast('b');
		assertEquals(2, lld1.size());

		lld1.addFirst('c');
		assertEquals(3, lld1.size());

        lld1.addLast('d');
        lld1.addLast('e');
        lld1.addFirst('f');
        lld1.addLast('g');
        lld1.addLast('h');
        lld1.addLast('i');
        lld1.addFirst('j');
		System.out.println("Printing out deque: ");
		lld1.printDeque();

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
		// should be empty
		assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse("lld1 should contain 1 item", lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue("lld1 should be empty after removal", lld1.isEmpty());

        lld1.addFirst(10);
        lld1.addLast(20);
        int last = lld1.removeLast();
        assertEquals(20,last);
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeque with different parameterized types*/
    public void multipleParamTest() {
        ArrayDeque<String>  lld1 = new ArrayDeque<>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /*  check resize. */
    public void resizeTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            lld1.addLast(i);
        }

        for (int i = 0; i < 20; i++) {
            lld1.removeFirst();
        }
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 50; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 99; i > 50; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void getTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            lld1.addLast(i);
        }
        int item5 = lld1.get(5);
        assertEquals("Should have the same value", 5, item5);
        assertEquals("Should have the same value", null, lld1.get(100));
        lld1.removeFirst();
        lld1.removeFirst();
        int item0 = lld1.get(0);
        assertEquals("Should have the same value", 2, item0);
    }

    @Test
    public void equalTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            lld1.addLast(i);
        }
        ArrayDeque<Integer> lld2 = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            lld2.addLast(i);
        }
        assertTrue("Should be true", lld1.equals(lld2));

        ArrayDeque<String> lld3 = new ArrayDeque<>();
        lld3.addFirst("a");
        lld3.addLast("b");
        lld3.addFirst("c");
        ArrayDeque<String> lld4 = new ArrayDeque<>();
        lld4.addFirst("a");
        lld4.addLast("b");
        lld4.addFirst("c");
        assertTrue("Should be true", lld3.equals(lld4));

        assertFalse("Should be true", lld3.equals(1));
    }
}
