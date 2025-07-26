package com.github.erodriguezg.grpcjavalab.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ServerMainApplication {

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
        SpringApplication.run(ServerMainApplication.class, args);
    }

}
