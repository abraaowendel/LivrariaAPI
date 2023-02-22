package com.api.livraria.services;

import com.api.livraria.entities.Author;
import com.api.livraria.entities.Publisher;
import com.api.livraria.repositories.PublisherRepository;
import com.api.livraria.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    private final PublisherRepository repository;
    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Publisher> findAll() {
        return repository.findAll();
    }
    @Transactional
    public Publisher findById(Long id) {
        Optional<Publisher> publisherOptional = repository.findById(id);
        return publisherOptional.orElseThrow(() -> new ResourceNotFoundException("Essa editora não existe."));
    }
    @Transactional
    public Publisher findByName(String publisher) {
        Optional<Publisher> publisherOptional = repository.findByName(publisher);
        return publisherOptional.orElseThrow(() -> new ResourceNotFoundException("Essa editora não existe."));
    }
    @Transactional
    public Publisher insert(Publisher publisher) {
        Optional<Publisher> publisherOptional = repository.findByName(publisher.getName().trim());
        return publisherOptional.orElseGet(() -> repository.save(new Publisher(null, publisher.getName())));
    }
}
