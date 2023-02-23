package com.api.livraria.controllers;

import com.api.livraria.entities.Book;
import com.api.livraria.services.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Book>> findAllPaginated(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "12") Integer size,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "id") String orderBy){

        PageRequest pagination = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);

        return ResponseEntity.status(HttpStatus.OK).body(service.findAllPaginated(pagination));
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Book> insert(@Valid @RequestBody Book book) {

        validateNotEmpty(book.getAuthor().getName(), "Autor");
        validateNotEmpty(book.getPublisher().getName(), "Editora");

        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(book));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @Valid @RequestBody Book book){

        validateNotEmpty(book.getAuthor().getName(), "Autor");
        validateNotEmpty(book.getPublisher().getName(), "Editora");

        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, book));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    public void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo " + fieldName + " não pode ser nulo ou vazio.");
        }
    }
}
