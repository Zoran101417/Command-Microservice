package com.command_microservice.security;

//import com.command_microservice.security.filter.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/**")
////                        .permitAll()
//                        .anyRequest().authenticated()).build();

//        http.authorizeHttpRequests(authorization -> authorization
//                .requestMatchers("api/**").permitAll());
//        return http.build();
        http.csrf(AbstractHttpConfigurer::disable).authorizeRequests(authorizeRequests ->
                authorizeRequests
//                        .requestMatchers("/api/**")
//                        .requestMatchers("/login")
//                        .permitAll().requestMatchers("/api/**").permitAll()
//                        .anyRequest().authenticated()// Allow all requests without authentication
                        .anyRequest().permitAll()// Allow all requests without authentication
        );
        return http.build();
//        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(authorization -> authorization
//                .requestMatchers("/login").permitAll()
//                .anyRequest().authenticated());
//        return http.build();
    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http)
//            throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
////        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
//        return authenticationManagerBuilder.build();
//    }


}
