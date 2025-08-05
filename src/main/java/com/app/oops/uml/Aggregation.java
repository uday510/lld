package com.app.oops.uml;

import java.util.Arrays;
import java.util.List;

class Professor {
    private String name;

    public Professor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Department {
    private String name;
    private List<Professor> professors;

    public Department(String name, List<Professor> professors) {
        this.name = name;
        this.professors = professors;
    }

    public String getName() {
        return name;
    }

    public List<Professor> getProfessors() {
        return professors;
    }
}


public class Aggregation {

    /**
     *
     * Aggregation is a weak "has-a" relationship where one class contains
     * objects of another class. However, the contained objects can exist
     * independently of the container object.
     *
     */

    public static void main(String[] args) {
        Professor professor1 = new Professor("professor1");
        Professor professor2 = new Professor("professor2");

        List<Professor> professors = Arrays.asList(professor1, professor2);
        Department department = new Department("department1", professors);


        // Aggregation relationship: department has professor, but professors exists without department
        System.out.println(department.getProfessors());

    }

}
