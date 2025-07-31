package org.app.tpdocker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class TpDockerApplication {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        dotenv.entries().forEach((entry) -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });SpringApplication.run(TpDockerApplication.class, args);
    }

}
