package org.aura.brosout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BrosoutApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrosoutApplication.class, args);
    }
}
