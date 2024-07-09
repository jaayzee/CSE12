
/*
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16357820
  Sources Used: PA3 Write-up 
  This file is for CSE 12 PA2 in Spring 2023,
  and contains a tester for debugging and testing purposes.
*/

/**
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of MyLinkedList. You will only receive points
 * if your test passes when run on a good implementation and fails for the
 * corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

public class MyLinkedListCustomTester {

	// Optional: add test variables here
	private MyLinkedList<Integer> emptyIntegerList;
    private MyLinkedList<String> threeStringList;
	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		// Optional: add setup here
		emptyIntegerList = new MyLinkedList<Integer>();
        threeStringList = new MyLinkedList<>();
	}

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		// TODO: add your test here
		assertEquals("return value true", 
			true, this.emptyIntegerList.add(100));
		assertEquals("Size of the LinkedList should be updated", 
            1, this.emptyIntegerList.size);
		this.threeStringList.add("Live");
		assertEquals("Size of the LinkedList should be updated", 
            1, this.threeStringList.size);
		this.threeStringList.add("Love");
		assertEquals("Size of the LinkedList should be updated", 
            2, this.threeStringList.size);
		this.threeStringList.add("Laugh");
		assertEquals("Size of the LinkedList should be updated", 
            3, this.threeStringList.size);
		assertEquals("New node should be accessible from head", 
            Integer.valueOf(100), this.emptyIntegerList.head.next.data);
        assertEquals("New node should be accessible from tail", 
            Integer.valueOf(100), this.emptyIntegerList.tail.prev.data);
        assertEquals("New node should be accessible from tail.prev.prev.next", 
            Integer.valueOf(100), this.emptyIntegerList.tail.prev.prev.next.data);
        assertEquals("Size of the LinkedList should be updated", 
            1, this.emptyIntegerList.size);
        assertSame("Make sure the reference from head and tail are the same", 
            this.emptyIntegerList.head.next, this.emptyIntegerList.tail.prev);
        assertSame("Added node should have correct previous pointer",
            this.emptyIntegerList.head.next.prev, this.emptyIntegerList.head);
        assertSame("Added node should have the correct next pointer",
            this.emptyIntegerList.head.next.next, this.emptyIntegerList.tail);
		assertSame("Iterated next 4 times should be tail",
            this.threeStringList.head.next.next.next.next, this.threeStringList.tail);
		assertSame("Iterated prev 4 times should be head",
            this.threeStringList.tail.prev.prev.prev.prev, this.threeStringList.head);
		assertEquals("Live node should be accessible from head.next", 
            String.valueOf("Live"), this.threeStringList.head.next.data);
		assertEquals("Love node should be accessible from head.next.next", 
            String.valueOf("Love"), this.threeStringList.head.next.next.data);
		assertEquals("Laugh node should be accessible from head.next.next.next", 
            String.valueOf("Laugh"), this.threeStringList.head.next.next.next.data);
		assertEquals("Laugh node should be accessible from tail.prev", 
            String.valueOf("Laugh"), this.threeStringList.tail.prev.data);
		assertEquals("Love node should be accessible from tail.prev.prev", 
            String.valueOf("Love"), this.threeStringList.tail.prev.prev.data);
		assertEquals("Live node should be accessible from tail.prev.prev.prev", 
            String.valueOf("Live"), this.threeStringList.tail.prev.prev.prev.data);
		assertEquals("Love node should be accessible from spamming", 
            String.valueOf("Love"), this.threeStringList.head.next.next.prev.next.prev.next.data);
		assertEquals("Reference should be the same from spamming", 
		this.threeStringList.tail.prev.prev.next.prev.next.prev.data, this.threeStringList.head.next.next.prev.next.prev.next.data);
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		this.emptyIntegerList.add(0, 100);
		this.threeStringList.add(0, "Laugh");
		assertEquals("Size of the LinkedList should be updated", 
            1, this.threeStringList.size);
		this.threeStringList.add(0, "Love");
		assertEquals("Size of the LinkedList should be updated", 
            2, this.threeStringList.size);
		this.threeStringList.add(0, "Live");
		assertEquals("Size of the LinkedList should be updated", 
            3, this.threeStringList.size);
        assertEquals("New node should be accessible from head", 
            Integer.valueOf(100), this.emptyIntegerList.head.next.data);
        assertEquals("New node should be accessible from tail", 
            Integer.valueOf(100), this.emptyIntegerList.tail.prev.data);
        assertEquals("New node should be accessible from tail.prev.prev.next", 
            Integer.valueOf(100), this.emptyIntegerList.tail.prev.prev.next.data);
        assertEquals("Size of the LinkedList should be updated", 
            1, this.emptyIntegerList.size);
        assertSame("Make sure the reference from head and tail are the same", 
            this.emptyIntegerList.head.next, this.emptyIntegerList.tail.prev);
        assertSame("Added node should have correct previous pointer",
            this.emptyIntegerList.head.next.prev, this.emptyIntegerList.head);
        assertSame("Added node should have the correct next pointer",
            this.emptyIntegerList.head.next.next, this.emptyIntegerList.tail);
		assertSame("Iterated next 4 times should be tail",
            this.threeStringList.head.next.next.next.next, this.threeStringList.tail);
		assertSame("Iterated prev 4 times should be head",
            this.threeStringList.tail.prev.prev.prev.prev, this.threeStringList.head);
		assertEquals("Live node should be accessible from head.next", 
            String.valueOf("Live"), this.threeStringList.head.next.data);
		assertEquals("Love node should be accessible from head.next.next", 
            String.valueOf("Love"), this.threeStringList.head.next.next.data);
		assertEquals("Laugh node should be accessible from head.next.next.next", 
            String.valueOf("Laugh"), this.threeStringList.head.next.next.next.data);
		assertEquals("Laugh node should be accessible from tail.prev", 
            String.valueOf("Laugh"), this.threeStringList.tail.prev.data);
		assertEquals("Love node should be accessible from tail.prev.prev", 
            String.valueOf("Love"), this.threeStringList.tail.prev.prev.data);
		assertEquals("Live node should be accessible from tail.prev.prev.prev", 
            String.valueOf("Live"), this.threeStringList.tail.prev.prev.prev.data);
		assertEquals("Love node should be accessible from spamming", 
            String.valueOf("Love"), this.threeStringList.head.next.next.prev.next.prev.next.data);
		assertEquals("Reference should be the same from spamming", 
		this.threeStringList.tail.prev.prev.next.prev.next.prev.data, this.threeStringList.head.next.next.prev.next.prev.next.data);
		// TODO: add your test here
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		this.emptyIntegerList.add(this.emptyIntegerList.size/2, 100);
		this.threeStringList.add(this.threeStringList.size/2, "Laugh");
		assertEquals("Size of the LinkedList should be updated", 
            1, this.threeStringList.size);
		this.threeStringList.add(this.threeStringList.size/2, "Live");
		assertEquals("Size of the LinkedList should be updated", 
            2, this.threeStringList.size);
		this.threeStringList.add(this.threeStringList.size/2, "Love");
		assertEquals("Size of the LinkedList should be updated", 
            3, this.threeStringList.size);
		assertEquals("New node should be accessible from head", 
            Integer.valueOf(100), this.emptyIntegerList.head.next.data);
        assertEquals("New node should be accessible from tail", 
            Integer.valueOf(100), this.emptyIntegerList.tail.prev.data);
        assertEquals("New node should be accessible from tail.prev.prev.next", 
            Integer.valueOf(100), this.emptyIntegerList.tail.prev.prev.next.data);
        assertEquals("Size of the LinkedList should be updated", 
            1, this.emptyIntegerList.size);
        assertSame("Make sure the reference from head and tail are the same", 
            this.emptyIntegerList.head.next, this.emptyIntegerList.tail.prev);
        assertSame("Added node should have correct previous pointer",
            this.emptyIntegerList.head.next.prev, this.emptyIntegerList.head);
        assertSame("Added node should have the correct next pointer",
            this.emptyIntegerList.head.next.next, this.emptyIntegerList.tail);
		assertSame("Iterated next 4 times should be tail",
            this.threeStringList.head.next.next.next.next, this.threeStringList.tail);
		assertSame("Iterated prev 4 times should be head",
            this.threeStringList.tail.prev.prev.prev.prev, this.threeStringList.head);
		assertEquals("Live node should be accessible from head.next", 
            String.valueOf("Live"), this.threeStringList.head.next.data);
		assertEquals("Love node should be accessible from head.next.next", 
            String.valueOf("Love"), this.threeStringList.head.next.next.data);
		assertEquals("Laugh node should be accessible from head.next.next.next", 
            String.valueOf("Laugh"), this.threeStringList.head.next.next.next.data);
		assertEquals("Laugh node should be accessible from tail.prev", 
            String.valueOf("Laugh"), this.threeStringList.tail.prev.data);
		assertEquals("Love node should be accessible from tail.prev.prev", 
            String.valueOf("Love"), this.threeStringList.tail.prev.prev.data);
		assertEquals("Live node should be accessible from tail.prev.prev.prev", 
            String.valueOf("Live"), this.threeStringList.tail.prev.prev.prev.data);
		assertEquals("Love node should be accessible from spamming", 
            String.valueOf("Love"), this.threeStringList.head.next.next.prev.next.prev.next.data);
		assertEquals("Reference should be the same from spamming", 
		this.threeStringList.tail.prev.prev.next.prev.next.prev.data, this.threeStringList.head.next.next.prev.next.prev.next.data);
		// // TODO: add your test here
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test (expected=IndexOutOfBoundsException.class)
	public void testCustomRemoveFromEmpty() {
		// TODO: add your test here
		this.emptyIntegerList.remove(0);
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		// TODO: add your test here
		this.emptyIntegerList.add(this.emptyIntegerList.size/2, 100);
		this.threeStringList.add("Live");
		assertEquals("Size of the LinkedList should be updated", 
            1, this.threeStringList.size);
		this.threeStringList.add("Live");
		assertEquals("Size of the LinkedList should be updated", 
            2, this.threeStringList.size);
		this.threeStringList.add("temp1");
		assertEquals("Size of the LinkedList should be updated", 
            3, this.threeStringList.size);
		this.threeStringList.add("temp2");
		assertEquals("Size of the LinkedList should be updated", 
			4, this.threeStringList.size);
		this.threeStringList.add("temp3");
		assertEquals("Size of the LinkedList should be updated", 
			5, this.threeStringList.size);
		this.threeStringList.add( "Laugh");
		assertEquals("Size of the LinkedList should be updated", 
            6, this.threeStringList.size);
		assertEquals("Should return temp2",
			"temp2", this.threeStringList.remove(this.threeStringList.size/2));
		assertEquals("Size of the LinkedList should be updated", 
            5, this.threeStringList.size);
		assertEquals("Should return temp1",
			"temp1", this.threeStringList.remove(this.threeStringList.size/2));
		assertEquals("Size of the LinkedList should be updated", 
            4, this.threeStringList.size);
		assertEquals("Should return temp3",
			"temp3", this.threeStringList.remove(this.threeStringList.size/2));
		assertEquals("Size of the LinkedList should be updated", 
            3, this.threeStringList.size);
		assertEquals("New node should be accessible from head", 
            Integer.valueOf(100), this.emptyIntegerList.head.next.data);
        assertEquals("New node should be accessible from tail", 
            Integer.valueOf(100), this.emptyIntegerList.tail.prev.data);
        assertEquals("New node should be accessible from tail.prev.prev.next", 
            Integer.valueOf(100), this.emptyIntegerList.tail.prev.prev.next.data);
        assertEquals("Size of the LinkedList should be updated", 
            1, this.emptyIntegerList.size);
        assertSame("Make sure the reference from head and tail are the same", 
            this.emptyIntegerList.head.next, this.emptyIntegerList.tail.prev);
        assertSame("Added node should have correct previous pointer",
            this.emptyIntegerList.head.next.prev, this.emptyIntegerList.head);
        assertSame("Added node should have the correct next pointer",
            this.emptyIntegerList.head.next.next, this.emptyIntegerList.tail);
		assertSame("Iterated next 4 times should be tail",
            this.threeStringList.head.next.next.next.next, this.threeStringList.tail);
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test (expected=IndexOutOfBoundsException.class)
	public void testCustomSetIdxOutOfBounds() {
		// TODO: add your test here
		this.emptyIntegerList.set(12, 100);
	}
}
