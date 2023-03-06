package com.api.livraria.dto;

import com.api.livraria.entities.Author;
import com.api.livraria.entities.Book;
import com.api.livraria.entities.Publisher;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class BookDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long id;
    @NotBlank(message = "Titulo não pode ser vazio ou nulo.")
    private String title;
    @NotBlank(message = "Descrição não pode ser vazia ou nulo.")
    private String description;
    @Past(message = "Deve ser uma data no passado.")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Data não pode ser vazia ou nula.")
    private LocalDate releaseDate;

    @NotNull(message = "Autor não pode ser nulo.")
    private AuthorDTO author;

    @NotNull(message = "Editora não pode ser nula.")
    private PublisherDTO publisher;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, String description, LocalDate releaseDate, AuthorDTO author, PublisherDTO publisher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.author = author;
        this.publisher = publisher;
    }
    public BookDTO(Book entity) {
        id = entity.getId();
        title = entity.getTitle();
        description = entity.getDescription();
        releaseDate = entity.getReleaseDate();
        author = new AuthorDTO(entity.getAuthor());
        publisher = new PublisherDTO(entity.getPublisher());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
        this.publisher = publisher;
    }
}
