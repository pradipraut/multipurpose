package com.feedback.vlearning;

import com.feedback.vlearning.user.Status;
import com.feedback.vlearning.user.User;
import com.feedback.vlearning.user.UserDTO;
import com.feedback.vlearning.user.UserService;
import com.feedback.vlearning.user.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FeedbackApplication {


    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(FeedbackApplication.class, args);
    }


    @Bean
    CommandLineRunner init() {

        return args -> {

            createUser("admin", UserType.SYSTEM_ADMIN, "123456", Status.ACTIVE);
//            createAnswer();

        };
    }

    void createUser(String username, UserType userType, String password, Status status) {

        User user = userService.findByUserName(username);

        if (user == null) {
            UserDTO newUser = new UserDTO();
            newUser.setUsername(username);
            newUser.setEmail("pradipradip7@gmail.com");
            newUser.setUserType(userType);
            newUser.setPassword(password);
            newUser.setStatus(status);
            userService.save(newUser);
        }

    }

}
