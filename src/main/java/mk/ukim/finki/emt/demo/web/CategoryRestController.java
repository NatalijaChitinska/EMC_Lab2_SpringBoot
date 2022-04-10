package mk.ukim.finki.emt.demo.web;

import mk.ukim.finki.emt.demo.model.enumerations.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="https://react-e-library.herokuapp.com/")
@RequestMapping("/api/categories")
public class CategoryRestController {

    private List<String> categories;
    public CategoryRestController(){
        this.categories=new ArrayList<>();
        categories.add("NOVEL");
        categories.add("THRILER");
        categories.add("HISTORY");
        categories.add("FANTASY");
        categories.add("BIOGRAPHY");
        categories.add("CLASSICS");
        categories.add("DRAMA");
    }

    @GetMapping
    public List<String> listAll(){
        return this.categories;
    }
}
