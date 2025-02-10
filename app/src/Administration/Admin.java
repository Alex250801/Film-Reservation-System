package Administration;
import domain.Guest;
import service.Theater;
import service.MovieRepository;
import domain.GuestList;

import java.util.List;
import java.util.Scanner;

public class Admin extends Guest {

    private List<Guest> guests;

    public Admin(String name, Theater theater) {
        super(name);
    }

    public void deleteMovie(MovieRepository movieRepository, int movieNumber) {
        movieRepository.deleteMovie(movieNumber);
    }

    public void cancelGuestReservation(int choice) {
        cancelReservation(choice);
    }

    public void displayCinemaRoom(Theater theater) {
        theater.displayTheater();
    }
}
