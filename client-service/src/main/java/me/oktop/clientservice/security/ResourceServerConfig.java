package me.oktop.clientservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.http.HttpMethod;
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();

//        http.authorizeRequests()
//                .antMatchers("/test").authenticated();
    }
}
