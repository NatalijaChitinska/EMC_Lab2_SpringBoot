package mk.ukim.finki.emt.demo.web;

import mk.ukim.finki.emt.demo.model.Author;
import mk.ukim.finki.emt.demo.model.Country;
import mk.ukim.finki.emt.demo.model.exceptions.CountryNotFoundException;
import mk.ukim.finki.emt.demo.service.AuthorService;
import mk.ukim.finki.emt.demo.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="https://react-e-library.herokuapp.com/")
@RequestMapping("/api/authors")
public class AuthorRestController {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorRestController(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping
    public List<Author> findAll(){
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return this.authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestParam String name,
                                       @RequestParam String surname,
                                       @RequestParam Long countryId){
        Country country = this.countryService.findById(countryId).orElseThrow(()->new CountryNotFoundException(countryId));
        return this.authorService.save(name, surname, country)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.authorService.deleteById(id);
        if (this.authorService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

}
