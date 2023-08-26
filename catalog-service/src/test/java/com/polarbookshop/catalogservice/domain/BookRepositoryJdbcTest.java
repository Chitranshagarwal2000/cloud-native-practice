package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DataConfig;
import com.polarbookshop.catalogservice.persistence.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@ActiveProfiles("integration")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryJdbcTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void findBookByIsbnWhenItExists() {
        var booksIsbn = "1234561237";
        var book = Book.of(booksIsbn, "Title", "Author", 12.12, "Polarsophia");
        jdbcAggregateTemplate.insert(book);
        Optional<Book> byIsbn = bookRepository.findByIsbn(booksIsbn);
        assertThat(byIsbn).isPresent();
        assertThat(byIsbn.get().isbn()).isEqualTo(book.isbn());
    }
}
