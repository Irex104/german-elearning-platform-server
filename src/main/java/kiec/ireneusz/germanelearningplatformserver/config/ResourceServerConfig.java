package kiec.ireneusz.germanelearningplatformserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/admin/**").hasRole("ADMIN")
                .antMatchers( "/moderator/**").hasAnyRole("ADMIN","MODERATOR")
                .antMatchers( "/user/**").authenticated()
                .antMatchers( "/authorization/**").permitAll()
                .antMatchers( "/public/**").permitAll()
                .and()
                .cors()
                .and()
                .formLogin();
    }

}
