package com.example.kinosistem.controller;

import com.example.kinosistem.data.DemoData;
import com.example.kinosistem.model.Movie;
import com.example.kinosistem.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private DemoData demoData;

    @GetMapping("/movies")
    public String getMovies(Model model) {
        model.addAttribute("movies", demoData.getMovies());
        return "listA";
    }

    @GetMapping("/tickets")
    public String getTickets(Model model) {
        List<Ticket> sortedTickets = demoData.getTickets().stream()
                .sorted((t1, t2) -> t1.getMovie().getTitle().compareToIgnoreCase(t2.getMovie().getTitle()))
                .toList();
        model.addAttribute("tickets", sortedTickets);
        return "listB";
    }


    @GetMapping("/movie/action/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        Movie movie = demoData.getMovieById(id);
        model.addAttribute("movie", movie);

        // filtriramo karte za ovaj film
        List<Ticket> movieTickets = demoData.getTickets().stream()
                .filter(t -> t.getMovie().getId().equals(id))
                .toList();
        model.addAttribute("tickets", movieTickets);

        return "action";
    }


    @PostMapping("/movie/buy/{id}")
    public String buyTicket(
            @PathVariable Long id,
            @RequestParam String customerName,
            @RequestParam String seatNumber,
            Model model
    ) {
        Movie movie = demoData.getMovieById(id);

        Long newId = demoData.getTickets().stream()
                .mapToLong(Ticket::getId)
                .max()
                .orElse(0L) + 1;

        // koristimo fiksnu cijenu iz filma
        Ticket newTicket = new Ticket(newId, seatNumber, movie.getPrice(), customerName, movie);
        demoData.getTickets().add(newTicket);

        List<Ticket> movieTickets = demoData.getTickets().stream()
                .filter(t -> t.getMovie().getId().equals(id))
                .toList();

        model.addAttribute("movie", movie);
        model.addAttribute("tickets", movieTickets);

        return "action";
    }



}
