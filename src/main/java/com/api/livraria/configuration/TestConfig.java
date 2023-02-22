package com.api.livraria.configuration;

import com.api.livraria.entities.Author;
import com.api.livraria.entities.Book;
import com.api.livraria.entities.Publisher;
import com.api.livraria.repositories.AuthorRepository;
import com.api.livraria.repositories.BookRepository;
import com.api.livraria.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public TestConfig(BookRepository bookRepository,
                      AuthorRepository authorRepository,
                      PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }
    @Override
    public void run(String... args) {

        Publisher p1 = new Publisher(null, "Companhia das Letras");
        Publisher p2 = new Publisher(null, "Editora Record");
        Publisher p3 = new Publisher(null, "Editora Intrínseca");
        Publisher p4 = new Publisher(null, "Editora Sextante");
        publisherRepository.saveAll(Arrays.asList(p1, p2, p3, p4));

        Author a1 = new Author(null, "Machado de Assis");
        Author a2 = new Author(null, "Clarice Lispector");
        Author a3 = new Author(null, "Jorge Amado");
        Author a4 = new Author(null, "Cecília Meireles");
        Author a5 = new Author(null, "Guimarães Rosa");
        Author a6 = new Author(null, "Carlos Drummond de Andrade");
        authorRepository.saveAll(Arrays.asList(a1, a2, a3, a4, a5, a6));

        Book b1 = new Book(null, "Dom Casmurro", "Romance de Machado de Assis que narra a história de Bentinho e Capitu, amigos de infância que se apaixonam e enfrentam diversas adversidades.", LocalDate.of(1899,1,1), a1, p1);
        Book b2 = new Book(null, "A Hora da Estrela", "A Hora da Estrela é um romance de Clarice Lispector publicado em 1977, pouco tempo depois da morte da escritora.", LocalDate.of(1977,1, 1), a2, p2);
        Book b3 = new Book(null, "Capitães da Areia", "Capitães da Areia é um romance de autoria do escritor brasileiro Jorge Amado, publicado em 1937.", LocalDate.of(1937, 1,1), a3, p3);
        Book b4 = new Book(null, "Ou Isto ou Aquilo", "Coletânea de poemas infantis de Cecília Meireles, publicada pela primeira vez em 1964.", LocalDate.of(1964,1,1), a4, p4);
        Book b5 = new Book(null, "Grande Sertão: Veredas", "Grande Sertão: Veredas é um romance escrito por João Guimarães Rosa, publicado originalmente em 1956.", LocalDate.of(1956,1,1), a5, p1);
        Book b6 = new Book(null, "Alguma Poesia", "Primeiro livro de poesias de Carlos Drummond de Andrade, publicado em 1930.", LocalDate.of(1930,1,1), a6, p2);
        Book b7 = new Book(null, "O Alienista", "Conto de Machado de Assis, publicado pela primeira vez em 1882.", LocalDate.of(1882, 1,1), a1, p1);
        Book b8 = new Book(null, "Laços de Família", "Coletânea de contos de Clarice Lispector, publicada em 1960.", LocalDate.of(1960, 1,1), a2, p2);
        Book b9 = new Book(null, "Tenda dos Milagres", "Tenda dos Milagres é um romance de Jorge Amado, publicado originalmente em 1969.", LocalDate.of(1969,1,1), a3, p3);
        Book b10 = new Book(null, "Criança, meu Amor", "Criança, meu Amor é um livro de poemas de Carlos Drummond de Andrade, publicado originalmente em 1940.", LocalDate.of(1940,1,1), a6, p4);

        bookRepository.saveAll(Arrays.asList(b1, b2, b3, b4, b5, b6,b7, b8, b9, b10));

    }
}
