/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: https://github.com/CaoAssignments/cse12-sp23-pa7-Heaps-and-Priority-Queue-starter
  Comments from MyMinHeap for formatting use and redundant parameters

   
  This file is for CSE 12 PA7 in Spring 2023,
  and contains definitions of MyMinHeap for use in MyPriorityQueue definitions.
*/

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class implements MyMinHeap to create MyPriorityQueue.
 */
public class MyMinHeap <E extends Comparable<E>> implements MinHeapInterface<E> {
    protected ArrayList<E> data;

    /**
     * Constructor to create new MyMinHeap when no parameters are passed.
     */
    public MyMinHeap() {
        data = new ArrayList<>();
    }
    /**
     * Constructor to create new MyMinHeap.
     *
     * @param collection elements to be used in the creation of the heap.
     * collection can't be null.
     */
    public MyMinHeap(Collection<? extends E> collection) {
        if (collection == null) { throw new NullPointerException(); }
        
        data = new ArrayList<>(collection);
        for (int i = data.size() - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    /**
     * Function to swaps the elements.
     *
     * @param from the element one in the swap.
     * @param two the element two in the swap.
     */
    protected void swap(int from, int to) {
        E temp = data.get(from);
        data.set(from, data.get(to));
        data.set(to, temp);
    }
    /**
     * Function to acquire the parent of an element.
     *
     * @param index the location of the element.
     * @return the parent element of the element at index.
     */
    protected static int getParentIdx(int index) {
        return (index - 1)/2;
    }
    /**
     * Function to acquire the left child index of an element.
     *
     * @param index the location of the element.
     * @return the left child element index of the element at index.
     */
    protected static int getLeftChildIdx(int index) {
        return index*2+1;
    }
    /**
     * Function to acquire the right child index of an element.
     *
     * @param index the location of the element.
     * @return the right child element index of the element at index.
     */
    protected static int getRightChildIdx(int index) {
        return index*2+2;
    }
    /**
     * Function to acquire index of the smaller of the two children of an element.
     *
     * @param index the location of the element.
     * @return the smaller child element index of the element at index.
     */
    protected int getMinChildIdx(int index) {
        int left = getLeftChildIdx(index);
        int right = getRightChildIdx(index);

        if (left >= data.size()) { return -1; }
        else if (right >= data.size()) { return left; }
        else if (data.get(left).compareTo(data.get(right)) <= 0) { return left; }
        else { return right; }
    }
    /**
     * Function to move elements up until no heap properties are violated.
     * Heap prioperty = current element's parents are not greater than itself.
     *
     * @param index the location of the element.
     */
    protected void percolateUp(int index) {
        int parent = getParentIdx(index);
        while ((data.get(index).compareTo(data.get(parent)) < 0) && index > 0) {
            swap(index, parent);
            index = parent;
            parent = getParentIdx(index);
        }
    }
    /**
     * Function to move elements down until no heap properties are violated.
     * Heap prioperty = current element is not greater than its children.
     *
     * @param index the location of the element.
     */
    protected void percolateDown(int index) {
        int smaller = getMinChildIdx(index);

        if (smaller == -1) { return; }
        if (data.get(index).compareTo(data.get(smaller)) > 0) {
            swap(index, smaller);
            percolateDown(smaller);
        }
    }
    /**
     * Function to remove the element at index.
     * Percolate element replacing it up or down to avoid violating heap properties.
     *
     * @param index the location of the element.
     * @return the element that was deleted.
     */
    protected E deleteIndex(int index) {
        E temp = data.get(index);
        int last = data.size() - 1;

        if (index == last) {
            data.remove(last);
            return temp;
        }

        E swapTemp =  data.get(last);
        data.set(index, swapTemp);
        data.remove(last);
        E parentTemp = data.get(getParentIdx(index));

        if ((swapTemp.compareTo(parentTemp) >= 0) || index == 0) { percolateDown(index); }
        else { percolateUp(index); }
        return temp;
    }

    /**
     * Function to insert a new element.
     * Percolate element up until not violating heap properties.
     *
     * @param element the element to be inserted, can't be null.
     */
    @Override
    public void insert(E element) {
        if (element == null) { throw new NullPointerException(); }
        
        data.add(element);
        int index = size() - 1;
        percolateUp(index);
    }
    /**
     * Function to return the smallest element.
     * Smallest element should be the root element of the heap.
     * 
     * @return smallest element in heap, returns null in case heap is empty.
     */
    @Override
    public E getMin() {
        if (data.size() == 0) { return null; }
        return data.get(0);
    }
    /**
     * Function to remove the smallest element.
     * Smallest element should be the root element of the heap.
     * 
     * @return removed element in heap, returns null in case heap is empty.
     */
    @Override
    public E remove() {
        if (data.size() == 0) { return null; }
        return deleteIndex(0);
    }
    /**
     * Function to return size of the heap.
     * 
     * @return the number of elements in the heap.
     */
    @Override
    public int size() {
        return data.size();
    }
    /**
     * Function that clears everything in the heap.
     */
    @Override
    public void clear() {
        data = new ArrayList<>();
    }
}
