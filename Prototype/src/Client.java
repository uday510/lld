public class Client {

    public static void fillRegistry(StudentRegistry studentRegistry) {
        Student apr21BatchStudent = new Student();
        apr21BatchStudent.setBatch("April 21");
        apr21BatchStudent.setAvgBatchPsp(80.9);

        studentRegistry.register("apr21Batch", apr21BatchStudent);
    }
    public static void main(String[] args) {
        StudentRegistry studentRegistry = new StudentRegistry();
        fillRegistry(studentRegistry);

        Student s1 = studentRegistry.get("apr21Batch").clone();


    }
}
