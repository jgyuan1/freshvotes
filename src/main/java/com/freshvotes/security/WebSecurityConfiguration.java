package com.freshvotes.security;


import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity

public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers("/","/index").permitAll()
                        .antMatchers("/hello").hasRole("USER")
                        .anyRequest().authenticated())
                .formLogin((form)->form.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/votes"));

//                .logout()
//                .logoutUrl("/logout")
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .logoutUrl("/index");
        return http.build();
    }
//        http
//                .authorizeRequests((authorize) -> authorize
//                        .antMatchers("/index").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin((form) -> form.loginPage("/login").permitAll());
//
//        return http.build();
//    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User.builder().passwordEncoder(passwordEncoder::encode)
                .username("user").password("password123").roles("USER").build();
        UserDetails admin = User.builder().passwordEncoder(passwordEncoder::encode)
                .username("admin").password("pass").roles("ADMIN").build();
        manager.createUser(user);
        manager.createUser(admin);
        return manager;
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                );
//
//        return http.build();
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
//                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login");
//                .permitAll()
//                .defaultSuccessUrl("/welcome", true)
//                .passwordParameter("password")
//                .usernameParameter("username")
//                .and()
//                .rememberMe()
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                .key("somethingverysecured")
//                .rememberMeParameter("remember-me")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "remember-me")
//                .logoutSuccessUrl("/login");

    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withUsername("user1")
//                  .passwordEncoder((s) -> {return new BCryptPasswordEncoder(10).encode(s);})
//                  .password("password")
//                  .roles("USER").build();
//        return new InMemoryUserDetailsManager(user);
//    }
//}
