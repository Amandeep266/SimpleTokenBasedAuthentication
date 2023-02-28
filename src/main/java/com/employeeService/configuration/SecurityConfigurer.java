package com.employeeService.configuration;

import com.employeeService.filter.JwtFilter;
import com.employeeService.services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.naming.NoPermissionException;

@Configuration
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailService;
    @Autowired
    private JwtFilter jwtFilter;

   // private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();

    public SecurityConfigurer() throws Exception {
    }


    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth.userDetailsService(myUserDetailService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //    usernamePasswordAuthenticationFilter.setPostOnly(true);
        http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().anyRequest().authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // usernamePasswordAuthenticationFilter.setFilterProcessesUrl("/login","POST");
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class
        );  // username paswrd authentication filter is by default wokr on /login url

        System.out.println("filter added");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
