package com.api.livraria.repositories;

import com.api.livraria.entities.Book;
import com.api.livraria.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByName(String name);
    @Query("SELECT b FROM Book b WHERE b.publisher.id = :publisher_Id")
    List<Book> findBooksByPublisherId(@Param("publisher_Id") Long publisherId);
}
