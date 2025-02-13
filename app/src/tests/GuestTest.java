package tests;

import domain.Guest;
import org.junit.Test;
import service.Movie;
import service.MovieRepository;
import service.Theater;

import java.io.StringReader;
import java.util.Scanner;
import static org.junit.Assert.assertTrue;

public class GuestTest {

    @Test
    public void testMakeReservation() {
        Scanner scanner = new Scanner(new StringReader("1\n1\n1\n1\n"));
        MovieRepository movieRepository = new MovieRepository();
        Movie movie = new Movie("Movie 1");
        movieRepository.addMovie(movie);
        Theater theater = new Theater();
        Guest guest = new Guest("John Doe");
        guest.makeReservation(scanner, movieRepository, theater);

        // Verify that the reservation was made
        assertTrue(guest.hasReservedSeat());
    }

    @Test
    public void testCancelReservation() {
        Scanner scanner = new Scanner(new StringReader("1\n1\n1\n1\n"));
        MovieRepository movieRepository = new MovieRepository();
        Movie movie = new Movie("Movie 1");
        movieRepository.addMovie(movie);
        Theater theater = new Theater();
        Guest guest = new Guest("John Doe");
        guest.makeReservation(scanner, movieRepository, theater);
        guest.cancelReservation(1);
        // Verify that the reservation was canceled
        assertTrue(guest.hasCanceledReservation());
    }
}
