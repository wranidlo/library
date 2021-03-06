package com.baryla.library_service.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(Long id){
        super("User does not exists: " + id);
    }
}
