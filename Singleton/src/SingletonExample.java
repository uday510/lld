public class SingletonExample {
    public static void main(String[] args) {
        // Get the Singleton instance
        Singleton singleton1 = Singleton.getInstance();

        // Call a method on the Singleton instance
        singleton1.showMessage();

        // Try to get another instance
        Singleton singleton2 = Singleton.getInstance();

        // Check if both instances are the same
        if (singleton1 == singleton2) {
            System.out.println("Both instances are the same. Singleton pattern is working.");
        } else {
            System.out.println("Singleton pattern failed!");
        }
    }
}
