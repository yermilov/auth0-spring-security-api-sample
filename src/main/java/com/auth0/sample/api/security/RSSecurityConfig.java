package com.auth0.sample.api.security;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity(debug = true)
@Configuration
@ConditionalOnProperty(prefix = "auth0", value = "secret", matchIfMissing = true)
public class RSSecurityConfig extends WebSecurityConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(RSSecurityConfig.class);

    @Value("${auth0.issuer}")
    private String issuer;

    @Value("${auth0.audience}")
    private String audience;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("Configuring for Jwt token with iss {} & aud {}", issuer, audience);

        JwtWebSecurityConfigurer.forRS256(audience, issuer)
                .configure(http)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/secure/**").fullyAuthenticated();
        //                .antMatchers(HttpMethod.GET, "/secure/**").hasAuthority("read:greeting");
    }
}
