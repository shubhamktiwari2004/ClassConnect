package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Teacher.teacherService.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    // Inject DataSource for Remember Me
    private final DataSource dataSource;


    private final LoginService loginService;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    public SecurityConfig(DataSource dataSource, LoginService loginService, CustomSuccessHandler customSuccessHandler, CustomLogoutSuccessHandler customLogoutSuccessHandler, CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.dataSource = dataSource;
        this.loginService = loginService;
        this.customSuccessHandler = customSuccessHandler;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }


    //For Teacher Panel
    @Bean
    public SecurityFilterChain teacherSecurityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/teachers/signup" ,"/teachers/api/**" , "/teachers/forgetPassword" , "/teachers/resetPassword" ,"/teachers/resetPasswordLink" , "/teachers/reset-password*" , "/teachers/resetPasswordConfirm").permitAll()
                .requestMatchers("/teachers/**").hasRole("TEACHER")
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
        http.rememberMe(rememberMe -> rememberMe
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 days
                .key("secureKey123") // Unique secret key for hashing
                .rememberMeParameter("remember_me") // Matches the checkbox name in the form
                .userDetailsService(loginService)
                .authenticationSuccessHandler(customSuccessHandler)
        );
        http.logout(logout -> logout
                .logoutUrl("/teachers/logout") // Logout endpoint
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .permitAll()
        );
        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(session -> session.sessionFixation().newSession());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        http.authenticationProvider(teacherAuthenticationProvider()); // Use teacher authentication provider

        return http.build();

    }

    @Bean
    public AuthenticationProvider teacherAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(loginService); // Teacher's UserDetailsService
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // Use bcrypt for encoding passwords
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


}
