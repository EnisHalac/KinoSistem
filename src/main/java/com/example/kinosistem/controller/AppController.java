package com.example.kinosistem.controller;

import com.example.kinosistem.model.Movie;
import com.example.kinosistem.model.Ticket;
import com.example.kinosistem.model.Director;
import com.example.kinosistem.repository.MovieRepository;
import com.example.kinosistem.repository.TicketRepository;
import com.example.kinosistem.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AppController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @GetMapping("/movies")
    public String getMovies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "listA";
    }

    @GetMapping("/tickets")
    public String getTickets(Model model) {
        List<Ticket> sortedTickets = ticketRepository.findAll().stream()
                .sorted((t1, t2) -> t1.getMovie().getTitle().compareToIgnoreCase(t2.getMovie().getTitle()))
                .toList();
        model.addAttribute("tickets", sortedTickets);
        return "listB";
    }

    @GetMapping("/movie/action/{id}")
    public String movieDetails(@PathVariable Long id, Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);
        model.addAttribute("movie", movie);

        // GENERIŠI SLUČAJAN BROJ SJEDALA
        if (movie != null) {
            List<Ticket> movieTickets = ticketRepository.findByMovieId(id);
            model.addAttribute("tickets", movieTickets);

            // Random seat broj od 1 do 100, prefix "A"
            String seatNumber = "A" + (new java.util.Random().nextInt(100) + 1);
            model.addAttribute("seatNumber", seatNumber);
        }

        return "action";
    }


    @PostMapping("/movie/buy/{id}")
    public String buyTicket(
            @PathVariable Long id,
            @RequestParam String customerName,
            @RequestParam String seatNumber,
            Model model
    ) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            Ticket newTicket = new Ticket();
            newTicket.setCustomerName(customerName);
            newTicket.setSeatNumber(seatNumber);
            newTicket.setPrice(movie.getPrice());
            newTicket.setMovie(movie);
            ticketRepository.save(newTicket);

            List<Ticket> movieTickets = ticketRepository.findByMovieId(id);
            model.addAttribute("tickets", movieTickets);
            model.addAttribute("movie", movie);
        }

        return "action";
    }

    // --- Directors ---

    @GetMapping("/directors")
    public String showAddDirectorForm(Model model) {
        model.addAttribute("director", new Director());
        model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("directors", directorRepository.findAll());
        return "addDirector";
    }

    @PostMapping("/directors/add")
    public String addDirector(@ModelAttribute Director director,
                              @RequestParam(value = "movieId", required = false) Long movieId) {

        directorRepository.save(director);

        if (movieId != null) {
            Movie movie = movieRepository.findById(movieId).orElse(null);
            if (movie != null) {
                movie.getDirectors().add(director);
                director.getMovies().add(movie);
                movieRepository.save(movie);
                directorRepository.save(director);
            }
        }

        return "redirect:/movies";
    }

    @RestController
    @RequestMapping("/api/directors")
    public class DirectorRestController {

        @GetMapping
        public List<Director> getAllDirectors() {
            return directorRepository.findAll();
        }

        @GetMapping("/{id}")
        public Director getDirectorById(@PathVariable Long id) {
            return directorRepository.findById(id).orElse(null);
        }
    }
}
