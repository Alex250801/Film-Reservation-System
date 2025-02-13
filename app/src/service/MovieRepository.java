package service;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private List<Movie> movies;

    public MovieRepository() {
        movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void showAvailableMovies() {
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i).getName());
        }
    }

    public void deleteMovie(int movieNumber) {
        movies.remove(movieNumber - 1);
    }

    public void deleteMovie(String movieName) {
        for (Movie movie : movies) {
            if (movie.getName().equals(movieName)) {
                movies.remove(movie);
                break;
            }
        }
    }
}
