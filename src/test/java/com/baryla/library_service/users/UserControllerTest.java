package com.baryla.library_service.users;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    UserRepository userRepository = mock(UserRepository.class);
    UserController userController = new UserController(userRepository);

    @Test
    void getAllUsers() {
        List<User> ls = new ArrayList<>(){{add(
                new User("John", "Jameson", AccountType.FREE));
        }};
        when(userRepository.findAll()).thenReturn(ls);
        Assertions.assertEquals(userController.getAllUsers(), ls);
    }

    @Test
    void getOneUser() {
        User us = new User("John", "Jameson", AccountType.FREE);
        Long x = 1L;
        when(userRepository.findById(x)).thenReturn(java.util.Optional.of(us));
        Assertions.assertEquals(userController.getOneUser(x), us);
    }

    @Test
    void createNewUser() {
        User us = new User("John", "Jameson", AccountType.FREE);
        when(userRepository.save(us)).thenReturn(us);
        Assertions.assertEquals(userController.createNewUser(us, us.getAccountType()), us);
    }

    @Test
    void updateUser() {
        User us = new User("John", "Jameson", AccountType.FREE);
        Long x = 1L;
        when(userRepository.findById(x)).thenReturn(java.util.Optional.of(us));
        when(userRepository.save(us)).thenReturn(us);
        Assertions.assertEquals(userController.updateUser(us, x), us);
    }

    @Test
    void changeUserAccountType() {
        User us = new User("John", "Jameson", AccountType.FREE);
        Long x = 1L;
        when(userRepository.findById(x)).thenReturn(java.util.Optional.of(us));
        when(userRepository.save(us)).thenReturn(us);
        Assertions.assertEquals(userController.updateUser(us, x).getAccountType(), us.getAccountType());
    }


    @Test
    void getUsersWithAccount() {
        List<User> ls = new ArrayList<>(){{add(
                new User("John", "Jameson", AccountType.FREE));
        }};
        when(userRepository.getUsersWithAccountType(AccountType.FREE)).thenReturn(ls);
        Assertions.assertEquals(userController.getUsersWithAccount(AccountType.FREE), ls);
    }
}