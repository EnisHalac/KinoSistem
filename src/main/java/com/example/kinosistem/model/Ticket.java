package com.example.kinosistem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;
    private double price;
    private String customerName;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

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
