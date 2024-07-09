/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: https://github.com/CaoAssignments/cse12-sp23-pa8-BinarySearchTree-Sorting-starter
  Comments from PublicTester for formatting use and redundant parameters

   
  This file is for CSE 12 PA8 in Spring 2023,
  and contains custom test cases for MyBST.
*/

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This class contains custom test cases for MyBST
 */
public class CustomTester {
    /**
     * Setup for single node BST testing
     */
    MyBST<Integer, Integer> tree;

    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(1, 1, null);

        this.tree = new MyBST<>();
        this.tree.root = root;
        tree.size = 1;
    }

    /** Test successor when only one node exists */
    @Test
    public void testSuccessor() {
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        assertSame(null, treeRoot.successor());
    }

    /** Test insert when one node exists */
    @Test
    public void testInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        tree.insert(2, 2);
        assertEquals(2, root.getRight().getKey().intValue());
        assertSame(root, root.getRight().getParent());
        assertEquals("size of tree", 2, tree.size);
    }

    /** Test insert when null is passed as key parameter */
    @Test (expected = NullPointerException.class)
    public void testNullInsert() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        tree.insert(null, 2);
    }

    /** Test search when key doesn't exist */
    @Test
    public void testSearch() {
        assertEquals(1, tree.search(1).intValue());
        assertNull(tree.search(10));
    }

    /** Test remove when only one node exists */
    @Test
    public void testRemove() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        assertEquals(1, tree.remove(1).intValue());
        assertEquals(null, tree.remove(6));
        assertEquals("size of tree", 0, tree.size);
    }

    /** Test inorder when only one node exists */
    @Test
    public void testInorder() {
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes =
                new ArrayList<>();
        expectedRes.add(root);
        assertEquals(expectedRes, tree.inorder());
    }
}
