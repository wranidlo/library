package com.baryla.library_service;

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
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            userRepository.save(new User("John", "Jameson", AccountType.FREE));
            userRepository.save(new User("Andrew", "Anderson", AccountType.PREMIUM));
            userRepository.save(new User("Steve", "Stevens", AccountType.FREE));
            userRepository.findAll().forEach(user -> log.info("Preloaded " + user));

        };
    }
}
