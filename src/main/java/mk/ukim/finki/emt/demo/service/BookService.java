package mk.ukim.finki.emt.demo.service;

import mk.ukim.finki.emt.demo.model.Book;
import mk.ukim.finki.emt.demo.model.dto.BookDto;
import mk.ukim.finki.emt.demo.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, Category category, Long authorId, int availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, Category category, Long authorId, int availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    void deleteBeId(Long id);

    void decreaseAvailableCopies(Long id);
}
