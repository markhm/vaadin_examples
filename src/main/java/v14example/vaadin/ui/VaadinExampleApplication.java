package v14example.vaadin.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// https://www.google.com/search?client=safari&rls=en&q=run+spring+boot+application+as+windows+service&ie=UTF-8&oe=UTF-8

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class VaadinExampleApplication extends SpringBootServletInitializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(VaadinExampleApplication.class, args);
    }
}
