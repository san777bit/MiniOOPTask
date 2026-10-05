package models;

import interfaces.Printable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Department demonstrates:
 *   - AGGREGATION: Department ◇ Course — courses can outlive the department.
 *   - ASSOCIATION: Department ↔ Instructor (one-to-many).
 */
public class Department implements Printable {
    private String code;
    private String name;
    private final List<Course> offeredCourses;       // AGGREGATION — courses exist independently
    private final List<Instructor> instructors;      // association

    public Department(String code, String name) {
        this.code = code;
        this.name = name;
        this.offeredCourses = new ArrayList<>();
        this.instructors = new ArrayList<>();
    }

    // --- Aggregation: Course management ---

    public void addCourse(Course course) {
        if (!offeredCourses.contains(course)) {
            offeredCourses.add(course);
            System.out.println("  [+] Course " + course.getTitle() + " added to " + name + " department");
        }
    }

    public void removeCourse(Course course) {
        if (offeredCourses.remove(course)) {
            System.out.println("  [-] Course " + course.getTitle() + " removed from " + name + " department");
        }
    }

    // --- Association: Instructor management ---

    public void addInstructor(Instructor instructor) {
        if (!instructors.contains(instructor)) {
            instructors.add(instructor);
            instructor.setDepartment(this);
            System.out.println("  [+] Instructor " + instructor.getName() + " added to " + name + " department");
        }
    }

    public void removeInstructor(Instructor instructor) {
        if (instructors.remove(instructor)) {
            instructor.setDepartment(null);
        }
    }

    // --- Printable ---

    @Override
    public void printInfo() {
        System.out.println("  Department: " + code + " — " + name
                + " | Instructors: " + instructors.size()
                + " | Courses offered: " + offeredCourses.size());
    }

    // --- Getters ---

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<Course> getOfferedCourses() {
        return Collections.unmodifiableList(offeredCourses);
    }

    public List<Instructor> getInstructors() {
        return Collections.unmodifiableList(instructors);
    }
}
