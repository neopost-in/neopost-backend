package com.ceaa.ceaaapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CeaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CeaaApplication.class, args);
    }

}
