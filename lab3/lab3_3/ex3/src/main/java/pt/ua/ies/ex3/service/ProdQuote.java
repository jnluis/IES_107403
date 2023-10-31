package pt.ua.ies.ex3.service;

import pt.ua.ies.ex3.entity.Movie;
import pt.ua.ies.ex3.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdQuote {
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
        Movie existingMovie = repo.findById((int)movie.getId()).orElse(null);
        assert existingMovie != null;
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setYear(movie.getYear());
        return repo.save(existingMovie);
    }
}
