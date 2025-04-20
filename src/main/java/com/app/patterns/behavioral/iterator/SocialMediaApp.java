package com.app.patterns.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

interface PostIterator {
    boolean hasNext();
    Post next();
}

class Post {
    private String title;
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Post: " + title + " - " + content;
    }
}

class FeedIterator implements PostIterator {
    private List<Post> posts;
    private int position = 0;

    public FeedIterator(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean hasNext() {
        return position < posts.size();
    }

    @Override
    public Post next() {
        return posts.get(position++);
    }
}

interface Feed {
    PostIterator createIterator();
}

class SocialMediaFeed implements Feed {
    private List<Post> posts;

    public SocialMediaFeed() {
        posts = new ArrayList<>();
        posts.add(new Post("Vacation", "Having a great time at the beach!"));
        posts.add(new Post("Dinner", "Just had a delicious meal!"));
        posts.add(new Post("Workout", "Just finished a great workout session!"));
    }

    @Override
    public PostIterator createIterator() {
        return new FeedIterator(posts);
    }
}

public class SocialMediaApp {

    public static void main(String[] args) {
        SocialMediaFeed feed = new SocialMediaFeed();
        PostIterator iterator = feed.createIterator();

        System.out.println("Scrolling through the feed:");
        while (iterator.hasNext()) {
            Post post = iterator.next();
            System.out.println(post);
        }
    }

}
