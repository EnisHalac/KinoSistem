package com.example.kinosistem.controller;

import com.example.kinosistem.data.DemoData;
import com.example.kinosistem.model.Movie;
import com.example.kinosistem.model.Director;
import com.example.kinosistem.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        com.example.kinosistem.model.Ticket newTicket = new com.example.kinosistem.model.Ticket(newId, seatNumber, movie.getPrice(), customerName, movie);
        demoData.getTickets().add(newTicket);

        List<Ticket> movieTickets = demoData.getTickets().stream()
                .filter(t -> t.getMovie().getId().equals(id))
                .toList();

        model.addAttribute("movie", movie);
        model.addAttribute("tickets", movieTickets);

        return "action";
    }


    @Controller
    public class DirectorController {

        @Autowired
        private DemoData demoData;

        // Prikaz forme za dodavanje režisera + listu filmova za odabir
        @GetMapping("/directors")
        public String showAddDirectorForm(Model model) {
            model.addAttribute("director", new Director());
            model.addAttribute("movies", demoData.getMovies());
            model.addAttribute("directors", demoData.getDirectors());
            return "addDirector";
        }

        // Obrada forme za dodavanje režisera i opcionalno povezivanje s filmom
        @PostMapping("/directors/add")
        public String addDirector(@ModelAttribute Director director,
                                  @RequestParam(value = "movieId", required = false) Long movieId) {

            // demoData će postaviti id režiseru
            demoData.addDirector(director);

            // ako je odabran film, povežemo
            if (movieId != null) {
                demoData.addDirectorToMovie(movieId, director.getId());
            }

            return "redirect:/movies";
        }
    }
    @RestController
    @RequestMapping("/api/directors") // svi endpointi će počinjati s /api/directors
    public class DirectorRestController {

        @Autowired
        private DemoData demoData;

        // GET /api/directors → vraća sve režisere
        @GetMapping
        public List<Director> getAllDirectors() {
            return demoData.getDirectors();
        }

        // GET /api/directors/{id} → vraća jednog režisera po id
        @GetMapping("/{id}")
        public Director getDirectorById(@PathVariable Long id) {
            return demoData.getDirectorById(id);
        }


    }

}
