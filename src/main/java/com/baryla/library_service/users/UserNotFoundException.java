package com.baryla.library_service.users;

public class UserNotFoundException extends RuntimeException{
    UserNotFoundException(Long id){
        super("User does not exists: " + id);
    }
}
