package pmf.gis.fastfoods.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Starting from Spring Boot 2, if Spring Security is present, endpoints are secured by default
 * using Spring Security’s content-negotiation strategy.
 */
@Configuration
public class DisableSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.cors().and()
            .authorizeRequests()
                .anyRequest().permitAll()
                .and()
            .csrf()
                .disable();
        // @formatter:on
    }
}
