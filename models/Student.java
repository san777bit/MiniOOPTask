package models;

import interfaces.Enrollable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Student extends Person — demonstrates INHERITANCE.
 * Student implements Enrollable — demonstrates INTERFACE IMPLEMENTATION.
 * Student holds a list of enrolled Courses — demonstrates ASSOCIATION (many-to-many).
 */
public class Student extends Person implements Enrollable {
    private String major;
    private int year;
    private final List<Course> enrolledCourses; // association with Course

    public Student(String id, String name, String email, String major, int year) {
        super(id, name, email);
        this.major = major;
        this.year = year;
        this.enrolledCourses = new ArrayList<>();
    }

    // --- Enrollable interface ---

    @Override
    public boolean enroll(Course course) {
        if (enrolledCourses.contains(course)) {
            System.out.println("  [!] " + getName() + " is already enrolled in " + course.getTitle());
            return false;
        }
        enrolledCourses.add(course);
        course.addStudent(this);
        System.out.println("  [+] " + getName() + " enrolled in " + course.getTitle());
        return true;
    }

    @Override
    public boolean drop(Course course) {
        if (!enrolledCourses.contains(course)) {
            System.out.println("  [!] " + getName() + " is not enrolled in " + course.getTitle());
            return false;
        }
        enrolledCourses.remove(course);
        course.removeStudent(this);
        System.out.println("  [-] " + getName() + " dropped " + course.getTitle());
        return true;
    }

    @Override
    public int getEnrollmentCount() {
        return enrolledCourses.size();
    }

    // --- Printable interface (from Person) ---

    @Override
    public void printInfo() {
        System.out.println("  Student: " + getName()
                + " | ID: " + getId()
                + " | Email: " + getEmail()
                + " | Major: " + major
                + " | Year: " + year
                + " | Courses enrolled: " + enrolledCourses.size());
    }

    // --- Getters / Setters ---

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Course> getEnrolledCourses() {
        return Collections.unmodifiableList(enrolledCourses);
    }
}
