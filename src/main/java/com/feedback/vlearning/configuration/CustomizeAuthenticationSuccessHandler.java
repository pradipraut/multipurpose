package com.feedback.vlearning.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Autowired
    SecurityUtility securityUtility;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {

        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);

        for (GrantedAuthority auth : authentication.getAuthorities()) {

            if ("SYSTEM_ADMIN".equalsIgnoreCase(auth.getAuthority()))
                response.sendRedirect("/auth/admin/dashboard");

            if ("ADMIN".equals(auth.getAuthority())) {
                response.sendRedirect("/auth/dashboard");
            }

        }
    }
}