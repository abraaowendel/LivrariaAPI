package com.api.livraria.services;

import com.api.livraria.entities.Author;
import com.api.livraria.repositories.AuthorRepository;
import com.api.livraria.services.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<Author> findAll() {
        return repository.findAll();
    }
    @Transactional
    public Author findById(Long id) {
        Optional<Author> autorOptional = repository.findById(id);
        return autorOptional.orElseThrow(() -> new ResourceNotFoundException("Esse autor não existe."));
    }
    @Transactional
    public Author findByName(String author) {
        Optional<Author> autorOptional = repository.findByName(author);
        return autorOptional.orElseThrow(() -> new ResourceNotFoundException("Esse autor não existe."));
    }

    @Transactional
    public Author insert(Author author) {
        Optional<Author> autorOptional = repository.findByName(author.getName().trim());
        return autorOptional.orElseGet(() -> repository.save(new Author(null, author.getName())));
    }

}
