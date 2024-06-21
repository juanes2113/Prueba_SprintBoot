package com.ideasstress.ideasstress1.Models.Configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.ideasstress.ideasstress1.Models.Servicio.UsuarioServicio;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
@EnableWebSecurity

public class CondiguracionWebSecurity
{
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UsuarioServicio();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(requests -> requests
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/public/**", "/css/**", "/js/**", "/Imagenes/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/formRegistro").permitAll()
                .antMatchers("/proceso_registro").permitAll()
                .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
                        .permitAll()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/Index")
                        .permitAll())
                .logout(logout -> logout.logoutSuccessUrl("/login").permitAll())
                .csrf().disable().headers().frameOptions().disable();
        return http.build();
    }
}
