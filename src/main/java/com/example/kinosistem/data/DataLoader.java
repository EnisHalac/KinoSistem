package com.example.kinosistem.data;

import com.example.kinosistem.model.*;
import com.example.kinosistem.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;
    private final DirectorRepository directorRepository;

    public DataLoader(MovieRepository movieRepository, TicketRepository ticketRepository, DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        this.directorRepository = directorRepository;
    }

    @Override
    public void run(String... args) {

        if (movieRepository.count() == 0) {

            // --- FILMOVI ---
            Movie avatar = new Movie();
            avatar.setTitle("Avatar");
            avatar.setGenre("Sci-Fi");
            avatar.setDuration(162);
            avatar.setRating(8.1);
            avatar.setPrice(9.50);
            avatar.setImageUrl("/css/images/avatar.webp");
            movieRepository.save(avatar);

            Movie oppenheimer = new Movie();
            oppenheimer.setTitle("Oppenheimer");
            oppenheimer.setGenre("Biografija");
            oppenheimer.setDuration(180);
            oppenheimer.setRating(8.9);
            oppenheimer.setPrice(10.00);
            oppenheimer.setImageUrl("/css/images/oppenheimer.webp");
            movieRepository.save(oppenheimer);

            Movie inception = new Movie();
            inception.setTitle("Inception");
            inception.setGenre("Akcija");
            inception.setDuration(148);
            inception.setRating(8.8);
            inception.setPrice(8.00);
            inception.setImageUrl("/css/images/inception.jpg");
            movieRepository.save(inception);

            Movie joker = new Movie();
            joker.setTitle("Joker");
            joker.setGenre("Drama");
            joker.setDuration(122);
            joker.setRating(8.4);
            joker.setPrice(7.50);
            joker.setImageUrl("/css/images/joker.jpg");
            movieRepository.save(joker);

            Movie dune = new Movie();
            dune.setTitle("Dune: Part Two");
            dune.setGenre("Sci-Fi");
            dune.setDuration(166);
            dune.setRating(8.6);
            dune.setPrice(9.00);
            dune.setImageUrl("/css/images/dune.webp");
            movieRepository.save(dune);

            // --- REŽISERI ---
            Director nolan = new Director();
            nolan.setName("Christopher Nolan");
            directorRepository.save(nolan);

            Director cameron = new Director();
            cameron.setName("James Cameron");
            directorRepository.save(cameron);

            // Poveži režisere s filmovima
            avatar.getDirectors().add(cameron);
            cameron.getMovies().add(avatar);

            oppenheimer.getDirectors().add(nolan);
            inception.getDirectors().add(nolan);

            movieRepository.save(avatar);
            movieRepository.save(oppenheimer);
            movieRepository.save(inception);

            directorRepository.save(cameron);
            directorRepository.save(nolan);

            // --- TIKETI ---
            Ticket t1 = new Ticket();
            t1.setSeatNumber("A5");
            t1.setPrice(9.50);
            t1.setCustomerName("Amar");
            t1.setMovie(avatar);
            ticketRepository.save(t1);

            Ticket t2 = new Ticket();
            t2.setSeatNumber("B2");
            t2.setPrice(10.00);
            t2.setCustomerName("Sara");
            t2.setMovie(oppenheimer);
            ticketRepository.save(t2);

            Ticket t3 = new Ticket();
            t3.setSeatNumber("C3");
            t3.setPrice(8.00);
            t3.setCustomerName("Haris");
            t3.setMovie(inception);
            ticketRepository.save(t3);

            Ticket t4 = new Ticket();
            t4.setSeatNumber("A1");
            t4.setPrice(7.50);
            t4.setCustomerName("Mina");
            t4.setMovie(joker);
            ticketRepository.save(t4);

            Ticket t5 = new Ticket();
            t5.setSeatNumber("E4");
            t5.setPrice(9.00);
            t5.setCustomerName("Emir");
            t5.setMovie(dune);
            ticketRepository.save(t5);

            System.out.println("Demo podaci su ubačeni u bazu!");
        }
    }
}
