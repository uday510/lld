public class D extends C {
    D() {
        System.out.println("D called");
    }

    D(String str) {
        super("paramter");
        System.out.println(str);
    }

}
