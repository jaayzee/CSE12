/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: https://github.com/CaoAssignments/cse12-sp23-pa6-Stacks-and-Queues-starter
  Comments from MyQueue for formatting use and redundant parameters

   
  This file is for CSE 12 PA6 in Spring 2023,
  and contains definitions of MyDeque for use in MyQueue and
  MyStack definitions.
*/

/**
 * This class implements the DequeInterface to create MyDeque.
 */
public class MyDeque<E> implements DequeInterface<E>{
    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * Constructor to create new MyDeque that holds data.
     * @param initialCapacity The max amount of elements this data structure
     * can hold.
     */
    public MyDeque(int initialCapacity) {
        if (initialCapacity < 0) { throw new IllegalArgumentException(); }
        data = new Object[initialCapacity];
        size = 0;
        rear = 0;
        front = 0;
    }
    /**
     * Returns the number of elements in this queue.
     * @return the number of elements in the queue
     */
    public int size() {
        return this.size;
    }
    /**
     * Function to expand the capacity of MyDeque.
     * Will default to a capacity of 10 if empty MyDeque data
     * default case size = 10
     */
    public void expandCapacity() {
        int newCap = 0;
        if (data.length == 0) { newCap = 10; }
        else { newCap = data.length * 2; }
        Object[] temp = new Object[newCap];
        int iter = front;
        for (int i = 0; i < size; i++) {
            temp[i] = data[iter];
            iter = (iter + 1) % data.length;
        }
        data = temp;
        front = 0;
        if (size == 0) { rear = 0; }
        else { rear = size - 1; }
    }
    /**
     * Function to add an element to the front of MyDeque data.
     * Will throw Null exception if element is null
     * @param element element to be added to the front
     */
    public void addFirst(E element) {
        if (element == null) { throw new NullPointerException(); }
        if (size >= data.length) { expandCapacity(); }
        if (size > 0) {
            front = (data.length + front - 1) % data.length;
        }
        data[front] = element;
        size++;
    }
    /**
     * Function to add an element to the end of MyDeque data.
     * Will throw Null exception if element is null
     * @param element element to be added to the rear
     */
    public void addLast(E element) {
        if (element == null) { throw new NullPointerException(); }
        if (size >= data.length) { expandCapacity(); }
        if (size > 0) {
            rear = (rear + 1) % data.length;
        }
        data[rear] = element;
        size++;
    }
    /**
     * Function to remove an element from the front of MyDeque data.
     * Will return null if size is 0
     * @return element that was removed from the front
     */
    @SuppressWarnings("unchecked")
    public E removeFirst() {
        if (size == 0) { return null; }
        E element = (E) data[front];
        data[front] = null;
        size--;
        if (size > 0) {
            front = (front + 1) % data.length;
        }
        return element;
    }
    /**
     * Function to remove an element from the end of MyDeque data.
     * Will return null if size is 0
     * @return element that was removed from the rear
     */
    @SuppressWarnings("unchecked")
    public E removeLast(){
        if (size == 0) { return null; }
        E element = (E) data[rear];
        data[rear] = null;
        size--;
        if (size > 0) {
            rear = (data.length + rear - 1) % data.length;
        }
        return element;
    }
    /**
     * Function to peek at the front of MyDeque data.
     * Will return null if size is 0
     * @return element that is at the front
     */
    @SuppressWarnings("unchecked")
    public E peekFirst() {
        if (size == 0) { return null; }
        return (E) data[front];
    }
    /**
     * Function to peek at the end of MyDeque data.
     * Will return null if size is 0
     * @return element that is at the rear
     */
    @SuppressWarnings("unchecked")
    public E peekLast() {
        if (size == 0) { return null; }
        return (E) data[rear];
    }
}
