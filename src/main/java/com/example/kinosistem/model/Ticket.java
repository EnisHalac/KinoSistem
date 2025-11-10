
package com.example.kinosistem.model;
import com.example.kinosistem.model.Movie;

public class Ticket {
    private Long id;
    private String seatNumber;
    private double price;
    private String customerName;

    private Movie movie; // veza s filmom

    public Ticket() {}

    public Ticket(Long id, String seatNumber, double price, String customerName, Movie movie) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.price = price;
        this.customerName = customerName;
        this.movie = movie;
    }

    // Getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
}
