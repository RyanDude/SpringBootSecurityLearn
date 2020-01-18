package com.example.demo.config;

import com.example.demo.Handlers.SecurityAuthenticationFailureHandler;
import com.example.demo.Util.VertificationCodeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class securityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/api/**").hasRole("ADMIN")
                .antMatchers("/user/api/**").hasRole("USER")
                .antMatchers("/app/api/**","/captcha.jpg").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()//must disable
                .formLogin()
                .loginPage("/mylogin.html")
                .loginProcessingUrl("/auth/form").permitAll().failureHandler(new SecurityAuthenticationFailureHandler())
                .and()
                .sessionManagement()
                .maximumSessions(1);
        http.addFilterBefore(new VertificationCodeFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
