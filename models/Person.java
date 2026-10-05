package models;

import interfaces.Printable;

/**
 * Base class for all people in the university system.
 * Demonstrates INHERITANCE (Student and Instructor extend Person).
 */
public abstract class Person implements Printable {
    private String id;
    private String name;
    private String email;

    public Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // --- Encapsulated getters/setters ---

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
