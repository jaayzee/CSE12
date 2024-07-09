/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: https://github.com/CaoAssignments/cse12-sp23-pa7-Heaps-and-Priority-Queue-starter
  Comments from MyPriorityQueue for formatting use and redundant parameters

   
  This file is for CSE 12 PA7 in Spring 2023,
  and contains definitions of MyPriorityQueue.
*/

import java.util.Collection;

/**
 * This class implements MyPriorityQueue using the MyMinHeap class
 */
public class MyPriorityQueue <E extends Comparable<E>>{
    protected MyMinHeap<E> heap;

    /**
     * Constructor to create new MyPriorityQueue when no parameters are passed.
     */
    public MyPriorityQueue() {
        heap = new MyMinHeap<>();
    }
    /**
     * Constructor to create new MyPriorityQueue.
     *
     * @param collection elements to be used in the creation of the heap.
     * collection can't be null.
     */
    public MyPriorityQueue(Collection<? extends E> collection) {
        if (collection == null) { throw new NullPointerException(); }
        
        heap = new MyMinHeap<>(collection);
    }

    /**
     * Function that adds the specified element to the heap of MyPriorityQueue.
     *
     * @param element the element to add to the heap.
     * element can't be null.
     */
    public void push(E element) {
        heap.insert(element);
    }
    /**
     * Function that looks at the highest priority element (smallest).
     *
     * @return the element with the highest priority, null if heap is empty.
     */
    public E peek() {
        return heap.getMin();
    }
    /**
     * Function that removes the element with the highest priority.
     * Returns the element removed, or null if heap is empty.
     *
     * @return the element removed, or null if heap is empty.
     */
    public E pop() {
        return heap.remove();
    }
    /**
     * Function that returns the number of elements in the priority queue.
     *
     * @return the number of elements in the priority queue
     */
    public int getLength() {
        return heap.size();
    }
    /**
     * Function that clears everything in the priority queue.
     */
    public void clear() {
        heap.clear();
    }
}
