import java.util.ArrayList;
import java.util.List;

public class Newspaper implements MagazinePublisher {
    private final List<Subscriber> subscribers = new ArrayList<>();
    private String currentIssue;

    @Override
    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(String issue) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(issue);
        }
    }
    public void releaseNewIssue(String issue) {
        if (!issue.equals(currentIssue)) {
            currentIssue = issue;
            notifySubscribers("New issue released: " + currentIssue);
        }
    }
}
