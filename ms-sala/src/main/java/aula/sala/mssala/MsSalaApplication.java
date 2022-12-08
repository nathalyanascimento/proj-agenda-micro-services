package aula.sala.mssala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsSalaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsSalaApplication.class, args);
    }

}
