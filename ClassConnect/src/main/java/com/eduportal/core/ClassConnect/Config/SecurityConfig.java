package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Teacher.teacherService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private LoginService loginService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/teachers/signup").permitAll()
                .anyRequest().authenticated()); // Allow these files
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(form -> form
                .loginPage("/teachers/login") // URL for your custom login page
                .loginProcessingUrl("/teachers/login") // URL to submit the login form
                .defaultSuccessUrl("/teachers/home", true) // Redirect after successful login
                .failureUrl("/login?error=true") // Redirect after failed login
                .permitAll()
        );
        http.logout(logout -> logout
                .logoutUrl("/teachers/logout") // Logout endpoint
                .logoutSuccessUrl("/teachers/login") // Redirect after logout
                .permitAll()
        );

//        http.sessionManagement(customizer -> customizer.sessionFixation().newSession());
        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(loginService); // Set LoginService as UserDetailsService
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // Set PasswordEncoder
        return provider;
    }

}
