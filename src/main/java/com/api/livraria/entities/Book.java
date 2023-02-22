package com.api.livraria.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TB_BOOK")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Book implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "Titulo não pode ser vazio.")
    private String title;
    @NotBlank(message = "Descrição não pode ser vazia.")
    private String description;
    @Past
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(message = "Data não pode ser vazia.")
    private LocalDate releaseDate;
    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull(message = "Nome do autor não pode ser nulo.")
    private Author author;
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @NotNull(message = "Nome da editora não pode ser nulo.")
    private Publisher publisher;
    public Book() {
    }

    public Book(Long id, String title, String description, LocalDate releaseDate, Author author, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.author = author;
        this.publisher = publisher;
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
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
