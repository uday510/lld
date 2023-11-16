public class Student implements Prototype {
    int age;
    String name;
    String batch;
    double avgBatchPsp;
    double studentPsp;

    Student() {};
    Student(Student student) {
        this.name = student.name;
        this.age = student.age;
        this.batch = student.batch;
        this.avgBatchPsp = student.avgBatchPsp;
        this.studentPsp = student.studentPsp;
    }

    @Override
    public Student clone() {
        return new Student(this);
    }
}
