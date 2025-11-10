package com.example.kinosistem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

public class Director {
    private Long id;
    private String name;
    @JsonManagedReference
    private List<Movie> movies; // veza M:N sa Filmom

    public Director() {}

    public Director(Long id, String name, List<Movie> movies) {
        this.id = id;
        this.name = name;
        this.movies = movies;
    }

    // Getteri i setteri
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Movie> getMovies() { return movies; }
    public void setMovies(List<Movie> movies) { this.movies = movies; }
}
