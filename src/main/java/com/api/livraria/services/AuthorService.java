package com.api.livraria.services;

import com.api.livraria.dto.AuthorDTO;
import com.api.livraria.entities.Author;
import com.api.livraria.entities.Book;
import com.api.livraria.repositories.AuthorRepository;
import com.api.livraria.services.exceptions.DataBaseException;
import com.api.livraria.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<AuthorDTO> findAll() {
        return repository.findAll().stream().map(AuthorDTO::new).collect(Collectors.toList());
    }
    @Transactional
    public AuthorDTO findById(Long id) {
        Optional<Author> autorOptional = repository.findById(id);
        return autorOptional.map(AuthorDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Esse autor não existe."));
    }
    @Transactional
    public AuthorDTO findByName(String name) {
        Optional<Author> autorOptional = repository.findByName(name.replace("+", " "));
        return autorOptional.map(AuthorDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Esse autor não existe."));
    }

    @Transactional
    public AuthorDTO insert(Author author) {
        repository.save(author);
        return new AuthorDTO(author);
    }
    @Transactional
    public AuthorDTO update(Long id, Author author) {
        try {
            Author entity = repository.getReferenceById(id);
            entity.setName(author.getName());
            repository.save(entity);
            return new AuthorDTO(entity);
        }
        catch (EntityNotFoundException error){
            throw new ResourceNotFoundException("Autor não encontrado.");
        }
    }
    public void delete(Long id) {
        try {
            List<Book> list = repository.findBooksByAuthorId(id);
            if(!list.isEmpty()){
                throw new DataBaseException ("O autor tem livros associados e não pode ser deletado.");
            }
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException("Não foi possivel deletar esse autor, pois ele não existe.");
        }
        catch (DataIntegrityViolationException error){
            throw new DataBaseException("Violação de integridade");
        }
    }
}
