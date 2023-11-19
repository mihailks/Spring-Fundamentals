package com.softuni.mobilelele.config;


import com.softuni.mobilelele.model.entity.enums.UserRoleEnum;
import com.softuni.mobilelele.repository.UserRepository;
import com.softuni.mobilelele.service.impl.MobileleUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    private final String rememberMeKey;

    public SecurityConfiguration(@Value("${mobilele.remember.me.key}")
                                 String rememberMeKey) {
        this.rememberMeKey = rememberMeKey;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                // define witch url are visible by witch users
                authorizeRequests -> authorizeRequests
                        // allow access to static resources to anyone
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        //access to actuator endpoints
//                        .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                        // allow access to index and user login and register to anyone
                        .requestMatchers("/", "/users/login", "/users/register", "/users/login-error", "/offers/all").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/api/currency/convert").permitAll()
                        .requestMatchers(HttpMethod.GET, "/offers/**").permitAll()
                        .requestMatchers("/brands").hasRole(UserRoleEnum.ADMIN.name())
                        // all other requests are authenticated.
                        .anyRequest().authenticated()

        ).formLogin(
                formLogin -> {
                    // if user go to a page with authentication
                    formLogin.loginPage("/users/login")
                            // email because we set it to login with email and not an actual username
                            .usernameParameter("email")
                            .passwordParameter("password")
                            // if login is successful go to index
                            .defaultSuccessUrl("/")
                            // if login is unsuccessful go to login-error
                            .failureForwardUrl("/users/login-error");
                }
        ).logout(
                logout -> logout
                        // the URL where we send the POST request to logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
        ).rememberMe(
                rememberMe -> rememberMe.key(rememberMeKey)
                        .rememberMeParameter("rememberme")
                        .rememberMeCookieName("rememberme")
        );
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        // This service translates the mobilele users and roles
        // to representation with spring security understands
        return new MobileleUserDetailService(userRepository); //реално е мапър между емайл и userDetails
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

}
