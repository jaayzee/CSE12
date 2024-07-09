/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: MyLinkedListCustomTester.java file within PA4 file
   
  This file is for CSE 12 PA4 in Spring 2023,
  and contains tests for the Doubly LinkedList Iterator.
*/

import static org.junit.Assert.*;
import org.junit.*;
import java.util.NoSuchElementException;

public class MyListIteratorCustomTester {
    private MyLinkedList listLen1, listLen2, listLen3;
    private MyLinkedList.MyListIterator listLen1Iter, listLen2Iter, listLen3Iter;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen1 = new MyLinkedList();
        listLen1.add("Christine");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();

        listLen3 = new MyLinkedList();
        listLen3Iter = listLen3.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test (expected = NoSuchElementException.class)
    public void testNextEnd() {
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.canRemoveOrSet = true;
        listLen1Iter.forward = true;
        listLen1Iter.next();
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test (expected = NoSuchElementException.class)
    public void testPreviousStart() {
        listLen1Iter.left = listLen1.head;
        listLen1Iter.right = listLen1.head.getNext();
        listLen1Iter.idx = 0;
        listLen1Iter.canRemoveOrSet = false;
        listLen1Iter.forward = true;
        listLen1Iter.previous();
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test (expected = NullPointerException.class)
    public void testAddInvalid() {
        listLen1Iter.add(null);
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test (expected = IllegalStateException.class)
    public void testCantSet() {
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.canRemoveOrSet = false;
        listLen1Iter.forward = true;
        listLen1Iter.set("I AIN'T GON HAPPEN");
    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test (expected = NullPointerException.class)
    public void testSetInvalid() {
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.canRemoveOrSet = true;
        listLen1Iter.forward = true;
        listLen1Iter.set(null);
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test (expected = IllegalStateException.class)
    public void testCantRemove() {
        listLen1Iter.left = listLen1.head.getNext();
        listLen1Iter.right = listLen1.head.getNext().getNext();
        listLen1Iter.idx = 1;
        listLen1Iter.canRemoveOrSet = false;
        listLen1Iter.forward = true;
        listLen1Iter.remove();
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen2Iter.left = listLen2.head.getNext().getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext().getNext();
        listLen2Iter.idx = 2;
        listLen2Iter.canRemoveOrSet = true;
        listLen2Iter.forward = true;
        assertFalse("Tests when list has sentinel node next",
                listLen2Iter.hasNext());
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.canRemoveOrSet = false;
        listLen3Iter.forward = true;
        assertFalse("Tests when list is empty",
                listLen2Iter.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        listLen2Iter.left = listLen2.head.getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.idx = 1;
        listLen2Iter.canRemoveOrSet = true;
        listLen2Iter.forward = true;
        assertTrue("Tests when list has sentinel node prev",
                listLen2Iter.hasPrevious());
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.canRemoveOrSet = false;
        listLen3Iter.forward = true;
        assertFalse("Tests when list is empty",
                listLen3Iter.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        listLen2Iter.left = listLen2.head;
        listLen2Iter.right = listLen2.head.getNext();
        listLen2Iter.idx = 0;
        listLen2Iter.canRemoveOrSet = true;
        listLen2Iter.forward = true;
        assertEquals("Tests when idx is at beginning", -1,
                listLen2Iter.previousIndex());
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.canRemoveOrSet = false;
        listLen3Iter.forward = true;
        assertEquals("Tests when list is empty", -1,
                listLen2Iter.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen2Iter.left = listLen2.head.getNext().getNext();
        listLen2Iter.right = listLen2.head.getNext().getNext().getNext();
        listLen2Iter.idx = 2;
        listLen2Iter.canRemoveOrSet = true;
        listLen2Iter.forward = true;
        assertEquals("Tests when idx is at the end", 2,
                listLen2Iter.nextIndex());
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.canRemoveOrSet = false;
        listLen3Iter.forward = true;
        assertEquals("Tests when list is empty", 0,
                listLen3Iter.nextIndex());
    }
}
