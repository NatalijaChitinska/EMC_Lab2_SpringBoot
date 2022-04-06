package mk.ukim.finki.emt.demo.model.dto;

import lombok.Data;
import mk.ukim.finki.emt.demo.model.enumerations.Category;

@Data
public class BookDto {
    private String name;
    private Long authorId;
    private Category category;
    private int availableCopies;

    public BookDto() {
    }

    public BookDto(String name, Long authorId, Category category, int availableCopies) {
        this.name = name;
        this.authorId = authorId;
        this.category = category;
        this.availableCopies = availableCopies;
    }

    public String getName() {
        return name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Category getCategory() {
        return category;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }
}
