# auth0-java-api-samples

## Before Running

- Clone repo https://github.com/auth0/jwks-rsa-java and run
  ```
  mvn install
  ```

- Create an API in Auth0 dashboard

## Configuration

In the file `application.properties` configure the following

- `auth0.issuer` with the issuer value of your API obtained from Auth0 Dashboard
- `auth0.audience` with the audience value of your API obtained from Auth0 Dashboard

This will use RS256 by default. If you want HS256 add also `auth0.secret` with your base64 encoded secret

## Run

Execute the following command

```
mvn spring-boot:run
```

And it will start the resource server so you can hit two endpoints

- http://localhost:8080/greeting: non secured endpoint
- http://localhost:8080/secure/greeting: secured endpoint that needs a bearer token issued by Auth0

By default it only allows access to authenticated users but you can also require the presence of a scope. You can do that in `SecurityConfig.java` where it has

```java
        http.authorizeRequests()
                .antMatchers("/secure/**").fullyAuthenticated();
```

and change `fullyAuthenticated()` for something like

```
        http.authorizeRequests()
                .antMatchers("/secure/**").hasAuthority("read:greeting");
```

When the user is authorized, all the token scopes are loaded in Spring's `Authentication` object and returned from the method `getAuthorities()`.

> You can use any of the scopes defined in your API, and even request several of them

## TODO

- Extract classes in `com.auth0.sample.api.security.jwt` to a OSS library
- Explain better how to use `JwtWebSecurityConfigurerAdapter` in your Spring Security configuration
- Document how JwtAuthentication is built, e.g. how the scopes can be accesed
- Update jwks-rsa lib version when released

