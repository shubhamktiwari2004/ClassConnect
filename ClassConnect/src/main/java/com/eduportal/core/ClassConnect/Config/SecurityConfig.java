package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Teacher.teacherService.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final LoginService loginService;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    public SecurityConfig(LoginService loginService, CustomSuccessHandler customSuccessHandler, CustomLogoutSuccessHandler customLogoutSuccessHandler, CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.loginService = loginService;
        this.customSuccessHandler = customSuccessHandler;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/teachers/signup" ,"/teachers/api/**" , "/teachers/forgetPassword" , "/teachers/resetPassword" ,"/teachers/resetPasswordLink" , "/teachers/reset-password*" , "/teachers/resetPasswordConfirm").permitAll()
                .anyRequest().authenticated()); // Allow these files
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(form -> form
                .loginPage("/teachers/login") // URL for your custom login page
                .loginProcessingUrl("/teachers/login") // URL to submit the login form
                .successHandler(customSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .failureUrl("/teachers/login?error=true") // Redirect after failed login
                .permitAll()
        );
        http.logout(logout -> logout
                .logoutUrl("/teachers/logout") // Logout endpoint
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll()
        );

        http.sessionManagement(session -> session.sessionFixation().newSession());
        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(loginService); // Set LoginService as UserDetailsService
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // Set PasswordEncoder
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
