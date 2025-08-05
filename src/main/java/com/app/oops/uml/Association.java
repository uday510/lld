package com.app.oops.uml;

class Teacher {

    private final String name;

    public Teacher(String name) {
        this.name = name;
    }

    public void teach() {
        System.out.println("Teaching...");
    }

    public String getName() {
        return name;
    }

}

class Student {

    private final String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}


public class Association {

    /**
     *
     * Represents a relationship between two or more classes, in this case,
     * each object in one class is associated with one or more objects of another class
     *
     */

    public static void main(String[] args) {
        Teacher teacher = new Teacher("teacher");
        Student student1 = new Student("student1");
        Student student2 = new Student("student2");

        teacher.teach();
    }

}
