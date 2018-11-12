/*
 * Created by Martin Dünkelmann on 12.11.18 23:58
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 12.11.18 23:58
 */

package de.martinx3.spring_boot_basic_example.database;

import de.martinx3.spring_boot_basic_example.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Martin Dünkelmann on 12.11.18
 * @project spring_boot_basic_example
 */
@Repository
public class Store {
    @Bean
    public Map<UUID, User> users() {
        return new ConcurrentHashMap<>();
    }
}
