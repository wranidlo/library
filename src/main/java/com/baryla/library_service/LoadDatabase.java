package com.baryla.library_service;

import com.baryla.library_service.books.Book;
import com.baryla.library_service.books.BookRepository;
import com.baryla.library_service.users.AccountType;
import com.baryla.library_service.users.User;
import com.baryla.library_service.users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, BookRepository bookRepository) {
        return args -> {
            userRepository.save(new User("John", "Jameson", AccountType.FREE));
            userRepository.save(new User("Andrew", "Anderson", AccountType.PREMIUM));
            userRepository.save(new User("Steve", "Stevens", AccountType.FREE));
            userRepository.findAll().forEach(user -> log.info("Preloaded " + user));

            bookRepository.save(new Book("Tai-Pan", "James Clavell",2001));
            bookRepository.save(new Book("Quo vadis", "Henryk Sienkiewicz",1999));
            bookRepository.save(new Book("Ogniem i mieczem", "Henryk Sienkiewicz",2006));
            bookRepository.findAll().forEach(book -> log.info("Preloaded " + book));

        };
    }
}
