public class DesignBird {
    static class Bird {
        //TODO : attributes
        String name;
        String type;
        int wings;
        double weight;
        String gender;

        public Bird(String name) {
            this.name = name;
        }
        String color;

        public Bird(String name, String type, int wings, double weight, String gender, String color) {
            this.name = name;
            this.type = type;
            this.wings = wings;
            this.weight = weight;
            this.gender = gender;
            this.color = color;
        }

        //TODO: behaviours
        public void fly() {
            System.out.println(this.name + " is flying.");

        }
        public void makeSound() {
            System.out.println(this.name + " is making sound.");
        }
        public void dance() {
            System.out.println(this.name + " is dancing.");
        }
        public void eat() {
            System.out.println(this.name + " is eating.");
        }
    }

    public static void main(String[] args) {
//        Bird peacock = new Bird("Peacock");
//
//        peacock.fly();
//        peacock.eat();
        Bird owl = new Owl("owl");
        owl.makeSound();

    }
    static class Owl extends Bird {

        public Owl(String name) {
            super(name);
        }

        @Override
        public void makeSound() {

            System.out.println("im gudurguing");
        }
    }
}
