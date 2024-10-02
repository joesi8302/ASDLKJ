package com.synergisticit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Autowired
    BCryptPasswordEncoder bCrypt;

    @Autowired
    UserDetailsService userDetailsService;


    @Bean
    DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCrypt);
        return authProvider;
    }


    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/home")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/userForm")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/airlinesForm")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/flightForm")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/airportForm")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/passengerForm")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/reservationForm")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/roleForm")).permitAll()
//                .requestMatchers(AntPathRequestMatcher.antMatcher("/WEB-INF/jsp/**")).permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/home", true)
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout") // URL for logout, default is /logout
//                .logoutSuccessUrl("/home") // URL to redirect after logout
//                .invalidateHttpSession(true) // Invalidate session
//                .clearAuthentication(true) // Clear authentication
//                .permitAll();
//
//        http.userDetailsService(userDetailsService);
//        return http.build();
//    }
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF protection for APIs
                .authorizeHttpRequests()
                // Secure API endpoints
                .requestMatchers("airlineApp/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                // Permit access to public endpoints
                .requestMatchers("/", "/home", "/userForm", "/airlinesForm", "/flightForm",
                        "/airportForm", "/passengerForm", "/reservationForm", "/roleForm", "/WEB-INF/jsp/**")

                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic() // Use HTTP Basic Authentication for API requests
                .and()
                .formLogin()
                .defaultSuccessUrl("/home", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll();

        return http.build();
    }
}
