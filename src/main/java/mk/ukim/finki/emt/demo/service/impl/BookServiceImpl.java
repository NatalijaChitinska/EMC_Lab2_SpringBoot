package mk.ukim.finki.emt.demo.service.impl;

import mk.ukim.finki.emt.demo.model.Author;
import mk.ukim.finki.emt.demo.model.Book;
import mk.ukim.finki.emt.demo.model.Country;
import mk.ukim.finki.emt.demo.model.dto.BookDto;
import mk.ukim.finki.emt.demo.model.enumerations.Category;
import mk.ukim.finki.emt.demo.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.emt.demo.model.exceptions.BookNotFoundException;
import mk.ukim.finki.emt.demo.repository.AuthorRepository;
import mk.ukim.finki.emt.demo.repository.BookRepository;
import mk.ukim.finki.emt.demo.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, int availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(()-> new AuthorNotFoundException(authorId));
        Book newBook = new Book(name, category, author, availableCopies);

        return Optional.of(this.bookRepository.save(newBook));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(()-> new AuthorNotFoundException(bookDto.getAuthorId()));
        Book newBook = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(newBook));
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, int availableCopies) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
//        Author author = this.authorRepository.findById(bookDto.getAuthorId())
//                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        System.out.println(bookDto.getAuthorId());
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
//        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteBeId(Long id) {
        this.bookRepository.deleteById(id);

    }

    @Override
    public void decreaseAvailableCopies(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        if (book.getAvailableCopies() > 0){
            int leftCopies = book.getAvailableCopies() - 1;
            book.setAvailableCopies(leftCopies);
            this.bookRepository.save(book);
        }
    }
}
