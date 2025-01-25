package com.eduportal.core.ClassConnect.Config;

import com.eduportal.core.ClassConnect.Admin.AdminService.AdminLoginService;
import com.eduportal.core.ClassConnect.Students.studentService.StudentLoginService;
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
    private final StudentLoginService studentLoginService;
    private final AdminLoginService adminLoginService;
    private final CustomSuccessHandler customSuccessHandler;
    private final CustomSuccessHandlerStudent customSuccessHandlerStudent;
    private final CustomSuccessHandlerAdmin customSuccessHandlerAdmin;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomLogoutHandlerStudent customLogoutHandlerStudent;
    private final CustomLogoutHandlerAdmin customLogoutHandlerAdmin;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAuthenticationFailureStudent customAuthenticationFailureStudent;
    private final CustomAuthenticationFailureAdmin customAuthenticationFailureAdmin;

    public SecurityConfig(DataSource dataSource, LoginService loginService, StudentLoginService studentLoginService, AdminLoginService adminLoginService, CustomSuccessHandler customSuccessHandler, CustomSuccessHandlerStudent customSuccessHandlerStudent, CustomSuccessHandlerAdmin customSuccessHandlerAdmin, CustomLogoutSuccessHandler customLogoutSuccessHandler, CustomLogoutHandlerStudent customLogoutHandlerStudent, CustomLogoutHandlerAdmin customLogoutHandlerAdmin, CustomAuthenticationFailureHandler customAuthenticationFailureHandler, CustomAuthenticationFailureStudent customAuthenticationFailureStudent, CustomAuthenticationFailureAdmin customAuthenticationFailureAdmin) {
        this.dataSource = dataSource;
        this.loginService = loginService;
        this.studentLoginService = studentLoginService;
        this.adminLoginService = adminLoginService;
        this.customSuccessHandler = customSuccessHandler;
        this.customSuccessHandlerStudent = customSuccessHandlerStudent;
        this.customSuccessHandlerAdmin = customSuccessHandlerAdmin;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        this.customLogoutHandlerStudent = customLogoutHandlerStudent;
        this.customLogoutHandlerAdmin = customLogoutHandlerAdmin;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.customAuthenticationFailureStudent = customAuthenticationFailureStudent;
        this.customAuthenticationFailureAdmin = customAuthenticationFailureAdmin;
    }


    //For student panel
    @Bean
    public SecurityFilterChain studentSecurityFilterChain(HttpSecurity http) throws Exception{

        http.securityMatcher("/students/**");
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/","/students/forgetPassword","/students/resetPassword","/students/reset-password","/students/resetPasswordConfirm").permitAll()
                .requestMatchers("/student/**").hasRole("STUDENT")
                .anyRequest().authenticated()); // Allow these files
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(form -> form
                .loginPage("/students/login") // URL for your custom login page
                .loginProcessingUrl("/students/login")
                .successHandler(customSuccessHandlerStudent)
                .failureHandler(customAuthenticationFailureStudent)
                .failureUrl("/students/login?error=true") // Redirect after failed login
                .permitAll()
        );
        http.rememberMe(rememberMe -> rememberMe
                .rememberMeCookieName("remember_me_student")
                .tokenRepository(studentTokenRepository())
                .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 days
                .key("studentKey") // Unique secret key for hashing
                .rememberMeParameter("remember_me_student") // Matches the checkbox name in the form
                .userDetailsService(studentLoginService)
                .authenticationSuccessHandler(customSuccessHandlerStudent)
        );
        http.logout(logout -> logout
                .logoutUrl("/students/logout") // Logout endpoint
                .logoutSuccessHandler(customLogoutHandlerStudent)
                .permitAll()
        );
        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(session -> session.sessionFixation().newSession());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        http.authenticationProvider(studentAuthenticationProvider()); // Use teacher authentication provider

        return http.build();

    }


    @Bean
    public AuthenticationProvider studentAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(studentLoginService); // Teacher's UserDetailsService
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // Use bcrypt for encoding passwords
        return provider;
    }

    @Bean
    public PersistentTokenRepository studentTokenRepository() {
        return new CustomJdbcTokenRepositoryImpl(dataSource, "persistent_logins_student");
    }





    //For Teacher Panel
    @Bean
    public SecurityFilterChain teacherSecurityFilterChain(HttpSecurity http) throws Exception{

        http.securityMatcher("/teachers/**");
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request
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
                .rememberMeCookieName("remember_me_teacher")
                .tokenRepository(teacherTokenRepository())
                .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 days
                .key("teacherKey") // Unique secret key for hashing
                .rememberMeParameter("remember_me_teacher") // Matches the checkbox name in the form
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
    public PersistentTokenRepository teacherTokenRepository() {
        return new CustomJdbcTokenRepositoryImpl(dataSource, "persistent_logins_teacher");
    }



    //Admin panel


    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception{

        http.securityMatcher("/admin/**");
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()); // Allow these files
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(form -> form
                .loginPage("/admin/login") // URL for your custom login page
                .loginProcessingUrl("/admin/login") // URL to submit the login form
                .defaultSuccessUrl("/admin/home")
                .successHandler(customSuccessHandlerAdmin)
                .failureHandler(customAuthenticationFailureAdmin)
                .failureUrl("/admin/login?error=true") // Redirect after failed login
                .permitAll()
        );
        http.rememberMe(rememberMe -> rememberMe
                .rememberMeCookieName("remember_me_admin")
                .tokenRepository(adminTokenRepository())
                .tokenValiditySeconds(7 * 24 * 60 * 60) // 7 days
                .key("adminKey") // Unique secret key for hashing
                .rememberMeParameter("remember_me_admin") // Matches the checkbox name in the form
                .userDetailsService(adminLoginService)
                .authenticationSuccessHandler(customSuccessHandlerAdmin)
        );
        http.logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessHandler(customLogoutHandlerAdmin)
                .permitAll()
        );
        http.httpBasic(Customizer.withDefaults());
//        http.sessionManagement(session -> session.sessionFixation().newSession());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
        http.authenticationProvider(adminAuthenticationProvider()); // Use teacher authentication provider

        return http.build();

    }

    @Bean
    public AuthenticationProvider adminAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(adminLoginService); // Teacher's UserDetailsService
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // Use bcrypt for encoding passwords
        return provider;
    }

    @Bean
    public PersistentTokenRepository adminTokenRepository() {
        return new CustomJdbcTokenRepositoryImpl(dataSource, "persistent_logins_admin");
    }





}
