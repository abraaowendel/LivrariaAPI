package com.api.livraria.services;

import com.api.livraria.entities.Author;
import com.api.livraria.entities.Book;
import com.api.livraria.entities.Publisher;
import com.api.livraria.repositories.BookRepository;
import com.api.livraria.services.exceptions.DataBaseException;
import com.api.livraria.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;
    private final AuthorService authorService;
    private final PublisherService publisherService;

    public BookService(BookRepository repository, AuthorService authorService, PublisherService publisherService) {
        this.repository = repository;
        this.authorService = authorService;
        this.publisherService = publisherService;
    }

    @Transactional
    public List<Book> findAll() {
        return repository.findAll();
    }
    @Transactional
    public Book findById(Long id) {
        Optional<Book> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));
    }
    @Transactional
    public Book insert(Book entity) {

        Author author = authorService.insert(entity.getAuthor());
        Publisher publisher = publisherService.insert(entity.getPublisher());

        entity.setAuthor(author);
        entity.setPublisher(publisher);

        repository.save(entity);

        return entity;
    }

    @Transactional
    public Book update(Long id, Book entityUpdate) {
        try{
            Book entity = repository.getReferenceById(id);

            Author author = authorService.insert(entityUpdate.getAuthor());
            Publisher publisher = publisherService.insert(entityUpdate.getPublisher());

            entity.setTitle(entityUpdate.getTitle());
            entity.setDescription(entityUpdate.getDescription());
            entity.setReleaseDate(entityUpdate.getReleaseDate());
            entity.setAuthor(author);
            entity.setPublisher(publisher);

            repository.save(entity);

            return entity;
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Livro não encontrado.");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Não foi possivel deletar esse livro, pois ele não existe.");
        }
        catch (DataIntegrityViolationException error){
            throw new DataBaseException("Violação de integridade");
        }
    }
}
