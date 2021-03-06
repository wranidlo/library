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
public class UserRestController {
    private final UserController userController;
    private final UserModelAssembler userModelAssembler;

    public UserRestController(UserController userController, UserModelAssembler userModelAssembler) {
        this.userController = userController;
        this.userModelAssembler = userModelAssembler;
    }

    // Get all users
    @GetMapping("/users")
    CollectionModel<EntityModel<User>> all() {
        List<EntityModel<User>> users =userController.getAllUsers().stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserRestController.class).all()).withSelfRel());
    }

    // Get one user
    @GetMapping("/users/{id}")
    EntityModel<User> one(@PathVariable Long id) {
        return userModelAssembler.toModel(userController.getOneUser(id));
    }

    //Add one free user
    @PostMapping("/users/addFreeUser")
    ResponseEntity<?> newFreeUser(@RequestBody User newUser){
        EntityModel<User> userEntityModel = userModelAssembler.toModel(userController.createNewUser(newUser, AccountType.FREE));
        return ResponseEntity.created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userEntityModel);
    }

    //Add one premium user
    @PostMapping("/users/addPremiumUser")
    ResponseEntity<?> newPremiumUser(@RequestBody User newUser){
        EntityModel<User> userEntityModel = userModelAssembler.toModel(userController.createNewUser(newUser, AccountType.PREMIUM));
        return ResponseEntity.created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userEntityModel);
    }

    // Update one user
    @PutMapping("/users/update/{id}")
    ResponseEntity<?> updateUser(@RequestBody User newUser, @PathVariable Long id){
        EntityModel<User> userEntityModel = userModelAssembler.toModel(userController.updateUser(newUser, id));
        return ResponseEntity.created(userEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(userEntityModel);
    }

    // Change account type
    @PutMapping("/users/changeAccountType/{id}")
    ResponseEntity<?> changeUserAccountType(@PathVariable Long id){
        return ResponseEntity.ok(userModelAssembler.toModel(userController.changeUserAccountType(id)));
    }

    // Delete one user
    @DeleteMapping("/users/delete/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id){
        userController.deleteOneUser(id);
        return ResponseEntity.noContent().build();
    }

    // Users with account type
    @GetMapping("/users/getUsersWithAccount")
    CollectionModel<EntityModel<User>> getUsersWithAccountType(@RequestParam(value = "type") AccountType type){
        List<EntityModel<User>> entityModelList = userController.getUsersWithAccount(type).stream().
                map(userModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(entityModelList, linkTo(methodOn(UserRestController.class).getUsersWithAccountType(type)).withSelfRel());
    }
}
