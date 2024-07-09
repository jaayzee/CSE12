/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: MyArrayList.java file within PA2 file
   
  This file is for CSE 12 PA2 in Spring 2023,
  and contains definitions of ArrayList for some standard functionalities.
*/

// Determines functionality of ArrayList functions
public class MyArrayList<E> implements MyList<E> {
    
    // Array data and size of valid elements
    Object[] data;
        //data will never be null
        //null can be used in data
    int size;

    //Constant for default capacity
    protected static final int DEFAULT_CAPACITY = 5;
    
    //Default capacity setting of 5 and size of 0
    public MyArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Determines capacity setting when passed into the function
     * Can't be less than 0
     * Size is set to 0
     * @param initialCapacity establishes capacity of array
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) { throw new IllegalArgumentException(); }
        this.data = new Object[initialCapacity];
        this.size = 0;

    }

    /**
     * Determines contents and capacity setting when passed into the function
     * If array is null, set to default capacity of 5
     * Establishes size, if null, size is 0
     * @param arr stated values of array upon creation
     */
    public MyArrayList(E[] arr) {
        if (arr == null) {
            this.data = new Object[DEFAULT_CAPACITY];
            this.size = 0;
        }
        else {
            this.data = arr;
            this.size = arr.length;
        }
    }

    /**
     * Increases the capacity depending on previous capacity
     * If no capacity, defaults to 5
     * Otherwise +3
     * If still smaller than requiredCapacity value, set to requiredCapacity
     * @param requiredCapacity sizing to edit MyArrayList to
     */
    @SuppressWarnings("unchecked")
    public void expandCapacity(int requiredCapacity) {
        if (requiredCapacity < this.data.length) { throw new IllegalArgumentException(); }
            int temp;
            if (this.data.length == 0) {
                temp = DEFAULT_CAPACITY;
            }
            else {
                temp = this.data.length + 3;
            }
            if (temp < requiredCapacity) {
                temp = requiredCapacity;
            }
            Object[] tempArr = new Object[temp];
            for(int i = 0; i<this.data.length; i++) {
                tempArr[i] = this.data[i];
            }
            this.data = (E[]) tempArr;
    }

    /**
     * Obtain the capacity, or usable space
     * @return useable space/capacity
     */
    public int getCapacity() {
        return this.data.length;
    }
    
    /**
     * Inserts the element at a specific index/position
     * Index can't be less than 0 or greater than the capacity
     * Increases size by 1 each time called
     * @param index position value in for insertion
     * @param element value to be inserted
     */
    public void insert(int index, E element) {
        if (index < 0 || index > this.data.length ) { throw new IndexOutOfBoundsException(); }
        if (this.data.length <= this.size) {
            expandCapacity(this.size+1);
        }
        for (int i = this.size-1; i >= index; i--) { 
            if(data[i] != null) { data[i+1] = data[i]; }
        }
        data[index] = element;
        this.size++;
    }

    /**
     * Adds a value/element to the end of the list
     * Calls expandCapacity if the capacity is less than or equal to size
     * Size increased by 1 per call
     * @param element element to be added
     */
    public void append(E element) {
        if (this.data.length <= this.size) { expandCapacity(this.size+1); }
        data[this.size] = element;
        this.size++;
    }

    /**
     * Adds a value/element to the front of the list
     * Calls expandCapacity if the capacity is less than or equal to size
     * Size increased by 1 per call
     * @param element element to be added
     */
    public void prepend(E element) {
        if (this.data.length <= this.size) { expandCapacity(this.size+1); }
        for (int i = this.size - 1; i >= 0; i--) {
            data[i+1] = data[i];
        }
        data[0] = element;
        this.size++;
    }

    /**
     * Search for an element at a specific position/index
     * @param index position to search for element, can't be less than 0 or greater than size
     * @return element at the index/position
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= this.size ) { throw new IndexOutOfBoundsException(); }
        return (E) data[index];
    }

    /**
     * Sets an element at an index to another passed element
     * @param index position to search for element, can't be less than 0 or greater than size
     * @param element element to be added
     */
    @SuppressWarnings("unchecked")
    public E set(int index, E element) {
        if (index < 0 || index > this.size ) { throw new IndexOutOfBoundsException(); }
        E val = (E) data[index];
        data[index] = element;
        return val;
    }

    /**
     * Removes an element from array
     * Size decreased by 1 per call
     * @param index position to find element to remove
     * @return value removed
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= this.size ) { throw new IndexOutOfBoundsException(); }
        E val = (E) data[index];
        for (int i = index; i < this.size - 1; i++) { data[i] = data[i+1]; }
        data[this.size-1] = null;
        this.size--;
        return val;
    }

    /**
     * Gets the occupied space by valid elements
     * @return the amount of valid elements within the array
     */
    public int size() {
        return this.size;
    }
}