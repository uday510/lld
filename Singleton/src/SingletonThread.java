public class SingletonThread extends Thread {
    @Override
    public void run() {
        // Get the thread-safe Singleton instance
        ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();

        // Call a method on the Singleton instance
        singleton.showMessage();
    }

    public static void main(String[] args) {
        // Create multiple threads to access the Singleton
        Thread thread1 = new SingletonThread();
        Thread thread2 = new SingletonThread();
        Thread thread3 = new SingletonThread();

        // Start the threads
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
