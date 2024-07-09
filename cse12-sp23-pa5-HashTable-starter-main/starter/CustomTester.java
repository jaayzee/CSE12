/*
 * Name: John Zhou
 * Email: jozhou@ucsd.edu
 * PID: A16357820
 * Sources Used: https://github.com/CaoAssignments/cse12-sp23-pa5-HashTable-starter
 * JDK 17 Doc
 * PublicTester.java within the same file
 *
 * This file is for CSE 12 PA5 in Spring 2023,
 * and is used to test Student.java, Course.java, Sanctuary.java 
 */
import org.junit.Test;
import java.util.HashSet;
import java.util.Objects;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
/**
 * The Custom tester for PA5, which covers cases stated in the github.
 */
public class CustomTester {
    /**
     * Test Student equals with invalid arguements
     */
    @Test
    public void testStudentEquals() {
        Student student1 = new Student("Test", "Student",
                "A12345678");
        assertEquals(false, student1.equals(12));
    }
    /**
     * Test Student compareTo against Student with a lexigraphically greater PID
     */
    @Test
    public void testStudentCompareTo() {
        Student student1 = new Student("Same", "Guy",
                "A00000000");
        Student student2 = new Student("Same", "Guy",
                "A12345678");
        assertEquals(-1, student1.compareTo(student2));
    }
    /**
     * Test Course drop with a non-existing student
     */
    @Test
    public void testCourseDrop() {
        Course course = new Course("CSE", "12", "Data Structure", 100);
        Student stu1 = new Student("Guy", "Guyer", "A000");
        Student stu2 = new Student("Girl", "Girler", "A111");
        Student stu3 = new Student("Unreal", "No", "A222");
        assertTrue(course.enroll(stu1));
        assertTrue(course.enroll(stu2));
        assertEquals(false, course.drop(stu3));
        assertEquals(2, course.getEnrolledCount());
        assertEquals(true, course.drop(stu2));
    }
    /**
     * Test Course enroll with an existing student into a max capacity course
     */
    @Test
    public void testCourseEnroll() {
        Course course = new Course("CSE", "12", "Data Structure", 2);
        Student stu1 = new Student("Guy", "Guyer", "A000");
        Student stu2 = new Student("Girl", "Girler", "A111");
        Student stu3 = new Student("Unreal", "No", "A222");
        assertTrue(course.enroll(stu1));
        assertTrue(course.enroll(stu2));
        assertEquals(false, course.enroll(stu3));
    }
    /**
     * Test Course getRoster with a course with a bunch of enrolled students
     */
    @Test
    public void testCourseGetRoster() {
        Course course = new Course("CSE", "12", "Data Structure", 120);
        for (int i = 0; i < 100; i++) {
            Student temp = new Student("same", "guy", String.valueOf(i));
            course.enroll(temp);
        }
        ArrayList<Student> temp1 = course.getRoster();
        assertEquals("90", temp1.get(90).getPID());
        
    }
    /**
     * Test Sanctuary constructor with invalid argument
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSanctConstructor() {
        Sanctuary sanct = new Sanctuary(-500, 50);
    }
    /**
     * Test Sanctuary rescue with partial num accepted
     */
    @Test
    public void testSanctRescuePartial() {
        Sanctuary sanct = new Sanctuary(50, 2);
        sanct.sanctuary.put("Rhino", 20);
        sanct.sanctuary.put("Alex", 10);
        assertEquals(5, sanct.rescue("Alex", 25));
    }
    /**
     * Test Sanctuary rescue with a new non-null species when sanctuary
     * is at max capacoty for species
     */
    @Test
    public void testSanctRescueMaxSpecies() {
        Sanctuary sanct = new Sanctuary(50, 2);
        sanct.sanctuary.put("Rhino", 20);
        sanct.sanctuary.put("Alex", 10);
        assertEquals(25, sanct.rescue("Ryan", 25));
        assertEquals(2, sanct.getTotalSpecies());
        assertEquals(30, sanct.getTotalAnimals());
    }
    /**
     * Test Sanctuary release some animals of species, not all
     */
    @Test
    public void testSanctReleasePartial() {
        Sanctuary sanct = new Sanctuary(50, 2);
        sanct.sanctuary.put("Rhino", 20);
        sanct.sanctuary.put("Alex", 10);
        Sanctuary sanct1 = new Sanctuary(50, 2);
        sanct1.sanctuary.put("Rhino", 10);
        sanct1.sanctuary.put("Alex", 10);
        sanct.release("Rhino", 10);
        assertEquals(sanct1.sanctuary, sanct.sanctuary);
    }
    /**
     * Test Sanctuary release more animals of species than available
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSanctReleaseTooMany() {
        Sanctuary sanct = new Sanctuary(50, 2);
        sanct.sanctuary.put("Rhino", 20);
        sanct.sanctuary.put("Alex", 10);
        sanct.release("Rhino", 50);
    }
}
