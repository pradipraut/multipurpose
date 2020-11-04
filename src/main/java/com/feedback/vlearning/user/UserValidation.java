package com.feedback.vlearning.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    @Autowired
    UserService userService;

    public UserError validateUser(UserDTO userDto,String type) {
        UserError userError = new UserError();
        Boolean valid = true;

        if (userDto.getEmail() == null ||
                userDto.getEmail().replaceAll(" ", "").equals("") ||
                userDto.getEmail().equals("") ||
                userDto.getEmail().trim().equals("")) {
            userError.setValid(Boolean.FALSE);
            userError.setEmail("Email is Required");
        }

        if (userDto.getUsername() == null ||
                userDto.getUsername().equals("") ||
                userDto.getUsername().trim().equals("") ||
                userDto.getUsername().replaceAll(" ", "").equals("")) {
            valid = false;
            userError.setUserName("Invalid username");
        } else if (userDto.getUsername().contains(" ")) {
            valid = false;
            userError.setUserName("Username cannot contain space");
        } else if (userDto.getPassword().replaceAll(" ", "").length() < 5) {
            valid = false;
            userError.setUserName("Username too short");
        }

        if(type.equalsIgnoreCase("add")) {
            if (userService.findByUserName(userDto.getUsername().trim()) != null) {
                valid = false;
                userError.setUserName("Username already exists!");
            }
        }

        if (type.equalsIgnoreCase("edit")){
            if (!userService.findUserById(userDto.getId()).getUsername().equalsIgnoreCase(userDto.getUsername())){
                if (userService.findByUserName(userDto.getUsername().trim()) != null) {
                    valid = false;
                    userError.setUserName("Username already exists!");
                }
            }
        }

        if (userDto.getPassword() == null ||
                userDto.getPassword().equals("") ||
                userDto.getPassword().trim().equals("") ||
                userDto.getPassword().replaceAll(" ", "").equals("")) {
            valid = false;
            userError.setPassword("Invalid password");
        } else if (userDto.getPassword().contains(" ")) {
            valid = false;
            userError.setPassword("Password cannot contain space");
        } else if (userDto.getPassword().replaceAll(" ", "").length() > 20) {
            valid = false;
            userError.setPassword("Password too long");
        } else if (userDto.getPassword().replaceAll(" ", "").length() < 8) {
            valid = false;
            userError.setPassword("Password too short");
        }

        userError.setValid(valid);
        return userError;
    }

}
