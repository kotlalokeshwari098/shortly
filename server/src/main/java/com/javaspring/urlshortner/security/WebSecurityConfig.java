package com.javaspring.urlshortner.security;

import com.javaspring.urlshortner.security.jwt.JwtAuthenticationFilter;
import com.javaspring.urlshortner.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    private UserDetailsServiceImpl userDetailsServiceImpl;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

      @Bean
      public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
      }

      @Bean
      public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration){
          return authenticationConfiguration.getAuthenticationManager();
      }

      @Bean
      public DaoAuthenticationProvider daoAuthenticationProvider(){
          DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider(userDetailsServiceImpl);
          authProvider.setPasswordEncoder(passwordEncoder());
          return authProvider;
      }



    @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
          http.csrf(csrf->csrf.disable())
                  .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                  .requestMatchers("/api/auth/**").permitAll()
                                  .requestMatchers("/{shortUrl}/**").permitAll()
                                  .requestMatchers("/api/urls/**").authenticated()

                   );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(daoAuthenticationProvider());
          return http.build();
      }
}
