package com.polarbookshop.catalogservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "polar")
public class PolarProperties {

    /**
     * Represents a greeting message.
     * <p>
     * This class encapsulates a string variable that holds a greeting message.
     * <p>
     * Example usage:
     * <p>
     * Greeting greeting = new Greeting();
     * greeting.setMessage("Hello, World!");
     * System.out.println(greeting.getMessage()); // Output: Hello, World!
     */
    private String greeting;
}
