package edu.itplus.bibliospring.backend;

import edu.itplus.bibliospring.backend.Service.LoginService;
import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    @Autowired
    private LoginService loginService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void postConstruct() {
            User user1 = new User();
            user1.setUsername("Vitya");
            user1.setPassword("Brusszel");
            User user2 = new User();
            user2.setUsername("Putin");
            user2.setPassword("Ukrajna");



    }
}
