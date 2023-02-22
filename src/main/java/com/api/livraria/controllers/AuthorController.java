package com.api.livraria.controllers;

import com.api.livraria.entities.Author;
import com.api.livraria.entities.Book;
import com.api.livraria.services.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService service;
    public AuthorController(AuthorService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Author>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    @GetMapping("/{name}")
    public ResponseEntity<Author> findByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
    }
    @PostMapping
    public ResponseEntity<Author> insert(@Valid @RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(author));
    }
}
