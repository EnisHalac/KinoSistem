package com.example.kinosistem.data;

import com.example.kinosistem.model.Movie;
import com.example.kinosistem.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoData {
    private List<Movie> movies = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    public DemoData() {
        movies.add(new Movie(1L, "Avatar", "Sci-Fi", 162, 8.1, 9.50, "/css/images/avatar.webp"));
        movies.add(new Movie(2L, "Oppenheimer", "Biografija", 180, 8.9, 10.00, "/css/images/oppenheimer.webp"));
        movies.add(new Movie(3L, "Inception", "Akcija", 148, 8.8, 8.00, "/css/images/inception.jpg"));
        movies.add(new Movie(4L, "Joker", "Drama", 122, 8.4, 7.50, "/css/images/joker.jpg"));
        movies.add(new Movie(5L, "Dune: Part Two", "Sci-Fi", 166, 8.6, 9.00, "/css/images/dune.webp"));


        tickets.add(new Ticket(1L, "A5", 9.50, "Amar", movies.get(0)));
        tickets.add(new Ticket(2L, "B2", 10.00, "Sara", movies.get(1)));
        tickets.add(new Ticket(3L, "C3", 8.00, "Haris", movies.get(2)));
        tickets.add(new Ticket(4L, "A1", 7.50, "Mina", movies.get(3)));
        tickets.add(new Ticket(5L, "E4", 9.00, "Emir", movies.get(4)));
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    // ➕ Dodaj ovo
    public Movie getMovieById(Long id) {
        return movies.stream()
                .filter(movie -> movie.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
