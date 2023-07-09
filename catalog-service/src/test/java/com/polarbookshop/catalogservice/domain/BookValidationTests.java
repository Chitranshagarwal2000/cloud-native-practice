package com.polarbookshop.catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }
    }

    @Test
    void whenAllFieldsAreCorrectThenValidationSucceeds() {
        Book book = new Book("1234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        Assertions.assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnIsInvalidAndAnErrorIsThrown() {
        Book book = new Book("1234567890A", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        Assertions.assertThat(violations).hasSize(1);
        Assertions.assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid");
    }
}