/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: https://www.geeksforgeeks.org/collections-sort-java-examples/#
  https://github.com/CaoAssignments/cse12-sp23-pa5-HashTable-starter
   
  This file is for CSE 12 PA5 in Spring 2023,
  and contains definitions of Course for storing students registered for
  particular courses in the form of a HashSet.
*/

import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;

// Determines base functionalities for Course class
public class Course {
    HashSet<Student> enrolled;
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    /**
     * Initialize Course information in addition to exceptions for null
     * Also creates the enrolled HashSet
     * @param department Course department
     * @param number Code associated with Course
     * @param description information on the Course
     * @param capacity maximum number of students allowed in Course
     */
    public Course(String department, String number, String description, int capacity) {
        if (department == null || number == null || description == null || capacity <= 0) {
            throw new IllegalArgumentException(); 
        }
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
        enrolled = new HashSet<>();
    }
    /** 
     * Accessor for the Course department 
     * @return the department data of Course
     */
    public String getDepartment() {
        return this.department;
    }
    /** 
     * Accessor for the Course number 
     * @return the number data of Course
     */
    public String getNumber() {
        return this.number;
    }
    /** 
     * Accessor for the Course description 
     * @return the description data of Course
     */
    public String getDescription() {
        return this.description;
    }
    /** 
     * Accessor for the Course capacity 
     * @return the capacity data of Course
     */
    public int getCapacity() {
        return this.capacity;
    }
    /**
     * Function to enroll Students in Course
     * Throws exception when student is null
     * @param student Student object to be enrolled into Course
     * @return boolean true if student is successfully enrolled into the Course
     * otherwise false
     */
    public boolean enroll(Student student) {
        if (student == null) { throw new IllegalArgumentException(); }
        if (enrolled.size() >= this.capacity || enrolled.contains(student)) {
            return false;
        }
        else {
            enrolled.add(student);
            return true;
        }
    }
    /**
     * Function to drop Students from Course
     * Throws exception when student is null
     * @param student Student object to be dropped from Course
     * @return boolean true if student is successfully dropped form the Course
     * otherwise false
     */
    public boolean drop(Student student) {
        if (student == null) { throw new IllegalArgumentException(); }
        if (enrolled.contains(student)) {
            enrolled.remove(student);
            return true;
        }
        else { return false; }
    }
    /**
     * Function to drop all Students from Course
     */
    public void cancel() {
        enrolled.clear();
    }
    /**
     * Function to determine if Course is full
     * @return boolean true if Course is full otherwise false
     */
    public boolean isFull() {
        return enrolled.size() == this.capacity;
    }
    /** 
     * Accessor for the number of Students enrolled in Course 
     * @return the size of the enrolled HashSet
     */
    public int getEnrolledCount() {
        return enrolled.size();
    }
    /** 
     * Accessor for the number of available seats in Course 
     * @return the difference between Course capacity and enrolled size
     */
    public int getAvailableSeats() {
        return this.capacity - enrolled.size();
    }
    /**
     * Function to create a shalllow copy of all Students enrolled in Course
     * @return new HashSet with different address, but same references
     */
    public HashSet<Student> getStudents() {
        HashSet<Student> shallow = new HashSet<Student>(enrolled);
        return shallow;
    }
    /**
     * Function to create a copy of HashSet Students enrolled in the 
     * Course as an ArrayList.
     * @return a sorted ArrayList copy of the HashSet of Students enrolled
     * in the Course.
     */
    public ArrayList<Student> getRoster() {
        ArrayList<Student> list = new ArrayList<>();
        for (Student i : this.enrolled) { list.add(i); }
        Collections.sort(list);
        return list;
    }
    /**
     * Function to print out the data initialized in Course
     * @return String consisting of Course department, number,
     * capacity, and description
     */
    @Override
    public String toString() {
        return String.format("%s %s [%d] %s", department, number, capacity, description);
    }
}
