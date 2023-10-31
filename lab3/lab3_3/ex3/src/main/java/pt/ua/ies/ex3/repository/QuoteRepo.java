package pt.ua.ies.ex3.repository;
import pt.ua.ies.ex3.entity.Quote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepo extends JpaRepository<Quote, Integer>{
    Quote findByAvaliacao(String title);
}
