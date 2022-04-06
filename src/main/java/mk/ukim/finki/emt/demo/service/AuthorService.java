package mk.ukim.finki.emt.demo.service;

import mk.ukim.finki.emt.demo.model.Author;
import mk.ukim.finki.emt.demo.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Country country);

    void deleteById(Long id);
}
