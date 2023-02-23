package com.api.livraria.controllers;

import com.api.livraria.entities.Publisher;
import com.api.livraria.services.PublisherService;
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
    public ResponseEntity<List<Publisher>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }
    @GetMapping("/id={id}")
    public ResponseEntity<Publisher> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }
    @GetMapping("/name={name}")
    public ResponseEntity<Publisher> findByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
    }
    @PostMapping
    public ResponseEntity<Publisher> insert(@Valid @RequestBody Publisher publisher) {
       return service.insert(publisher);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Publisher> update(@PathVariable Long id, @Valid @RequestBody Publisher publisher) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, publisher));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
