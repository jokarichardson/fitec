package br.com.richardson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages="br.com.richardson")
@EnableJpaRepositories(basePackages = "br.com.richardson")
@EntityScan(basePackages = "br.com.richardson")
@ComponentScan(basePackages = "br.com.richardson")
public class FitecStarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(FitecStarterApplication.class);
    }
}