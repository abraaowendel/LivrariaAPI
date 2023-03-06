package com.api.livraria.repositories;

import com.api.livraria.entities.Author;
import com.api.livraria.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
    @Query("SELECT b FROM Book b WHERE b.author.id = :author_Id")
    List<Book> findBooksByAuthorId(@Param("author_Id") Long authorId);
    
    boolean existsByName(String name);
}
