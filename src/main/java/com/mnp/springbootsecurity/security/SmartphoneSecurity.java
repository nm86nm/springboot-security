package com.mnp.springbootsecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@EnableWebSecurity
public class SmartphoneSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Matt").password(passwordEncoder().encode("pass1")).roles("ADMIN")
                .and()
                .withUser("Jennifer").password(passwordEncoder().encode("pass2")).roles("MODERATOR")
                .and()
                .withUser("Batman").password(passwordEncoder().encode("pass3")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("smartphone/delete-smartphones").hasRole("ADMIN")
                .antMatchers("smartphone/save-smartphone").hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers("smartphone/get-smartphones", "smartphone/get-smartphone").permitAll()   // hasAnyRole("ADMIN","MODERATOR","USER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
