package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IMDBScraper {
    public static List<Movie> getMovies() throws IOException {
        String url = "https://www.imdb.com/chart/top";
        Document doc = Jsoup.connect(url).get();
        Elements movieElements = doc.select("h3.ipc-title__text");
        List<Movie> movies = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            Element movieElement = movieElements.get(i);
            String movieTitle = movieElement.text();
            Movie movie = new Movie(movieTitle);
            movies.add(movie);
        }
        return movies;
    }
}