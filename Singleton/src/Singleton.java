/*
TODO:  A singleton is a class that is supposed to have only one instance per JVM.
       The Same instance of the singleton class is reused by multiple threads.
       Most often, we use singletons to represent system configurations and window managers,
       since those instances should be common to all threads and objects within a JVM.

       The Singleton design pattern ensures that a class has only one instance and
       provides a global point of access to that instance.
 */
public class Singleton {
    // Private static instance of the Singleton class
    private static Singleton INSTANCE;

    // Private constructor to prevent instantiation from other classes
    private Singleton() {}

    // Public method to provide a single point of access to the instance
    public static Singleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton();
        }
        return INSTANCE;
    }

    // Other methods and data members of the Singleton class
    public void showMessage() {
        System.out.println("Hello, I am a Singleton!");
    }
}


/*
    1. Make the constructor private.
    2. Create a static function which will actually help in creating the object.
    3. Have a Static member, which will hold on over object.
 */
