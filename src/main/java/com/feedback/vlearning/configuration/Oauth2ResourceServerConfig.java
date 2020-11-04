package com.feedback.vlearning.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/*
 * On default oauth2 filter chain is placed before the basic authentication filter chain.
 * @Order(3) will place it after the the basic authentication filter chain
 */
@Configuration
@Order(3)
@EnableResourceServer
public class Oauth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /*
     * Since this filter chain is place after the basic authentication filter chain
     * it will override the previously defined filter chain on Oauth2WebSecurityConfig.
     * Directly calling '.authorizeRequests().antMatchers("/api/**")' will override the already added matchers.
     * The '.requestMatchers().antMatchers("/api/**")' will add a new endpoint to the existing matchers
     * and then we authenticate the endpoint using '.authorizeRequests().antMatchers("/api/**").authenticated()'.
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/api/**").and()
                .authorizeRequests()
                .antMatchers("/api/**").hasAuthority("ADMIN");
    }
}
