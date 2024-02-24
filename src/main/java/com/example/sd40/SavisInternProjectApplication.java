package com.example.sd40;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SavisInternProjectApplication {


    public static void main(String[] args) {
        SpringApplication.run(SavisInternProjectApplication.class, args);
    }

}
