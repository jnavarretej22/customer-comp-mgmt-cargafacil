package com.cargafacil.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/user-types/**").permitAll()
            .requestMatchers("/user/**").permitAll()
            .requestMatchers("/parametros/**").permitAll()
            .requestMatchers("/client/**").hasAuthority("ROLE_Cliente")
            .requestMatchers("/tracking/**").hasAuthority("ROLE_Cliente")
            .requestMatchers("/trip/**").hasAuthority("ROLE_Cliente")
            .requestMatchers("/driver/**").hasAuthority("ROLE_Chofer")
            .requestMatchers("/vehicle/**").hasAuthority("ROLE_Chofer")
            .requestMatchers("/cooperative/**").hasAuthority("ROLE_Cooperativa")
            .requestMatchers("/admin/**").hasAuthority("ROLE_Administrador")
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
