package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Instructor extends Person — demonstrates INHERITANCE.
 * Instructor is associated with a Department — demonstrates ASSOCIATION.
 * Instructor teaches a list of Courses — demonstrates ASSOCIATION (one-to-many).
 */
public class Instructor extends Person {
    private String title;                       // e.g. "Professor", "Associate Professor"
    private Department department;              // association (Instructor ↔ Department)
    private final List<Course> taughtCourses;   // association (one instructor – many courses)

    public Instructor(String id, String name, String email, String title) {
        super(id, name, email);
        this.title = title;
        this.taughtCourses = new ArrayList<>();
    }

    // --- Association helpers ---

    public void assignCourse(Course course) {
        if (!taughtCourses.contains(course)) {
            taughtCourses.add(course);
            course.setInstructor(this);
            System.out.println("  [+] " + getName() + " assigned to teach " + course.getTitle());
        }
    }

    public void removeCourse(Course course) {
        if (taughtCourses.remove(course)) {
            course.setInstructor(null);
        }
    }

    // --- Printable ---

    @Override
    public void printInfo() {
        String deptName = (department != null) ? department.getName() : "Unassigned";
        System.out.println("  Instructor: " + title + " " + getName()
                + " | ID: " + getId()
                + " | Email: " + getEmail()
                + " | Department: " + deptName
                + " | Courses taught: " + taughtCourses.size());
    }

    // --- Getters / Setters ---

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Course> getTaughtCourses() {
        return Collections.unmodifiableList(taughtCourses);
    }
}
