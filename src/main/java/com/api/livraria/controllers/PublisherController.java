package com.api.livraria.controllers;

import com.api.livraria.entities.Publisher;
import com.api.livraria.services.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService service;
    public PublisherController(PublisherService service) {
        this.service = service;
    }
    @GetMapping
    @Operation(summary = "Obter todas as Editoras", description = "Retorna todas as Editora")
    public ResponseEntity<List<Publisher>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping("/id={id}")
    @Operation(summary = "Obter uma Editora pelo ID", description = "Retorna uma Editora pelo ID")
    public ResponseEntity<Publisher> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    @GetMapping("/name={name}")
    @Operation(summary = "Obter uma Editora pelo NOME", description = "Retorna uma Editora pelo NOME")
    public ResponseEntity<Publisher> findByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
    }
    @PostMapping
    @Operation(summary = "Inserir uma nova  Editora", description = "Cria uma nova Editora")
    public ResponseEntity<Publisher> insert(@Valid @RequestBody Publisher publisher) {
       return service.insert(publisher);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma Editora", description = "Atualiza uma Editora existente")
    public ResponseEntity<Publisher> update(@PathVariable Long id, @Valid @RequestBody Publisher publisher) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, publisher));
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar uma Editora", description = "Deleta uma Editora existente")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
