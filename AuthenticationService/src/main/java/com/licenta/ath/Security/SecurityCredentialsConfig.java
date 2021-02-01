package com.licenta.ath.Security;

import com.licenta.ath.Service.AuthTokensPairService;
import com.licenta.ath.Service.UserService;
import com.licenta.ath.Utils.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static com.google.common.collect.ImmutableList.of;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity    // Enable security config. This annotation denotes config for spring security.
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final TokenGenerator tokenGenerator;
    private final AuthTokensPairService authTokensPairService;

    @Autowired
    public SecurityCredentialsConfig(TokenGenerator tokenGenerator,
                                     UserService userService,
                                     @Qualifier("MyUserDetailService") UserDetailsService userDetailsService,
                                     AuthTokensPairService authTokensPairService) {
        this.tokenGenerator = tokenGenerator;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.authTokensPairService = authTokensPairService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(POST, "/api/users").permitAll()
                .antMatchers(POST, "/api/tokens").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),
                        userService, tokenGenerator, authTokensPairService))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(STATELESS);
    }

    // Spring has UserDetailsService interface, which can be overriden to provide our implementation for fetching user from database (or
    // any other source).
    // The UserDetailsService object is used by the auth manager to load the user from database.
    // In addition, we need to define the password encoder also. So, auth manager can compare and verify passwords.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(of("*"));
        configuration.setAllowedMethods(of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not
        // be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(of("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        //        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        //        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}