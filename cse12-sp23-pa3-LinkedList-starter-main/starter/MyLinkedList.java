/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: MyLinkedList.java file within PA3 file
   
  This file is for CSE 12 PA3 in Spring 2023,
  and contains definitions of MyLinkedList for some standard 
  Doubly Linked List functionalities.
*/

import java.util.AbstractList;

// Determines base functionalities for a Doubly Linked List
public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //  Implementation of the MyLinkedList Class
    public MyLinkedList() {
        /* Add your implementation here */
        /**
         * Establishes dummy nodes for the Lists head and tail
         * Pre-determines List size initially 0
         * Connect head.next to tail and tail.prev to head
         */
        Node headDummy = new Node(null);
        Node tailDummy = new Node(null);
        this.size = 0;
        this.head = headDummy;
        this.tail = tailDummy;
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
        // TODO
    }

    /**
     * Accessor to get the Nodes size 
     * @return the size of the linked list (# of nodes)
     */
    @Override
    public int size() {
        // need to implement the size method
        return this.size; // TODO
    }

    /**
     * Accessor to return the element at index within the list
     * @param index location to get within the list
     * @return element in location of index
     */
    @Override
    public E get(int index) {
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException(); }

        return (E) this.getNth(index).getElement();  // TODO
    }

    /**
     * Function to add an element to a certain index of the list
     * @param index location to add within the list
     * @param data element to be added to the list
     */
    @Override
    public void add(int index, E data) {
        /* Add your implementation here */
        if (data == null) { throw new NullPointerException(); }
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException(); }

        Node toAdd = new Node(data);
        // Head case
        if (index == 0) {
            toAdd.setNext(this.head.getNext());
            toAdd.setPrev(this.head);
            this.head.getNext().setPrev(toAdd);
            this.head.setNext(toAdd);
        }
        // Tail case
        else if (index == size) {
            toAdd.setPrev(this.tail.getPrev());
            toAdd.setNext(this.tail);
            this.tail.getPrev().setNext(toAdd);
            this.tail.setPrev(toAdd);
        }
        else {
            Node temp = this.getNth(index);
            toAdd.setNext(temp);
            toAdd.setPrev(temp.getPrev());
            temp.getPrev().setNext(toAdd);
            temp.setPrev(toAdd);
        }
        this.size++;
        // TODO
    }

    /**
     * Function to add an element to the end of a list
     * @param data element to be added to the list
     * @return boolean true when added to list
     */
    @Override
    public boolean add(E data) {
        if (data == null) { throw new NullPointerException(); }

        Node toAdd = new Node(data);
        toAdd.setPrev(this.tail.getPrev());
        toAdd.setNext(this.tail);
        this.tail.getPrev().setNext(toAdd);
        this.tail.setPrev(toAdd);
        this.size++;
        return true; // TODO
    }

    /**
     * Function to set an index's data to another element within the list 
     * @param index location to set within the list
     * @param data element to be added to the list
     * @return element that was overwritten within the list
     */
    @Override
    public E set(int index, E data) {
        if (data == null) { throw new NullPointerException(); }
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException(); }

        Node temp = this.getNth(index);
        E val = temp.getElement();
        temp.setElement(data);
        return (E) val; // TODO
    }

    /**
     * Function to remove an element within the list 
     * @param index location to remove within the list
     * @return element that was removed within the list
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) { throw new IndexOutOfBoundsException(); }

        Node temp = this.getNth(index);
        // Head Case
        if (this.head == temp) {
            this.head = temp.getNext();
        }
        // Tail Case
        else if (this.tail == temp) {
            this.tail = temp.getPrev();
        }
        else {
            temp.getPrev().setNext(temp.getNext());
            temp.getNext().setPrev(temp.getPrev());
        }
        this.size--;
        return (E) temp.getElement(); // TODO
    }

    /**
     * Function to clear all elements within the list 
     */
    @Override
    public void clear() {
        /* Add your implementation here */
        this.size = 0;
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);
    }

    /**
     * Checks if the list is empty 
     * @return true if list is empty, or false if not
     */
    @Override
    public boolean isEmpty() {
        // if (size() == 0) { return true; }
        // else { return false; }
        return size() == 0;
          // TODO
    }

    /**
     * Accessor to get the Nodes at a specific location within the list 
     * @param index location to get within the list
     * @return Node at location of index in the list
     */
    protected Node getNth(int index) {
        if (index < 0 || index > size) { throw new IndexOutOfBoundsException(); }

        Node temp = this.head.getNext();
        for (int i = 0; i < index; i++) { temp = temp.next; }
        return (Node) temp;  // TODO
    }
}
