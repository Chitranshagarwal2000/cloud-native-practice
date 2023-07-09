package com.polarbookshop.catalogservice.persistence;

import com.polarbookshop.catalogservice.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository {
    Optional<Book> findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);

    void deleteByIsbn(String isbn);

    Book save(Book book);

    Iterable<Book> findAll();
}
