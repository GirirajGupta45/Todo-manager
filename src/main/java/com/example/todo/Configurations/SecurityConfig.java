package com.example.todo.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/Welcome", "/login", "/register", "/css/**", "/js/**").permitAll() // allow welcome + login + register
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")                 // your custom login page
                        .loginProcessingUrl("/doLogin")      // where form submits
                        .defaultSuccessUrl("/home", true)    // after login
                        .failureUrl("/Login?error=true")     // on failure
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/") // 👈 after logout go back to welcome.jsp
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/JSP/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
