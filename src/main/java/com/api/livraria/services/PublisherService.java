package com.api.livraria.services;

import com.api.livraria.entities.Book;
import com.api.livraria.entities.Publisher;
import com.api.livraria.repositories.PublisherRepository;
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
    public Publisher findByName(String name) {
        Optional<Publisher> publisherOptional = repository.findByName(name.replace("+", " "));
        return publisherOptional.orElseThrow(() -> new ResourceNotFoundException("Essa editora não existe."));
    }
    @Transactional
    public ResponseEntity<Publisher> insert(Publisher publisher) {
        Optional<Publisher> publisherOptional = repository.findByName(publisher.getName().trim());
        return publisherOptional.map(value ->
                ResponseEntity.status(HttpStatus.CONFLICT).body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(repository.save(new Publisher(null, publisher.getName()))));
    }
    @Transactional
    public Publisher update(Long id, Publisher publisher) {
        try {
            Publisher entity = repository.getReferenceById(id);
            entity.setName(publisher.getName());
            repository.save(entity);
            return entity;
        }
        catch (EntityNotFoundException error){
            throw new ResourceNotFoundException("Editora não encontrada.");
        }
    }
    public void delete(Long id) {
        try {
            List<Book> list = repository.findBooksByPublisherId(id);
            if(!list.isEmpty()){
                throw new DataBaseException("A editora tem livros associados e não pode ser deletada.");
            }
            repository.deleteById(id);        }
        catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException("Não foi possivel deletar essa editora, pois ele não existe.");
        }
        catch (DataIntegrityViolationException error){
            throw new DataBaseException("Violação de integridade");
        }

    }
}
