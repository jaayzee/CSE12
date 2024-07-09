/*
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16357820
  Sources Used: PA2 Write-up 
                Testing for InvalidArguements: https://stackoverflow.com/questions/27807898/test-legal-and-illegal-argument-of-a-method-in-junit
  This file is for CSE 12 PA2 in Spring 2023,
  and contains a tester for debugging and testing purposes.
*/

import static org.junit.Assert.*;

import org.junit.*;

public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */
    static final int INVALID_CAPACITY = -12;

    Integer[] arrInts = { 1, 2, 3 };
    Integer[] arrNonEmptyInts = {1, null, null}; // NOTE: LIST OF SIZE ONE
    Integer[] arrValidNull = {5, null, 6, null, null};

    private MyArrayList listNonEmpty, listNull, listWithInt, listInvalid, listValidNull;
    
    @Before
    public void setUp() throws Exception {
        listNonEmpty = new MyArrayList<>(arrNonEmptyInts);
        listNonEmpty.size = 1;
        listWithInt = new MyArrayList<Integer>(arrInts);
        listValidNull = new MyArrayList<>(arrValidNull);
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test (expected=IllegalArgumentException.class)
    public void testConstructorInvalidArg(){
        listInvalid = new MyArrayList(INVALID_CAPACITY); 
        assertEquals("Check size for the constructor with invalid size input",
            0, listInvalid.size);
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        listNull = new MyArrayList(null);
        assertArrayEquals("Check for 5 null elements", 
            new Integer[]{null, null, null, null, null}, listNull.data);
        assertEquals("Check size for the constructor with null size input",
            0, listNull.size);
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
        assertArrayEquals("Check array contents", 
        new Integer[]{5, null, 6, null, null}, listValidNull.data);
        assertEquals("Check size for the constructor with null size input",
            5, listValidNull.size);
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listWithInt.append(5);
        assertArrayEquals("Check for successful append at capacity", 
            new Integer[]{1, 2, 3, 5, null, null}, listWithInt.data);
        assertEquals("Check list size after the append", 4, listWithInt.size);
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        listNonEmpty.append(null);
        assertArrayEquals("Check for successful null append", 
            new Integer[]{1, null, null}, listNonEmpty.data);
        assertEquals("Check list size after the append", 2, listNonEmpty.size);
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        listWithInt.prepend(5);
        assertArrayEquals("Check for successful prepend at capacity", 
            new Integer[]{5, 1, 2, 3, null, null}, listWithInt.data);
        assertEquals("Check list size after the prepend", 4, listWithInt.size);
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listNonEmpty.prepend(null);
        assertArrayEquals("Check for successful null prepend", 
            new Integer[]{null, 1, null}, listNonEmpty.data);
        assertEquals("Check list size after the prepend", 2, listNonEmpty.size);
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test (expected=IndexOutOfBoundsException.class)
    public void testInsertOutOfBounds(){
        listNonEmpty.insert(10,5);
        assertEquals("The size should 1", 1, listNonEmpty.size);
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        listNonEmpty.insert(2,5);
        assertArrayEquals("check data", 
            new Integer[]{1, null, 5}, listNonEmpty.data);
        assertEquals("The size should 2", 2, listNonEmpty.size);

        listNonEmpty.insert(1,12);
        assertArrayEquals("check data", 
            new Integer[]{1, 12, 5}, listNonEmpty.data);
        assertEquals("The size should 3", 3, listNonEmpty.size);

        listNonEmpty.insert(3,7);
        assertArrayEquals("check data", 
            new Integer[]{1, 12, 5, 7, null, null}, listNonEmpty.data);
        assertEquals("The size should 4", 4, listNonEmpty.size);

        listNonEmpty.insert(5,100);
        assertArrayEquals("check data", 
            new Integer[]{1, 12, 5, 7, null, 100}, listNonEmpty.data);
        assertEquals("The size should 5", 5, listNonEmpty.size);
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetOutOfBound(){
        listWithInt.get(12);
        assertArrayEquals("check data", 
            new Integer[]{1, 2, 3}, listWithInt.data);
        assertEquals("The size should 3", 3, listWithInt.size);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test (expected=IndexOutOfBoundsException.class)
    public void testSetOutOfBound(){
        listWithInt.set(10, 200);
        assertEquals("The size should 3", 3, listWithInt.size);
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        assertEquals("check for 1", 1, listWithInt.remove(0));
        assertArrayEquals("check data", 
            new Integer[]{2, 3, null}, listWithInt.data);
        assertEquals("The size should 2", 2, listWithInt.size);
        assertEquals("check for 3", 3, listWithInt.remove(1));
        assertArrayEquals("check data", 
            new Integer[]{2, null, null}, listWithInt.data);
        assertEquals("The size should 1", 1, listWithInt.size);
        assertEquals("check for 2", 2, listWithInt.remove(0));
        assertArrayEquals("check data", 
            new Integer[]{null, null, null}, listWithInt.data);
        assertEquals("The size should 0", 0, listWithInt.size);
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test (expected=IndexOutOfBoundsException.class)
    public void testRemoveOutOfBound(){
        listWithInt.remove(12);
        assertArrayEquals("check data", 
            new Integer[]{1, 2, 3}, listWithInt.data);
        assertEquals("The size should 3", 3, listWithInt.size);
        
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test (expected=IllegalArgumentException.class)
    public void testExpandCapacitySmaller(){
        listWithInt.expandCapacity(2);
        assertArrayEquals("check data", 
            new Integer[]{1, 2, 3}, listWithInt.data);
        assertEquals("The size should 3", 3, listWithInt.size);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        listWithInt.expandCapacity(10);
        assertArrayEquals("check data", 
            new Integer[]{1, 2, 3, null, null, null,
                null, null, null, null}, listWithInt.data);
        assertEquals("The size should 3", 3, listWithInt.size);
    }
    

}
