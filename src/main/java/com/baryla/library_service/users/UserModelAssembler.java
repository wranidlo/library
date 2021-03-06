package com.baryla.library_service.users;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserRestController.class).one(user.getId())).withSelfRel(),
                linkTo(methodOn(UserRestController.class).all()).withRel("users"),
                linkTo(methodOn(UserRestController.class).changeUserAccountType(user.getId()))
                        .withRel("change_account_type"));
    }
}
