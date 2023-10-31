package pt.ua.ies.ex3.service;

import pt.ua.ies.ex3.entity.Movie;
import pt.ua.ies.ex3.entity.Quote;
import pt.ua.ies.ex3.repository.MovieRepo;
import pt.ua.ies.ex3.repository.QuoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepo repository;

    public Quote saveQuote(Quote movie) {
        return repository.save(movie);
    }

    public List<Quote> saveQuotes(List<Quote> movies) {
        return repository.saveAll(movies);
    }

    public List<Quote> getQuotes() {
        return repository.findAll();
    }

    public Quote getQuoteById(int id) {
        return repository.findById((int)id).orElse(null);
    }

    public Quote getQuoteByName(String title) {
        return repository.findByAvaliacao(title);
    }

    public String deleteQuote(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Quote updateQuote(Quote movie) {
        Quote existingMovie = repository.findById((int)movie.getId()).orElse(null);
        assert existingMovie != null;
        existingMovie.setAvaliacao(movie.getAvaliacao());
        return repository.save(existingMovie);
    }

    @Autowired
    private MovieRepo repo;

    public Movie saveMovie(Movie movie) {
        return repo.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        return repo.saveAll(movies);
    }

    public List<Movie> getMovies() {
        return repo.findAll();
    }

    public Movie getMovieById(long id) {
        return repo.findById((int)id).orElse(null);
    }

    public Movie getMovieByName(String title) {
        return repo.findByTitle(title);
    }

    public String deleteMovie(int id) {
        repo.deleteById(id);
        return "product removed !! " + id;
    }

    public Movie updateMovie(Movie movie) {
        Movie existingMovie = repo.findById(movie.getId()).orElse(null);
        assert existingMovie != null;
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setYear(movie.getYear());
        return repo.save(existingMovie);
    }
}
