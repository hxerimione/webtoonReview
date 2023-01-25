package com.review.webtoon;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/**").authenticated()
                //user 주소에 대해 인증 요구
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')")
                //manager주소는 매니저 권한이나 어드민 권한 있어야 접근가능
                .antMatchers("/admin/**").hasRole("ADMIN")
                //admin주소는 어드민권한 있어야 접근가능
                .anyRequest().permitAll()//나머지 주소는 인증없지 접근 가능
                .and()
                .formLogin()
                //formlogin기반
                .loginPage("/loginForm")
                //인증 필요한 URL 접근시 /loginForm 으로 이동
                .usernameParameter("id")
                //로그인 시 form에서 가져올 값
                .passwordParameter("pw")
                //로그인 시 form에서 가져올 값
                .loginProcessingUrl("/login")
                //로그인을 처리할 URL 입력
                .defaultSuccessUrl("/")
                //로그인 성공하면 "/"으로 이동
                .failureUrl("/loginForm")
                //로그인 실패 시 /loginForm 으로 이동
                .and()
                .logout()
                .logoutUrl("/logout")
                //로그아웃을 처리할 URL 입력
                .logoutSuccessUrl("/");
                //로그아웃 성공 시 "/"으로 이동
    }
}