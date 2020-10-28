package biz.tugay.sprangsec.configuration;

import biz.tugay.sprangsec.filter.JwtFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("john")
        .password("{noop}doe")
        .authorities("ROLE_USER")
        .and()
        .withUser("a")
        .password("{noop}a")
        .authorities("ROLE_ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        // This is needed since Angular first sends an OPTIONS request to /validate endpoint
        // without any authorization information with the request.
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .antMatchers("/validate", "/foo", "/bar").hasAnyRole("USER", "ADMIN")
        .and()
        .httpBasic()
        .and()
        .addFilter(new JwtFilter(authenticationManager()));
  }
}
