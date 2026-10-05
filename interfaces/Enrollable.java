package interfaces;

import models.Course;

/**
 * Interface for entities that can be enrolled in courses.
 * Demonstrates interface implementation.
 */
public interface Enrollable {
    boolean enroll(Course course);
    boolean drop(Course course);
    int getEnrollmentCount();
}
