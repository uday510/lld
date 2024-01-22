public class MagazineReader implements Subscriber {
    private final String name;
    public MagazineReader(String name) {
        this.name = name;
    }
    public void update(String issue) {
        System.out.println(name + " received a new issue: " + issue);
    }
}
