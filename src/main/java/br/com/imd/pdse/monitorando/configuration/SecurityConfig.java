package br.com.imd.pdse.monitorando.configuration;

import br.com.imd.pdse.monitorando.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationProvider authProvider;

    public static final String[] ENDPOINTS_WHITELIST = {
            "/css/**",
            "/js/**",
            "/login",
            "/register",
            "/save",
            "/process-login"
    };
    public static final String LOGIN_URL = "/login";
    public static final String LOGIN_PROCESS_URL = "/process-login";
    public static final String LOGIN_FAIL_URL = LOGIN_URL;
    public static final String DEFAULT_SUCCESS_URL = "/classroom";

    public SecurityConfig(CustomAuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }


//    @Bean
//    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, AuthenticationService userDetailsService) {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(userDetailsService);
//        return new ProviderManager(provider);
//    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request.requestMatchers(ENDPOINTS_WHITELIST)
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage(LOGIN_URL)
                        .usernameParameter("login")
                        .successForwardUrl(LOGIN_PROCESS_URL)
                        .failureUrl(LOGIN_FAIL_URL)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .invalidSessionUrl(LOGIN_URL)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(false)
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl(LOGIN_URL + "?logout"))
                .build();
    }
}
