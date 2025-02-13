package com.app.oops.datahiding.encapsulation;

public class Movie {
    // Data members
    private String title;
    private int year;
    private String genre;

    // Default constructor
    public Movie() {
        title = "";
        year = -1;
        genre = "";
    }

    // Parameterized constructor
    public Movie(String title, int year, String genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    // getters setters
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    void printDetails() {
        System.out.println("Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("Genre: " + genre);
    }

    public static void main(String[] args) {
        Movie movie = new Movie("The Lion King", 1994, "Adventure");
        movie.printDetails();

        System.out.println("---");
        movie.setTitle("Forrest Gump");
        System.out.println("New title: " + movie.getTitle());
    }
}
