package main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import domain.GuestList;
import service.IMDBScraper;
import service.Theater;
import domain.Guest;
import service.Movie;
import service.MovieRepository;
import Administration.Admin;

public class Main {
    public static void main(String[] args) {
        Theater theater = new Theater();
        GuestList guestList = new GuestList();
        Scanner scanner = new Scanner(System.in);
        MovieRepository movieRepository = new MovieRepository();

        try {
            List<Movie> scrapedMovies = IMDBScraper.getMovies();
            for (Movie movie : scrapedMovies) {
                movieRepository.addMovie(movie);
            }
        } catch (IOException e) {
            System.out.println("Error scraping movies: " + e.getMessage());
        }

        while (true) {
            System.out.println("\nWelcome to the theater!");
            System.out.println("1. Create a new guest");
            System.out.println("2. Login as admin");
            System.out.println("3. Display cinema");
            System.out.println("4. See available movies");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    // GUEST LOGIN
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();

                    Guest guest;
                    if (guestList.getGuest(name) != null) {
                        guest = guestList.getGuest(name);
                        System.out.println("Welcome back, " + name + "!");
                        guest.showReservations();
                    } else {
                        guest = new Guest(name);
                        guestList.addGuest(guest);
                        System.out.println("Welcome, " + name + "!");
                    }

                    while (true) {
                        System.out.println("\n1. Show my reservations");
                        System.out.println("2. Make a reservation");
                        System.out.println("3. Cancel a reservation");
                        System.out.println("4. Display Cinema");
                        System.out.println("5. See available movies");
                        System.out.println("6. Back to main menu");
                        System.out.print("\nChoose an option: ");
                        int guestOption = scanner.nextInt();
                        scanner.nextLine();

                        switch (guestOption) {
                            case 1:
                                guest.showReservations();
                                break;
                            case 2:
                                guest.makeReservation(scanner, movieRepository, theater);
                                break;
                            case 3:
                                guest.showReservations();
                                System.out.print("Enter the number of the reservation you want to cancel: ");
                                if (scanner.hasNextInt()) {
                                    int choice = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline left-over
                                    guest.cancelReservation(choice);
                                } else {
                                    System.out.println("Invalid input. Please enter a number.");
                                }
                                break;
                            case 4:
                                guest.displayTheater();
                                break;
                            case 5:
                                movieRepository.showAvailableMovies();
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("Invalid option");
                        }

                        if (guestOption == 6) {
                            break;
                        }
                    }
                    break;
                case 2:
                    // ADMIN LOGIN
                    System.out.print("Enter admin username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.nextLine();
                    if (adminUsername.equals("Admin") && adminPassword.equals("admin")) {
                        Admin admin = new Admin("Admin", theater);
                        while (true) {
                            System.out.println("\n1. Delete movie");
                            System.out.println("2. Cancel guest reservation");
                            System.out.println("3. Display cinema room");
                            System.out.println("4. See current movies");
                            System.out.println("5. Add a movie");
                            System.out.println("6. Logout");
                            System.out.print("Choose an option: ");
                            int adminOption = scanner.nextInt();
                            scanner.nextLine();

                            switch (adminOption) {
                                case 1:
                                    movieRepository.showAvailableMovies();
                                    System.out.print("Enter the number of the movie to delete: ");
                                    int movieNumber = scanner.nextInt();
                                    scanner.nextLine();
                                    admin.deleteMovie(movieRepository, movieNumber);
                                    break;
                                case 2:
                                    System.out.println("Cancel guest reservation");
                                    guestList.showGuestsAndReservations();
                                    System.out.print("Enter the number of the guest to cancel reservation: ");
                                    int guestNumber = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline left-over
                                    String guestName = guestList.getGuestNameByNumber(guestNumber);
                                    Guest guest4 = guestList.getGuest(guestName);
                                    guest4.showReservations();
                                    System.out.print("Enter the number of the reservation to cancel: ");
                                    int reservationNumber = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline left-over
                                    guest4.cancelReservation(reservationNumber);
                                    break;
                                case 3:
                                    admin.displayCinemaRoom(theater);
                                    break;
                                case 4:
                                    movieRepository.showAvailableMovies();
                                    break;
                                case 5:
                                    System.out.print("Enter the title of the movie you want to add: ");
                                    String movieTitle = scanner.nextLine();
                                    int newIndex = movieRepository.getMovies().size() + 1;
                                    String indexedTitle = newIndex + ". " + movieTitle;
                                    Movie movie = new Movie(indexedTitle);
                                    movieRepository.addMovie(movie);
                                    break;
                                case 6:
                                    break;
                                default:
                                    System.out.println("Invalid option");
                            }

                            if (adminOption == 6) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("You have no admin privileges");
                    }
                    break;
                case 3:
                    theater.displayTheater();
                    break;
                case 4:
                    movieRepository.showAvailableMovies();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
