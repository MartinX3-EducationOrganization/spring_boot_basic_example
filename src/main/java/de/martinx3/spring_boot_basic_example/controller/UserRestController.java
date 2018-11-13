/*
 * Created by Martin Dünkelmann on 13.11.18 00:06
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 13.11.18 00:06
 */

package de.martinx3.spring_boot_basic_example.controller;

import de.martinx3.spring_boot_basic_example.entity.User;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * @author Martin Dünkelmann on 13.11.18
 * @project spring_boot_basic_example
 */
@RestController
@RequestMapping("/users")
@Api(tags = {"users"})
public class UserRestController {
    private final Map<UUID, User> users;

    public UserRestController(Map<UUID, User> users) {
        this.users = users;
    }

    @GetMapping
    public ResponseEntity<Collection<User>> getUser() {
        Collection<User> deepCopyUsers = new ArrayList<>();

        users.keySet().forEach(id -> {
            User user = new User(users.get(id));
            user.add(linkTo(UserRestController.class).slash(id).withSelfRel());
            deepCopyUsers.add(user);
        });

        return ResponseEntity.ok(deepCopyUsers);
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<User> getUser(@PathVariable UUID id) {
        User user = new User(users.get(id));
        user.add(linkTo(UserRestController.class).slash(id).withSelfRel());

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        UUID uuid = UUID.randomUUID();

        users.put(uuid, user);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(uuid).toUri()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        users.put(id, updatedUser);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable UUID id) {
        users.remove(id);

        return ResponseEntity.ok().build();
    }
}