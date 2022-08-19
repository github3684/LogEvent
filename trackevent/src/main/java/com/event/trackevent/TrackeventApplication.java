package com.event.trackevent;

import com.event.trackevent.services.EventService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class TrackeventApplication {
    @Value("${filepath}")
    private String filepath;
    public static void main(String[] args) {

        SpringApplication.run(TrackeventApplication.class, args);
    }

    @Bean
    CommandLineRunner run(EventService eventService) {

        return args -> {
            eventService.readFie(filepath);
        };
    }

}

