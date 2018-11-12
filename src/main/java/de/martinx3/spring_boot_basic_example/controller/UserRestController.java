/*
 * Created by Martin Dünkelmann on 13.11.18 00:06
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 13.11.18 00:06
 */

package de.martinx3.spring_boot_basic_example.controller;

import de.martinx3.spring_boot_basic_example.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * @author Martin Dünkelmann on 13.11.18
 * @project spring_boot_basic_example
 */
@RestController
@RequestMapping("/users")
public class UserRestController {
    private final Map<UUID, User> users;

    public UserRestController(Map<UUID, User> users) {
        this.users = users;
    }

    @GetMapping
    public Collection<User> getUser() {
        return users.values();
    }

    @GetMapping(value = "/{id}")
    private User getUser(@PathVariable UUID id) {
        return users.get(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(UUID.randomUUID());

        users.put(user.getId(), user);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User updatedUser) {
        updatedUser.setId(id);

        users.put(id, updatedUser);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedUser.getId()).toUri()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable UUID id) {
        users.remove(id);

        return ResponseEntity.ok().build();
    }
}