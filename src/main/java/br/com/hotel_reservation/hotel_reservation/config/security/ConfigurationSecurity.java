package br.com.hotel_reservation.hotel_reservation.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class ConfigurationSecurity extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/auth").permitAll()
                        .antMatchers(HttpMethod.GET, "/teste").permitAll()
                        .antMatchers(HttpMethod.GET,"/hotel-reservation/v1/admin/customers").permitAll()
                        .antMatchers(HttpMethod.POST,"/hotel-reservation/v1/admin/room").permitAll()
                        .antMatchers(HttpMethod.GET,"/hotel-reservation/v1/admin/room").permitAll();
                http.csrf().disable();
        }



}
