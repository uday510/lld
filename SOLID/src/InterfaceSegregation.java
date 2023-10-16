public class InterfaceSegregation {
    //TODO: The ISP suggests that clients should not be forced to depend on interfaces they do not use.

    interface Worker {
        void work();
        void eat();
    }
    static class Engineer implements Worker {
        public void work() {
            System.out.println("Engineer is working.");
        }
        public void eat() {
            System.out.println("Engineer is eating.");
        }
    }
    static class Waiter implements Worker {
        public void work() {
            System.out.println("Waiter is working.");
        }
        public void eat() {
            System.out.println("Waiter is eating.");
        }
    }
}
