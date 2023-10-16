public class OpenClosed {


    //TODO: The OCP suggests that software entities should be open for extension but closed for modification.

    abstract static class Shape {
        public abstract double calculateArea();
    }

    static class Circle extends Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }
        @Override
        public double calculateArea() {
            return (Math.PI * radius * radius);
        }
    }
    static class Rectangle extends Shape {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        @Override
        public double calculateArea() {
            return this.width * this.height;
        }
    }
    // TODO : You can add new shapes (e.g., Triangle) without modifying the Shape class.

}
