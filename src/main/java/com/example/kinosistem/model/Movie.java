package com.example.kinosistem.model;
import com.example.kinosistem.model.Ticket;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private Long id;
    private String title;
    private String genre;
    private int duration; // minute
    private double rating;
    private double price;
    private String imageUrl;

    // Relacija 1:N -> jedan film ima više karata
    private List<Ticket> tickets = new ArrayList<>();

    public Movie() {}

    public Movie(Long id, String title, String genre, int duration, double rating, double price, String imageUrl) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }
    // Getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
