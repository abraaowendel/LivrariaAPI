package com.api.livraria.controllers;

import com.api.livraria.dto.AuthorDTO;
import com.api.livraria.entities.Author;
import com.api.livraria.repositories.AuthorRepository;
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
    private final AuthorRepository authorRepository;

    public AuthorController(AuthorService service,
                            AuthorRepository authorRepository) {
        this.service = service;
        this.authorRepository = authorRepository;
    }
    @GetMapping
    @Operation(summary = "Obter todos os Autores", description = "Retorna todos os Autores")
    public ResponseEntity<List<AuthorDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping("/id={id}")
    @Operation(summary = "Obter um Autor por ID", description = "Retorna um Autor pelo ID")
    public ResponseEntity<AuthorDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    @GetMapping("/name={name}")
    @Operation(summary = "Obter um Autor pelo NOME", description = "Retorna um Autor pelo NOME")
    public ResponseEntity<AuthorDTO> findByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
    }
    @PostMapping
    @Operation(summary = "Inserir um novo Autor", description = "Cria um novo Autor")
    public ResponseEntity<?> insert(@Valid @RequestBody Author author) {
        if(authorRepository.existsByName(author.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario Já existe");
        }
        service.insert(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthorDTO(author));
    }
    @PutMapping(value = "/{id}")
    @Operation(summary = "Atualizar um Autor", description = "Atualiza um Autor existente")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @Valid @RequestBody Author author){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, author));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um Autor", description = "Deleta um Autor existente")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.notFound().build();
    }
}
