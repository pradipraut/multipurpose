package com.feedback.vlearning.configuration;

import com.feedback.vlearning.user.User;
import com.feedback.vlearning.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserSecurityService implements UserDetailsService {

    private UserService userService;

    public UserSecurityService(UserService userService) {
        this.userService=userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);

        if(null == user) {
            throw new UsernameNotFoundException("Username not found");
        }

        return new CustomUserDetails(user, user.getAuthorities());
    }



}