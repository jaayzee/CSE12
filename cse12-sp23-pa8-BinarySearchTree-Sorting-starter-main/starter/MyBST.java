/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: https://github.com/CaoAssignments/cse12-sp23-pa8-BinarySearchTree-Sorting-starter
  Zybooks sections:
  - 7.4 BST search algorithm
  - 7.5 BST insert algorithm
  - 7.6 BST remove algorithm
  - 7.7 BST inorder traversal
   
  This file is for CSE 12 PA8 in Spring 2023,
  and contains definitions of MyBST methods.
*/

import java.util.ArrayList;

/**
 * This class implements MyBST.
 */
public class MyBST<K extends Comparable<K>, V> {
    /**
     * Basis for an empty BST.
     */
    MyBSTNode<K, V> root = null;
    int size = 0;

    /**
     * Accessor for the size of the BST.
     * 
     * @return number of nodes in the BST.
     */
    public int size() {
        return size;
    }

    /**
     * Function to insert new nodes into the BST in the proper location.
     * Throws a NullPointerException when key is null
     * 
     * @param key the key of which the node is sorted into the BST.
     * @param value the contents within the node.
     * @return null if properly inserted, and the value of the node
     * replaced if a node is replaced.
     */
    public V insert(K key, V value) {
        if (key == null) { throw new NullPointerException(); }
        if (this.root == null) {
            this.root = new MyBSTNode<K,V>(key, value, null);
            this.size++;
            return null;
        }
        else {
            MyBSTNode<K,V> curr = this.root;
            while (curr != null) {
            if (key.compareTo(curr.getKey()) > 0) {
                if (curr.getRight() == null) {
                    curr.setRight(new MyBSTNode<K,V>(key, value, curr));
                    this.size++;
                }
                else { curr = curr.getRight(); }
            }
            else if (key.compareTo(curr.getKey()) < 0) {
                if (curr.getLeft() == null) {
                    curr.setLeft(new MyBSTNode<K,V>(key, value, curr));
                    this.size++;
                }
                else { curr = curr.getLeft(); }
            }
            else {
                V temp = curr.getValue();
                curr.setValue(value);
                return temp;
            }
        }
        }
        
        return null;
    }

    /**
     * Function to search for a node in the BST.
     *
     * @param key the key of which the node is searched in the BST.
     * @return the value of the node found from the key, or null if unfound.
     */
    public V search(K key) {
        if (key == null) { return null; }
        MyBSTNode<K,V> curr = this.root;
        while (curr != null) {
            if (key.compareTo(curr.getKey()) == 0) { return curr.getValue(); }
            else if (key.compareTo(curr.getKey()) > 0) {
                curr = curr.getRight();
            }
            else {
                curr = curr.getLeft();
            }
        }
        return null;
    }

    /**
     * Function to remove nodes from the BST and properly sort the BST after.
     *
     * @param key the key of the node to be removed from the BST.
     * @return the node removed, or null if key is null, or key doesn't exist.
     */
    public V remove(K key) {
        if (key == null) { return null; }
        V temp = null;
        MyBSTNode<K,V> curr = this.root;
        while (curr != null) {
            if (key.compareTo(curr.getKey()) == 0) {
                temp = curr.getValue();
                if (curr.getLeft() == null && curr.getRight() == null) {
                    if (curr.getParent() == null) { this.root = null; }
                    else if (curr.getParent().getLeft() == curr) {
                        curr.getParent().setLeft(null);
                    }
                    else { curr.getParent().setRight(null); }
                    this.size--;
                }
                else if (curr.getRight() == null) {
                    if (curr.getParent() == null) { this.root = curr.getLeft(); }
                    else if (curr.getParent().getLeft() == curr) {
                        curr.getParent().setLeft(curr.getLeft());
                    }
                    else { curr.getParent().setRight(curr.getLeft()); }
                    this.size--;
                }
                else if (curr.getLeft() == null){
                    if (curr.getParent() == null) { this.root = curr.getRight(); }
                    else if (curr.getParent().getLeft() == curr) {
                        curr.getParent().setLeft(curr.getRight());
                    }
                    else { curr.getParent().setRight(curr.getRight()); }
                    this.size--;
                }
                else {
                    MyBSTNode<K,V> succ = curr.successor();
                    V succData = succ.getValue();
                    remove(succ.getKey());
                    curr.setValue(succData);
                    this.size--;
                }
                return temp;
            }
            else if (key.compareTo(curr.getKey()) > 0) {
                curr = curr.getRight();
            }
            else {
                curr = curr.getLeft();
            }
        }
        return temp;
    }

    /**
     * Function to create a sorted ArrayList from the nodes of the BST.
     *
     * @return ArrayList of BST nodes in ascending order.
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {
        ArrayList<MyBSTNode<K, V>> result = new ArrayList<>();
        if (this.root != null) {
            MyBSTNode<K,V> curr = root;
            while (curr.getLeft() != null) { curr = curr.getLeft(); }
            while (curr != null) {
                result.add(curr);
                curr = curr.successor();
            }
        }
        return result;
    }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode storing specified data
         *
         * @param key    the key the MyBSTNode will store
         * @param value  the data the MyBSTNode will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode
         *
         * @return the key stored in the MyBSTNode
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode
         *
         * @return the data stored in the MyBSTNode
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * Function to determine the successor of a particular node in the BST.
         *
         * @return the node that would succeed the node in question.
         */
        public MyBSTNode<K, V> successor() {
            MyBSTNode<K, V> curr = this;
            if (curr.getRight() != null) {
                curr = curr.getRight();
                while (curr.getLeft() != null) { curr = curr.getLeft(); }
                return curr;
            }
            else {
                MyBSTNode<K,V> par = curr.getParent();
                while(par != null && curr == par.getRight()) {
                    curr = par;
                    par = par.getParent();
                }
                return par;
            }
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
