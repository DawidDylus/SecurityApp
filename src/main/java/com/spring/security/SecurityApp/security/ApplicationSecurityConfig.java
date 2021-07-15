package com.spring.security.SecurityApp.security;


import com.spring.security.SecurityApp.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


import java.net.PasswordAuthentication;

import static com.spring.security.SecurityApp.security.ApplicationUserRole.ADMIN;
import static com.spring.security.SecurityApp.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
       UserDetails Dawid = User.builder()
                .username("Dawid")
                .password(passwordEncoder.encode("Password"))
                .roles(STUDENT.name())
                .build();

       UserDetails Admin = User.builder()
               .username("admin")
               .password((passwordEncoder.encode("admin")))
               .roles(ADMIN.name())
               .build();


       return new InMemoryUserDetailsManager(
               Dawid,
               Admin
       );
    }
}
