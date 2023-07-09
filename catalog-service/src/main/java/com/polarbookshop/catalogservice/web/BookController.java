package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Book> findAll() {
        return bookService.viewBookList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBookToCatalog(@RequestBody @Valid Book book) {
        return bookService.addBookToCatalog(book);
    }

    @GetMapping("/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Book getByIsbn(@PathVariable String isbn) {
        return bookService.viewBookDetails(isbn);
    }

    @PutMapping("/{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@RequestBody @Valid Book book, @PathVariable String isbn) {
        return bookService.editBookDetails(isbn, book);
    }

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable String isbn) {
        bookService.removeBookFromCatalog(isbn);
    }
}
