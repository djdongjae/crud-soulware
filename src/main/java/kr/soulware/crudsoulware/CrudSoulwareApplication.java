package kr.soulware.crudsoulware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CrudSoulwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSoulwareApplication.class, args);
    }

}
