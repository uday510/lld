public class Student implements Prototype {
    int age;
    String name;
    String batch;
    double avgBatchPsp;
    double studentPsp;
    @Override
    public Student clone() {
        Student copy = new Student();
        copy.name = this.name;
        copy.age = this.age;
        copy.batch = this.batch;
        copy.avgBatchPsp = this.avgBatchPsp;
        copy.studentPsp = this.studentPsp;
        return copy;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public double getAvgBatchPsp() {
        return avgBatchPsp;
    }

    public void setAvgBatchPsp(double avgBatchPsp) {
        this.avgBatchPsp = avgBatchPsp;
    }

    public double getStudentPsp() {
        return studentPsp;
    }

    public void setStudentPsp(double studentPsp) {
        this.studentPsp = studentPsp;
    }
}
