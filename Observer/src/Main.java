public class Main {
    public static void main(String[] args) {

        Newspaper newYorkTimes = new Newspaper();

        MagazineReader reader1 = new MagazineReader("user1");
        MagazineReader reader2 = new MagazineReader("user2");

        newYorkTimes.subscribe(reader1);
        newYorkTimes.subscribe(reader2);

        newYorkTimes.releaseNewIssue("Jan 2024");

        newYorkTimes.unsubscribe(reader2);

        newYorkTimes.releaseNewIssue("Feb 2024");
    }
}
