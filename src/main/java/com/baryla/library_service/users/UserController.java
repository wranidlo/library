package com.baryla.library_service.users;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/library")
public class UserController {
    private final UserRepository userRepository;
    private final UserModelAssembler userModelAssembler;

    public UserController(UserRepository userRepository, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userModelAssembler = userModelAssembler;
    }

    // Get all users
    @GetMapping("/users")
    CollectionModel<EntityModel<User>> all() {

        List<EntityModel<User>> users = userRepository.findAll().stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    // Get one user
    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userModelAssembler.toModel(user);
    }

    //Add one free user
    @PostMapping("/users/free")
    ResponseEntity<?> newFreeUser(@RequestBody User newUser){
        newUser.setAccountType(AccountType.FREE);
        EntityModel<User> userEntityModel = userModelAssembler.toModel(userRepository.save(newUser));
        return ResponseEntity.created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userEntityModel);
    }

    //Add one premium user
    @PostMapping("/users/premium")
    ResponseEntity<?> newPremiumUser(@RequestBody User newUser){
        newUser.setAccountType(AccountType.PREMIUM);
        EntityModel<User> userEntityModel = userModelAssembler.toModel(userRepository.save(newUser));
        return ResponseEntity.created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userEntityModel);
    }

    // Update one user
    @PutMapping("/users/update/{id}")
    ResponseEntity<?> updateUser(@RequestBody User newUser, @PathVariable Long id){
        User updateUser = userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setAccountType(newUser.getAccountType());
                    return userRepository.save(user);
                }).orElseGet(()->{
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
        EntityModel<User> userEntityModel = userModelAssembler.toModel(updateUser);
        return ResponseEntity.created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userEntityModel);
    }

    // Change account type
    @PutMapping("/users/change/{id}")
    ResponseEntity<?> changeUserAccountType(@PathVariable Long id){
        User updateUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));

        if (updateUser.getAccountType() == AccountType.FREE){
            updateUser.setAccountType(AccountType.PREMIUM);
        }
        else {
            updateUser.setAccountType(AccountType.FREE);
        }
        return ResponseEntity.ok(userModelAssembler.toModel(userRepository.save(updateUser)));
    }

    // Delete one user
    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id){
        userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Users with account type
    @GetMapping("/users/account")
    CollectionModel<EntityModel<User>> getUsersWithAccountType(@RequestParam(value = "type") AccountType type){
        List<EntityModel<User>> entityModelList = userRepository.getUsersWithAccountType(type).stream().
                map(userModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(entityModelList, linkTo(methodOn(UserController.class).getUsersWithAccountType(type)).withSelfRel());
    }
}
