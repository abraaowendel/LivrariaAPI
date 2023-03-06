package com.api.livraria.services;

import com.api.livraria.dto.AuthorDTO;
import com.api.livraria.dto.BookDTO;
import com.api.livraria.dto.PublisherDTO;
import com.api.livraria.entities.Author;
import com.api.livraria.entities.Book;
import com.api.livraria.entities.Publisher;
import com.api.livraria.repositories.AuthorRepository;
import com.api.livraria.repositories.BookRepository;
import com.api.livraria.repositories.PublisherRepository;
import com.api.livraria.services.exceptions.DataBaseException;
import com.api.livraria.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository repository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public Page<BookDTO> findAllPaginated(Pageable pagination) {
        return repository.findAll(pagination).map(BookDTO::new);
    }
    @Transactional
    public BookDTO findById(Long id) {
        Optional<Book> entity = repository.findById(id);
        return entity.map(BookDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não encontrado."));
    }
    @Transactional
    public BookDTO insert(BookDTO dto) {

        Author author = checkAuthor(dto.getAuthor().getName());
        Publisher publisher = checkPublisher(dto.getPublisher().getName());

        Book entity = new Book(null, dto.getTitle(),
                dto.getDescription(), dto.getReleaseDate(),
                author, publisher);

        repository.save(entity);

        return new BookDTO(entity);
    }

    @Transactional
    public BookDTO update(Long id, BookDTO dto) {
        try{
            Book entity = repository.getReferenceById(id);

            entity.setTitle(dto.getTitle());
            entity.setDescription(dto.getDescription());
            entity.setReleaseDate(dto.getReleaseDate());

            Author author = checkAuthor(dto.getAuthor().getName());
            Publisher publisher = checkPublisher(dto.getPublisher().getName());


            entity.setAuthor(author);
            entity.setPublisher(publisher);

            repository.save(entity);

            return new BookDTO(entity);
        }
        catch (EntityNotFoundException error){
            throw new ResourceNotFoundException("Livro não encontrado.");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException("Não foi possivel deletar esse livro, pois ele não existe.");
        }
        catch (DataIntegrityViolationException error){
            throw new DataBaseException("Violação de integridade");
        }
    }

    public Author checkAuthor(String name){
        return authorRepository.findByName(name)
                .orElseGet(() -> {
                    Author newAuthor = new Author(null, name);
                    authorRepository.save(newAuthor);
                    return newAuthor;
                });
    }
    public Publisher checkPublisher(String name){
        return publisherRepository.findByName(name)
                .orElseGet(() -> {
                    Publisher newPublisher = new Publisher(null, name);
                    publisherRepository.save(newPublisher);
                    return newPublisher;
                });
    }
}
