package com.baryla.library_service.users;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getOneUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    public User createNewUser(User newUser, AccountType accountType){
        newUser.setAccountType(accountType);
        return userRepository.save(newUser);
    }

    public User updateUser(User newUser, Long id){
        User updatedUser = userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setAccountType(newUser.getAccountType());
                    return userRepository.save(user);
                }).orElseGet(()->{
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
        return updatedUser;
    }

    public User changeUserAccountType(Long id){
        User updateUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));

        if (updateUser.getAccountType() == AccountType.FREE){
            updateUser.setAccountType(AccountType.PREMIUM);
        }
        else {
            updateUser.setAccountType(AccountType.FREE);
        }
        return userRepository.save(updateUser);
    }

    public void deleteOneUser(Long id){
        userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    public List<User> getUsersWithAccount(AccountType accountType){
        return userRepository.getUsersWithAccountType(accountType);
    }
}
