package services;

import models.Course;
import models.Enrollment;
import models.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * EnrollmentService demonstrates DEPENDENCY.
 * It depends on Student and Course to perform its work,
 * but does not own or store them long-term (they are passed as parameters).
 */
public class EnrollmentService {
    private final List<Enrollment> enrollmentRecords;

    public EnrollmentService() {
        this.enrollmentRecords = new ArrayList<>();
    }

    /**
     * Enroll a student in a course — dependency on Student and Course.
     */
    public Enrollment processEnrollment(Student student, Course course) {
        System.out.println("\n  >> Processing enrollment...");
        if (student.enroll(course)) {
            Enrollment record = new Enrollment(student, course);
            enrollmentRecords.add(record);
            return record;
        }
        return null;
    }

    /**
     * Drop a student from a course — dependency on Student and Course.
     */
    public boolean processDrop(Student student, Course course) {
        System.out.println("\n  >> Processing drop...");
        if (student.drop(course)) {
            enrollmentRecords.removeIf(e ->
                    e.getStudent().equals(student) && e.getCourse().equals(course));
            return true;
        }
        return false;
    }

    /**
     * Assign a grade to an enrollment — dependency on Student and Course.
     */
    public void assignGrade(Student student, Course course, String grade) {
        for (Enrollment e : enrollmentRecords) {
            if (e.getStudent().equals(student) && e.getCourse().equals(course)) {
                e.setGrade(grade);
                e.setStatus("COMPLETED");
                System.out.println("  [G] Grade " + grade + " assigned to "
                        + student.getName() + " for " + course.getTitle());
                return;
            }
        }
        System.out.println("  [!] Enrollment record not found.");
    }

    public void printAllEnrollments() {
        System.out.println("\n  --- All Enrollment Records ---");
        if (enrollmentRecords.isEmpty()) {
            System.out.println("  (none)");
            return;
        }
        for (Enrollment e : enrollmentRecords) {
            System.out.println("  " + e);
        }
    }
}
