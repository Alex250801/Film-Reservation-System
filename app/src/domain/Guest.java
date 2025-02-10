package domain;
import service.Movie;
import service.Theater;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import service.MovieRepository;

public class Guest {
    private String name;
    private Theater theater;
    private boolean reservedSeat;
    private boolean canceledReservation;
    private Map<String, String> reservations = new HashMap<>();
    private Set<String> canceledReservations = new HashSet<>();

    public Guest(String name) {
        this.name = name;
        this.theater = new Theater();
        this.reservations = new HashMap<>();
        this.reservedSeat = false;
        this.canceledReservation = false;
    }

    // lambdas
    public void showReservations() {
        if (reservations.isEmpty()) {
            System.out.println("You have no reservations.");
        } else {
            AtomicInteger i = new AtomicInteger(1);
            reservations.forEach((key, value) -> System.out.println(i.getAndIncrement() + ". " + key + " -> Seats: " + value));
        }
    }

    public void makeReservation(Scanner scanner, MovieRepository movieRepository, Theater theater) {
        movieRepository.showAvailableMovies();

        System.out.print("Enter the number of the movie you want to reserve: ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        Movie chosenMovie = movieRepository.getMovies().get(movieChoice - 1);

        System.out.print("Enter the number of seats you want to reserve: ");
        int numSeats = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        String key = chosenMovie.getName();
        String seats = "";

        for (int i = 0; i < numSeats; i++) {
            theater.displayTheater();
            System.out.print("RAND: ");
            int row = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            System.out.print("LOC: ");
            int column = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            theater.reserveSeat(row - 1, column - 1, chosenMovie);
            seats += "RAND:" + row + ", LOC:" + column + ",";

        }

        if (reservations.containsKey(key)) {
            String existingSeats = reservations.get(key);
            reservations.put(key, existingSeats + seats);
        } else {
            reservations.put(key, seats.substring(0, seats.length() - 1)); // Remove the trailing comma
        }
        reservedSeat = true;
    }

    public void cancelReservation(int choice) {
        int i = 1;
        for (String key : reservations.keySet()) {
            if (i == choice) {
                canceledReservations.add(key);
                reservations.remove(key);
                return;
            }
            i++;
        }
        System.out.println("Invalid choice. Please try again.");
    }

    public boolean hasCanceledReservation() {
        return !canceledReservations.isEmpty();
    }

    public boolean hasReservedSeat() {
        System.out.println("Checking if guest has reserved seat: " + name);
        System.out.println("Reserved seat: " + reservedSeat);
        System.out.println("Reservations: " + reservations);
        return reservedSeat;
    }

    public void displayTheater() {
        this.theater.displayTheater();
    }

    public String getName() {
        return name;
    }

    public void reserveSeat() {
        System.out.println("Reserving seat for guest: " + name);
        this.reservedSeat = true;
        System.out.println("Reserved seat: " + reservedSeat);
    }
}
