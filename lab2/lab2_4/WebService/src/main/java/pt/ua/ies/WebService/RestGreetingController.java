package pt.ua.ies.WebService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@RestController
public class RestGreetingController {

    private static final List<Show> shows = List.of(
            new Show("1"," Star Wars, Empire Strikes Back (1980)"),
            new Show("2"," Toy Story (1995)"),
            new Show("3"," Men in Black (2019)"),
            new Show("4"," Die Hard (1988)"),
            new Show("5"," Titanic (1997)"),
            new Show("6"," Furious 7 (2015)"),
            new Show("7","Mulan (1998) "),
            new Show("8","Moulin Rouge (2001)"),
            new Show("9"," The Dark Knight (2008)"),
            new Show("10"," Up (2009)"),
            new Show("11"," Finding Dory (2016)")
    );

    private static final   List<Quote> quotes = List.of(
            new Quote("1","Luke, I am your father."),
            new Quote("2","This isn't flying. This is falling with style!"),
            new Quote("3","Let’s do this."),
            new Quote("4","Welcome to the party, pal!"),
            new Quote("5","I'm king of the world!"),
            new Quote("6","I don't have friends. I got family."),
            new Quote("7", "The flower that blooms in adversity is the most rare and beautiful of all."),
            new Quote("8", "The greatest thing you’ll ever learn is just to love and be loved in return. "),
            new Quote("9", "Some men just want to watch the world burn."),
            new Quote("10", "Adventure is out there"),
            new Quote("11", "When life gets you down, you know what you gotta do? Just keep swimming.")

    );

    @GetMapping("/quote")
    public Quote getRandomQuote() {
        // return a random quote
        Random rand = new Random();
        return quotes.get(rand.nextInt(quotes.size()));
    }

    @GetMapping("/shows")
    public List<Show> getShows() {
        // return the list of shows
        return shows;
    }

    @GetMapping("/quotes")
    public List<Quote> getQuotes(@RequestParam(value="show") String show) {
        // return the quotes for a specific show
        List<Quote> tmp = new ArrayList<>();
        for(Quote q: quotes){
            if (q.getId().equals(show)) {
                 tmp.add(q);
            }
        }
        return tmp;
    }

}