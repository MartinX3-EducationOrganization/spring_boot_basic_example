/*
 * Created by Martin Dünkelmann on 12.11.18 23:43
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 12.11.18 23:43
 */

package de.martinx3.spring_boot_basic_example.entity;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author Martin Dünkelmann on 12.11.18
 * @project spring_boot_basic_example
 */

public class User extends ResourceSupport {
    private String username;
    private String password;

    public User(User user) {
        username = user.username;
        password = user.password;
    }

    public User() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
