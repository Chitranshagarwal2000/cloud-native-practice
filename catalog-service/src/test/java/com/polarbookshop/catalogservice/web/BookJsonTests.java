package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ActiveProfiles("integration")
class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws IOException {
        Book book = Book.of("123456789", "Title", "Author", 12.23);
        JsonContent<Book> write = json.write(book);
        assertThat(write).extractingJsonPathStringValue("@.isbn").isEqualTo(book.isbn());
        assertThat(write).extractingJsonPathStringValue("@.title").isEqualTo(book.title());
        assertThat(write).extractingJsonPathStringValue("@.author").isEqualTo(book.author());
        assertThat(write).extractingJsonPathNumberValue("@.price").isEqualTo(book.price());
    }

    @Test
    void deserialize() throws IOException {

        String content = """
                {
                    "isbn": "1234567890",
                    "title": "Title",
                    "author": "Author",
                    "price": 12.32
                }
                """;
        assertThat(json.parse(content))
                .usingRecursiveAssertion()
                .isEqualTo(Book.of("1234567890", "Title", "Author", 12.32));
    }

}