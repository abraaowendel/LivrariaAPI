package com.api.livraria.controllers;

import com.api.livraria.entities.Book;
import com.api.livraria.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Book> insert(@Valid @RequestBody Book book) {
        validation(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(book));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book){
        validation(book);
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, book));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    public static void validation(Book entity){
        if (entity.getPublisher().getName().trim().equals("")) {
            throw new IllegalArgumentException("O nome da editora não pode estar vazio.");
        }
        if (entity.getAuthor().getName().trim().equals("")) {
            throw new IllegalArgumentException("O nome do autor não pode estar vazio.");
        }
    }
}
