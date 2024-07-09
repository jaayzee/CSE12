/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: https://github.com/CaoAssignments/cse12-sp23-pa6-Stacks-and-Queues-starter
  Comments from PublicTester for formatting use and redundant parameters

   
  This file is for CSE 12 PA6 in Spring 2023,
  and contains custom test cases for MyDeque, MyStack, and MyQueue.
*/
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * This class contains custom test cases for MyDeque, MyStack, and MyQueue
 */
public class CustomTester {
     /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                          int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }
    // ------------ Deque ---------------

    /** Test expandCapacity with several elements at the start of the array */
    @Test
    public void testExpandCapacity() {
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] orig = {};
        Integer[] finalOrdering = {null, null, null, null, null, null, null, null,
                null, null};
        initDeque(deque, orig, 0, 0, 0);

        deque.expandCapacity();

        assertEquals("Capacity should have 10", 10, deque.data.length);
        assertEquals("Size should not have changed", 0, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should be at size 0", 0, deque.rear);
        for (int i = 0; i < 10; i++) {
            assertEquals("Deque data should be 10 nulls",
                    finalOrdering[i], deque.data[i]);
        }
    }

    /**
     * Test addFirst when element is null
     */
    @Test (expected = NullPointerException.class)
    public void testAddFirstNull() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        Integer[] orig = {4, 5, 6};
        initDeque(deque, orig, 3, 0, 2);

        deque.addFirst(null);
    }

    /**
     * Test addFirst when forced to call expand capacity
     */
    @Test ()
    public void testAddFirstCap() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        Integer[] orig = {4, 5, 6};
        Integer[] finalOrdering = {4, 5, 6, null, null, 7};
        initDeque(deque, orig, 3, 0, 2);

        deque.addFirst(7);

        assertEquals("Capacity should double", 6, deque.data.length);
        assertEquals("Should increment size", 4, deque.size);
        assertEquals("Front should be 5" , 5, deque.front);
        assertEquals("Rear should be 2", 2, deque.rear);
        assertEquals("7 should have been inserted into index 5",
                7, deque.data[5]);
        assertEquals("Index 3 should be null", null,
                deque.data[3]);
        assertEquals("Index 4 should be null",
                null, deque.data[4]);
        assertEquals("Index 2 should be 6", 6,
                deque.data[2]);
        for (int i = 0; i < 6; i++) {
            assertEquals("Deque data should be the same",
                    finalOrdering[i], deque.data[i]);
        }
    }

        /**
     * Test addLast when element is null
     */
    @Test (expected = NullPointerException.class)
    public void testAddLastNull() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        Integer[] orig = {4, 5, 6};
        initDeque(deque, orig, 3, 0, 2);

        deque.addLast(null);
    }
    /**
     * Test addLast when forced to call expand capacity
     */
    @Test ()
    public void testAddLastCap() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        Integer[] orig = {};
        Integer[] finalOrdering = {7, null, null, null, null, null, null, null,
             null, null, null};
        initDeque(deque, orig, 0, 0, 0);

        deque.addLast(7);

        assertEquals("Capacity should be 10", 10, deque.data.length);
        assertEquals("Should increment size", 1, deque.size);
        assertEquals("Front should be 0" , 0, deque.front);
        assertEquals("Rear should be 0", 0, deque.rear);
        for (int i = 0; i < 10; i++) {
            assertEquals("Deque data should be the same",
                    finalOrdering[i], deque.data[i]);
        }
    }

    /**
     * Test removeFirst when first is at the end
     */
    @Test ()
    public void testRemoveFirst() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        Integer[] orig = {4, 5, 6, null, null, 7};
        Integer[] finalOrdering = {4, 5, 6, null, null, null};
        initDeque(deque, orig, 4, 5, 2);

        deque.removeFirst();

        assertEquals("Capacity should 6", 6, deque.data.length);
        assertEquals("Should decrement size", 3, deque.size);
        assertEquals("Front should be 0" , 0, deque.front);
        assertEquals("Rear should be 2", 2, deque.rear);
        for (int i = 0; i < 6; i++) {
            assertEquals("Deque data should be the same",
                    finalOrdering[i], deque.data[i]);
        }
    }

    /**
     * Test removeLast when last is at the start
     */
    @Test ()
    public void testRemoveLast() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        Integer[] orig = {0, null, null, null, null, 1};
        Integer[] finalOrdering = {null, null, null, null, null, 1};
        initDeque(deque, orig, 2, 5, 0);

        deque.removeLast();

        assertEquals("Capacity should 6", 6, deque.data.length);
        assertEquals("Should decrement size", 1, deque.size);
        assertEquals("Front should be 5" , 5, deque.front);
        assertEquals("Rear should be 5", 5, deque.rear);
        for (int i = 0; i < 6; i++) {
            assertEquals("Deque data should be the same",
                    finalOrdering[i], deque.data[i]);
        }
    }

    // ------------ Stack ---------------

    /** Test push and pop on full stack */
    @Test
    public void testStack() {
        MyStack<Integer> stack = new MyStack<>(2);
        Integer[] orig = {0,1};
        initDeque(stack.theStack, orig, 2, 0, 1);

        stack.push(2);
        Integer temp = stack.peek();

        if(temp == 0 || temp == 2 ) {
        assertEquals("Size should be incremented", 3, stack.theStack.size);
        }
        else {
            fail("An element on one of the ends should have been returned");
        }
        assertEquals("Capacity should be doubled", 4,
                stack.theStack.data.length);

        Integer temp1 = stack.pop();

        if(temp1 == 0 || temp1 == 2 ) {
        assertEquals("Size should be decremented", 2, stack.theStack.size);
        if (stack.theStack.peekFirst() != 1 &&
            stack.theStack.peekLast() != 1) {
                fail("Next element to remove should be 1");
                }
            }
        else {
            fail("An element on one of the ends should have been returned");
        }
        assertEquals("Capacity should be doubled", 4,
                stack.theStack.data.length);
    }

    // ------------ Queue ---------------

    /** Test enqueue and dequeue on full queue */
    @Test
    public void testQueue() {
        MyQueue<Integer> queue = new MyQueue<>(2);
        Integer[] orig = {0,1,2};
        initDeque(queue.theQueue, orig, 3, 0, 2);

        queue.enqueue(3);

        if(queue.theQueue.peekLast() == Integer.valueOf(3)) {
            assertEquals("Front should be 0", 0, queue.theQueue.front);
            assertEquals("Rear should be 3", 3, queue.theQueue.rear);
            assertEquals("Size should be incremented", 4, queue.theQueue.size);
            assertEquals("Capacity should be doubled", 6,
            queue.theQueue.data.length);
            
            assertEquals("0 should be dequeued", Integer.valueOf(0), queue.dequeue());
            assertEquals("Size should be decremented", 3, queue.theQueue.size);
            assertEquals("Capacity should be the same", 6,
                queue.theQueue.data.length);
        }
        else if (queue.theQueue.peekFirst() == Integer.valueOf(3)) {
            assertEquals("Front should be 5", 5, queue.theQueue.front);
            assertEquals("Rear should be 2", 2, queue.theQueue.rear);
            assertEquals("Size should be incremented", 4, queue.theQueue.size);
            assertEquals("Capacity should be doubled", 6,
            queue.theQueue.data.length);
            
            assertEquals("2 should be dequeued", Integer.valueOf(2), queue.dequeue());
            assertEquals("Size should be decremented", 3, queue.theQueue.size);
            assertEquals("Capacity should be the same", 6,
                queue.theQueue.data.length);
        }
        else {
            fail("incorrect enqueue, should be 2 at either end");
        }
    }
}
