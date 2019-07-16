package org.nexters.cultureland;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class CultureLandApplication implements ApplicationRunner {

    @Autowired
    private Crawling service;

    public static void main(String[] args) {
        SpringApplication.run(CultureLandApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        service.getCrawling();
    }
}

