package com.carprakingapp.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {




        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

            // you guys can research this if you want .. its for preventing automated bots and helps to increase the likelyhood that its a human using your site
            http.csrf(csrf -> csrf.disable());

            // this part of the configuration secures acutal URLS
            // this is the list of URLS that require authentication to the website befroe the user can view the URL
            // this works on the idea .. that all URLS are accessable to everyone excpt for the ones listed here
            http.authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers("/User/userSearch").authenticated()
                    .requestMatchers("/Booking/**").authenticated()
                    // Allow all other requests without authentication
                    .anyRequest().permitAll()
            );


//            http.authorizeHttpRequests((authorize) -> authorize
//                    .anyRequest().authenticated()
//                    .requestMatchers("/pub/**").permitAll()
//                    .requestMatchers("/login/**").permitAll()
//            );

            // this section specifies where our login page is
            http.formLogin(formLogin -> formLogin
                    // this is the URL for the login page ... this needs a controller method to listen and show the login page
                    // this is alnalagous to /customer and the page is login
                    .loginPage("/login/login")

                    // spring security has this controller method created for us already .. and we are just configuring the URL where it submits to
                    .loginProcessingUrl("/login/loginSubmit")
                            .defaultSuccessUrl("/User/userDashboard", true)
//                    .successForwardUrl("/index")
            );

            // this section is for configuing logout
            http.logout(formLogout -> formLogout
                    // when the user logs out ... destroy the session the server side
                    .invalidateHttpSession(true)
                    // this is the acutal URL this is implemented by spring security and we are just specifying the get mapping for it
                    .logoutUrl("/login/logout")
                    // where does the user go after they have been logged out
                    // this is a URL that we have implemented somewhere in our project or controllers
                    .logoutSuccessUrl("/")
                    // extra security and delete these cookies when logging out
                    .deleteCookies("username", "JSESSIONID"));

            http.exceptionHandling(exception -> exception
                    .accessDeniedPage("/error/404"));

            return http.build();
        }

        @Bean(name = "passwordEncoder")
        public PasswordEncoder getPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }


        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
            return authConfig.getAuthenticationManager();
        }
    }


