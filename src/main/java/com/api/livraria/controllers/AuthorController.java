package com.api.livraria.controllers;

import com.api.livraria.entities.Author;
import com.api.livraria.services.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Obter todos os Autores", description = "Retorna todos os Autores")
    public ResponseEntity<List<Author>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping("/id={id}")
    @Operation(summary = "Obter um Autor por ID", description = "Retorna um Autor pelo ID")
    public ResponseEntity<Author> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    @GetMapping("/name={name}")
    @Operation(summary = "Obter um Autor pelo NOME", description = "Retorna um Autor pelo NOME")
    public ResponseEntity<Author> findByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
    }
    @PostMapping
    @Operation(summary = "Inserir um novo Autor", description = "Cria um novo Autor")
    public ResponseEntity<Author> insert(@Valid @RequestBody Author author) {
        return service.insert(author);
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar um Autor", description = "Atualiza um Autor existente")
    public ResponseEntity<Author> update(@PathVariable Long id, @Valid @RequestBody Author author){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, author));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um Autor", description = "Deleta um Autor existente")
    public ResponseEntity<Void> update(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
}
