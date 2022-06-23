package com.sop.seciurity;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.SET_COOKIE;

/**
 * Klasa konfigurująca zabezpieczenia i logowanie aplikacji dziedzicząca z klasy {@link WebSecurityConfigurerAdapter}
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Przechowuje obiekt typu {@link UserDetailsServiceImpl}
     */
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * Metoda tworząca {@link BCryptPasswordEncoder}
     *
     * @return zwraca nowy {@link BCryptPasswordEncoder}
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Metoda tworząca obiekt do zarządzania danymi użytjownika
     * przez obiekt typu {@link DaoAuthenticationProvider}
     *
     * @return zwraca obiket typu {@link DaoAuthenticationProvider}
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Metoda konfigurująca zarządzanie danymi użytkownika przez {@link AuthenticationManagerBuilder}
     *
     * @param auth obiekt zarządzający autentyfikacją oraz przekazywaniem danych użytkownika
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Metoda konfigurująca połączenia, autentyfikacje, zarządzanie plikami cookies,
     * zabezpieczeniem stron oraz dostępem do zasobów aplikacji
     *
     * @throws Exception wyrzuca wyjąctek w razie błędów
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    response.setHeader(ACCESS_CONTROL_ALLOW_HEADERS, SET_COOKIE);
                    response.setStatus(HttpServletResponse.SC_OK);
                })
                .failureHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_NOT_FOUND))
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
                .accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(HttpServletResponse.SC_FORBIDDEN))
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> response.setStatus(HttpServletResponse.SC_OK))
                .deleteCookies("JSESSIONID")
                .and()
                .authorizeRequests()
                .mvcMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * Metoda tworząca obiekt typu {@link CorsConfiguration} umożliwiający współdzielnie zasobów między
     * serwerami w różnych domenach
     *
     * @return zwraca skonfigurowany obiekt implementujący interfejs {@link CorsConfigurationSource}
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader(CONTENT_TYPE);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
}