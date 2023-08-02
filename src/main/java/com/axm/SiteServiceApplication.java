package com.axm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SiteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiteServiceApplication.class, args);
    }

}
