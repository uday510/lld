package com.app.oops.encapsulation;

public class Movie {
    // Data members
    private String title;
    private int year;
    private String genre;

    // Default constructor
    public Movie() {
        title = "Unknown";
        year = -1;
        genre = "Unknown";
    }

    // Parameterized constructor
    public Movie(String title, int year, String genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    // getters setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String t) {
        title = t;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int y) {
        year = y;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String g) {
        genre = g;
    }

    void printDetails() {
        System.out.println("Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("Genre: " + genre);
    }

    public static void main(String[] args) {
        Movie movie = new Movie("The Dark Knight", 2008, "Action");
        movie.printDetails();

        System.out.println("-----");
        movie.setTitle("The Dark Knight Rises");
        movie.setYear(2012);
    }
}

