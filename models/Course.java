package models;

import interfaces.Printable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Course demonstrates:
 *   - COMPOSITION: Course owns its Schedule (created internally, destroyed with Course).
 *   - ASSOCIATION:  Course ↔ Student (many-to-many), Course → Instructor (many-to-one).
 *   - AGGREGATION:  Department ◇ Course — a Course can exist without a Department.
 */
public class Course implements Printable {
    private final String courseCode;
    private String title;
    private int maxCapacity;
    private Instructor instructor;                // association
    private final List<Student> enrolledStudents; // association (many-to-many)
    private final Schedule schedule;              // COMPOSITION — created inside Course

    public Course(String courseCode, String title, int maxCapacity,
                  String day, String startTime, String endTime, String room) {
        this.courseCode = courseCode;
        this.title = title;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
        // Composition: Schedule is created by Course, lives and dies with it
        this.schedule = new Schedule(day, startTime, endTime, room);
    }

    // --- Student management (association) ---

    boolean addStudent(Student student) {
        if (enrolledStudents.size() >= maxCapacity) {
            System.out.println("  [!] " + title + " is full (capacity " + maxCapacity + ")");
            return false;
        }
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
        return true;
    }

    boolean removeStudent(Student student) {
        return enrolledStudents.remove(student);
    }

    // --- Printable ---

    @Override
    public void printInfo() {
        String instrName = (instructor != null) ? instructor.getName() : "TBA";
        System.out.println("  Course: " + courseCode + " — " + title
                + " | Instructor: " + instrName
                + " | Enrolled: " + enrolledStudents.size() + "/" + maxCapacity
                + " | Schedule: " + schedule);
    }

    // --- Getters / Setters ---

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Student> getEnrolledStudents() {
        return Collections.unmodifiableList(enrolledStudents);
    }

    public Schedule getSchedule() {
        return schedule;
    }
}
