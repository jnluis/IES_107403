package pt.ua.ies.ex3.repository;

import pt.ua.ies.ex3.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer>{
    Movie findByTitle(String title);
}
