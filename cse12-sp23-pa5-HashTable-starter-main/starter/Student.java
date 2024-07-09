/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: https://www.geeksforgeeks.org/hashing-in-java/#
  https://github.com/CaoAssignments/cse12-sp23-pa5-HashTable-starter
   
  This file is for CSE 12 PA5 in Spring 2023,
  and contains definitions of Student for use in the Course 
  definitions.
*/

import java.util.Objects;

// Determines base functionalities for Student class
public class Student implements Comparable<Student> {
    private final String firstName;
    private final String lastName;
    private final String PID;

    /**
     * Initialize student information in addition to exceptions for null
     * @param firstName first name of Student
     * @param lastName last name of Student
     * @param PID unique code for Student
     */
    public Student(String firstName, String lastName, String PID) {
        if (firstName == null || lastName == null || PID== null) {
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }
    /** 
     * Accessor for the Student first name 
     * @return the firstName data of Student
     */
    public String getFirstName() {
        return this.firstName;
    }
    /** 
     * Accessor for the Student last name 
     * @return the lastName data of Student
     */
    public String getLastName() {
        return this.lastName;
    }
    /** 
     * Accessor for the Student PID 
     * @return the PID data of Student
     */
    public String getPID() {
        return this.PID;
    }
    /**
     * Function to compare Students
     * @param o Student object to be compared
     * @return boolean true if all instance variables are the same
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (!(o instanceof Student)) { return false; }
        else {
            Student temp = (Student) o;
            return (this.firstName == temp.getFirstName())&&(this.lastName == temp.getLastName())
            &&(this.PID == temp.getPID());
        }
    }
    /**
     * Function to generate hash value from Object.hash
     * @return hash value from Student firstName, lastName, PID
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, PID);
    }
    /**
     * Function to compare two Students
     * Throws Exception when o is null
     * @param o Student that is compared
     * @return negative if this Student comes before Student o, 0 if equal, 
     * and positive if this Student comes after Student o
     */
    public int compareTo (Student o) {
        if (o == null) { throw new NullPointerException(); }
        int tempLast = this.lastName.compareTo(o.getLastName());
        if (tempLast != 0) { return tempLast; }
        else {
            int tempFirst = this.firstName.compareTo(o.getFirstName());
            if (tempFirst != 0) { return tempFirst; }
            else { return this.PID.compareTo(o.getPID()); }
        }
    }
}
