package com.github.erodriguezg.grpcjavalab.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class WebMainApplication {

    public static void loadEnv() {
        Dotenv dotenv = Dotenv.configure()
                .directory(System.getProperty("user.dir")) // Carga desde la raíz del proyecto
                .ignoreIfMissing() // No falla si no existe .env
                .load();

        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }

    public static void main(String[] args) {
        loadEnv();
        SpringApplication.run(WebMainApplication.class, args);
    }

}