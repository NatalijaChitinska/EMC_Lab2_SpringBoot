package mk.ukim.finki.emt.demo.bootstrap;

import mk.ukim.finki.emt.demo.model.Author;
import mk.ukim.finki.emt.demo.model.Book;
import mk.ukim.finki.emt.demo.model.dto.BookDto;
import mk.ukim.finki.emt.demo.model.enumerations.Category;
import mk.ukim.finki.emt.demo.model.Country;
import mk.ukim.finki.emt.demo.service.AuthorService;
import mk.ukim.finki.emt.demo.service.BookService;
import mk.ukim.finki.emt.demo.service.CountryService;
import org.springframework.stereotype.Component;


@Component
public class DataHolder {

   private AuthorService authorService;
   private BookService bookService;
   private CountryService countryService;

    public DataHolder(AuthorService authorService, BookService bookService, CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;

        init();
    }

    public void init(){

        countryService.save("United Kingdom", "Europe");
        countryService.save("USA", "North America");
        countryService.save("North Macedonia", "Europe");
        countryService.save("Russia", "Asia");
        countryService.save("Italy", "Europe");
        countryService.save("France", "Europe");
        countryService.save("Nigeria", "Africa");


        authorService.save("William", "Shakespeare", countryService.findById(1L).get());
        authorService.save("Emily", "Brontë", countryService.findById(1L).get());
        authorService.save("Wole ", "Soyinka", countryService.findById(7L).get());
        authorService.save("Petre M.", "Andreevski", countryService.findById(3L).get());
        authorService.save("Albert", "Camus", countryService.findById(6L).get()); //L’Étranger

        bookService.save(new BookDto("Macbeth", 1L, Category.DRAMA, 6));
        bookService.save(new BookDto("Wuthering Heights", 2L, Category.NOVEL, 9));
        bookService.save(new BookDto("Pirej", 4L, Category.NOVEL, 4));
        bookService.save(new BookDto("L’Étranger", 5L, Category.NOVEL, 10));
        bookService.save(new BookDto("The Interpreters", 3L, Category.NOVEL, 15));
        bookService.save(new BookDto("Book 6", 3L, Category.FANTASY, 20));
        bookService.save(new BookDto("Book 7", 1L, Category.BIOGRAPHY, 12));
        bookService.save(new BookDto("Book 8", 5L, Category.FANTASY, 14));
        bookService.save(new BookDto("Book 9", 4L, Category.CLASSICS, 3));
        bookService.save(new BookDto("Book 10", 2L, Category.THRILER, 5));

    }
}
