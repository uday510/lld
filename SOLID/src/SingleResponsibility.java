public class SingleResponsibility {

    // TODO: Every class in Java should have a single job to do. To be precise, there should only be one reason to change a class

    static class Rectangle {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        public double getWidth() {
            return this.width;
        }
        public double getHeight() {
            return this.height;
        }
        public double calculateArea() {
            return this.width * this.height;
        }
    }
    static class RectanglePrinter {
        public static void print(Rectangle rectangle) {
            System.out.println("Width: " + rectangle.getWidth());
            System.out.println("Height: " + rectangle.getHeight());
            System.out.println("Area: " + rectangle.calculateArea());
        }
    }


    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(10, 20);

        RectanglePrinter.print(rectangle);
    }
}

/*
  record Rectangle(double width, double height) {
        public double calculateArea() {
                return this.width * this.height;
            }
        }
    static class RectanglePrinter {
        public static void print(Rectangle rectangle) {
            System.out.println("Width: " + rectangle.width());
            System.out.println("Height: " + rectangle.height());
            System.out.println("Area: " + rectangle.calculateArea());
        }
    }
 */