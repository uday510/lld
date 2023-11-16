public class Client {

    public static void fillRegistry(StudentRegistry registry) {
        Student student = new Student();
        registry.register("April 21", student);
    }
    public static void main(String[] args) {
        StudentRegistry registry = new StudentRegistry();
        fillRegistry(registry);

        Student student = registry.get("April 21").clone();
    }
}
