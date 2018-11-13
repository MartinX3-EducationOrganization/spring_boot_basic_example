package de.martinx3.spring_boot_basic_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import static org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType.HAL;

@SpringBootApplication
@EnableHypermediaSupport(type = HAL)
public class SpringBootBasicExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBasicExampleApplication.class, args);
    }
}
