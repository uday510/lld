public class ThreadSafeSingleton {
    // Private static instance of the Singleton class
    private static ThreadSafeSingleton INSTANCE;

    // Private constructor to prevent instantiation from other classes
    private ThreadSafeSingleton() {}

    // Synchronized method to provide a single point of access to the instance
    public static synchronized ThreadSafeSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ThreadSafeSingleton();
        }
        return INSTANCE;
    }

    // Other methods and data members of the Singleton class
    public void showMessage() {
        System.out.println("Hello, I am a thread-safe Singleton!");
    }
}
