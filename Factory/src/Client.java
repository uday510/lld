import Components.Button.Button;

public class Client {
    public static void main(String[] args) {
        Flutter flutter = new Flutter();
        UIFactory factory = flutter.createUIFactory("Android");
        Button b = factory.createButton();
        b.getSize();
    }
}

