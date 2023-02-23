package com.review.webtoon;

import com.review.webtoon.service.PrincipalOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired private PrincipalOAuth2UserService principalOAuth2UserService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                    .antMatchers("/user/**").authenticated()
                //user 주소에 대해 인증 요구
                    .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')")
                //manager주소는 매니저 권한이나 어드민 권한 있어야 접근가능
                    .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                //admin주소는 어드민권한 있어야 접근가능
                    .anyRequest().permitAll()//나머지 주소는 인증없지 접근 가능
                .and()
                    .formLogin()
                        .loginPage("/loginForm")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/review")
                        .failureUrl("/loginForm")
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/review")
                .and()
                    .oauth2Login()
                        .loginPage("/loginForm")
                        .defaultSuccessUrl("/review")
                        .failureUrl("/loginForm")
                        .userInfoEndpoint()
                        .userService(principalOAuth2UserService);
    }
}