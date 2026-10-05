import models.*;
import services.EnrollmentService;

/**
 * University Management System — Main entry point.
 *
 * Demonstrates all required OOP relationships:
 *   1. INHERITANCE:             Person → Student, Person → Instructor
 *   2. INTERFACE IMPLEMENTATION: Printable, Enrollable
 *   3. ASSOCIATION:             Instructor ↔ Course (one-to-many),
 *                               Student ↔ Course (many-to-many),
 *                               Instructor ↔ Department
 *   4. AGGREGATION:             Department ◇ Course
 *   5. COMPOSITION:             Course ◆ Schedule
 *   6. DEPENDENCY:              EnrollmentService uses Student & Course
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   UNIVERSITY MANAGEMENT SYSTEM");
        System.out.println("==============================================");

        // ---- 1. Create Departments ----
        System.out.println("\n[1] Creating departments...");
        Department csDept = new Department("CS", "Computer Science");
        Department mathDept = new Department("MATH", "Mathematics");
        csDept.printInfo();
        mathDept.printInfo();

        // ---- 2. Create Instructors ----
        System.out.println("\n[2] Creating instructors...");
        Instructor profSmith = new Instructor("I001", "John Smith", "smith@uni.edu", "Professor");
        Instructor profJones = new Instructor("I002", "Alice Jones", "jones@uni.edu", "Associate Professor");

        // Association: assign instructors to departments
        csDept.addInstructor(profSmith);
        mathDept.addInstructor(profJones);

        profSmith.printInfo();
        profJones.printInfo();

        // ---- 3. Create Courses (with Schedule — composition) ----
        System.out.println("\n[3] Creating courses (each with its own schedule)...");
        Course oop = new Course("CS201", "Object-Oriented Programming", 30,
                "Monday", "09:00", "10:30", "A101");
        Course dataStructures = new Course("CS301", "Data Structures", 25,
                "Wednesday", "11:00", "12:30", "B202");
        Course linearAlgebra = new Course("MATH101", "Linear Algebra", 35,
                "Friday", "14:00", "15:30", "C303");

        // Aggregation: add courses to departments
        csDept.addCourse(oop);
        csDept.addCourse(dataStructures);
        mathDept.addCourse(linearAlgebra);

        // Association: assign instructors to courses
        profSmith.assignCourse(oop);
        profSmith.assignCourse(dataStructures);
        profJones.assignCourse(linearAlgebra);

        oop.printInfo();
        dataStructures.printInfo();
        linearAlgebra.printInfo();

        // ---- 4. Create Students ----
        System.out.println("\n[4] Creating students...");
        Student alice = new Student("S001", "Alice Brown", "alice@uni.edu", "Computer Science", 2);
        Student bob = new Student("S002", "Bob Davis", "bob@uni.edu", "Mathematics", 1);
        Student carol = new Student("S003", "Carol Wilson", "carol@uni.edu", "Computer Science", 3);

        alice.printInfo();
        bob.printInfo();
        carol.printInfo();

        // ---- 5. Enrollment Service — dependency ----
        System.out.println("\n[5] Enrolling students (via EnrollmentService)...");
        EnrollmentService enrollmentService = new EnrollmentService();

        enrollmentService.processEnrollment(alice, oop);
        enrollmentService.processEnrollment(alice, linearAlgebra);
        enrollmentService.processEnrollment(bob, linearAlgebra);
        enrollmentService.processEnrollment(bob, dataStructures);
        enrollmentService.processEnrollment(carol, oop);
        enrollmentService.processEnrollment(carol, dataStructures);

        // Attempt duplicate enrollment
        enrollmentService.processEnrollment(alice, oop);

        // ---- 6. Print current state ----
        System.out.println("\n[6] Current state after enrollments:");
        System.out.println("\n  --- Departments ---");
        csDept.printInfo();
        mathDept.printInfo();

        System.out.println("\n  --- Courses ---");
        oop.printInfo();
        dataStructures.printInfo();
        linearAlgebra.printInfo();

        System.out.println("\n  --- Students ---");
        alice.printInfo();
        bob.printInfo();
        carol.printInfo();

        enrollmentService.printAllEnrollments();

        // ---- 7. Assign grades ----
        System.out.println("\n[7] Assigning grades...");
        enrollmentService.assignGrade(alice, oop, "A");
        enrollmentService.assignGrade(bob, linearAlgebra, "B+");

        // ---- 8. Drop a course ----
        System.out.println("\n[8] Dropping a course...");
        enrollmentService.processDrop(carol, dataStructures);

        // ---- 9. Final state ----
        System.out.println("\n[9] Final state:");
        enrollmentService.printAllEnrollments();

        System.out.println("\n  --- Students (updated) ---");
        alice.printInfo();
        bob.printInfo();
        carol.printInfo();

        System.out.println("\n==============================================");
        System.out.println("   PROGRAM COMPLETED SUCCESSFULLY");
        System.out.println("==============================================");
    }
}
