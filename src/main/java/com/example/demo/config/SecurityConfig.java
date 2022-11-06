package com.example.demo.config;

import com.example.demo.components.tokenprovider.JwtAuthenticationFilter;
import com.example.demo.modules.authen.biz.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public InMemoryUserDetailsManager user() {
        return new InMemoryUserDetailsManager(
                User.withUsername("duc")
                    .password("{noop}password")
                    .authorities("read")
                    .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authAuthenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authAuthenticationManagerBuilder.userDetailsService(authService).passwordEncoder(passwordEncoder());
        return authAuthenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors()
                .and()
                .exceptionHandling().authenticationEntryPoint(
                        (req, res, ex) -> {
                            res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                            ex.getMessage();
                        }
                )
                .and()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/auth/login", "/auth/register").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults())
                .build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
//        return (web) -> web.ignoring().antMatchers("/books/all", "/books/details/*");
//    }
}
