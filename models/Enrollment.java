package models;

/**
 * Enrollment represents the link between a Student and a Course.
 * Stores additional data about each enrollment (grade, status).
 */
public class Enrollment {
    private final Student student;
    private final Course course;
    private String status;   // "ACTIVE", "COMPLETED", "DROPPED"
    private String grade;    // null until graded

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.status = "ACTIVE";
        this.grade = null;
    }

    // --- Getters / Setters ---

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        String gradeStr = (grade != null) ? grade : "N/A";
        return "Enrollment: " + student.getName() + " → " + course.getTitle()
                + " | Status: " + status + " | Grade: " + gradeStr;
    }
}
