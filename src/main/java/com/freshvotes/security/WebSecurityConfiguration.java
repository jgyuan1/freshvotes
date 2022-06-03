package com.freshvotes.security;


import com.freshvotes.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;


import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity

public class WebSecurityConfiguration {
//Spring Security uses DelegatingPasswordEncoder by default. However, this can be customized by exposing a PasswordEncoder as a Spring bean.
    // BCryptPasswordEncoder is default
//    @Bean
//    PasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers("/", "/index").permitAll()
                        .antMatchers("/hello", "/votes").hasAuthority("ROLE_USER"))
                .formLogin((form) -> form.loginPage("/login").permitAll()
                        .defaultSuccessUrl("/votes"))
                //.authenticationManager(new ProviderManager(new DaoAuthenticationProvider()))
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/logoutSuccessful");
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userDetailsServiceImpl);
        //System.out.print(userDetailsService.getClass().toGenericString());
        return http.build();
    }





//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        PasswordEncoder passwordEncoder = getPasswordEncoder();
//        UserDetails user = User.builder().passwordEncoder(passwordEncoder::encode)
//                .username("user").password("password123").roles("USER").build();
//        UserDetails admin = User.builder().passwordEncoder(passwordEncoder::encode)
//                .username("admin").password("pass").roles("ADMIN").build();
//        manager.createUser(user);
//        manager.createUser(admin);
//        return manager;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        return this.userDetailsService;
//    }


}


