package fr.fms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(ahr -> ahr.requestMatchers(HttpMethod.POST,"/**").permitAll())
                .authorizeHttpRequests(ahr -> ahr.requestMatchers(HttpMethod.GET, "/**").permitAll())
                .authorizeHttpRequests(ahr -> ahr.requestMatchers(HttpMethod.PUT, "/**").permitAll())
                .authorizeHttpRequests(ahr -> ahr.requestMatchers(HttpMethod.DELETE, "/**").permitAll())
                .authorizeHttpRequests(ahr -> ahr.anyRequest().authenticated())

                //Pour le test
//                .authorizeHttpRequests(ahr -> ahr.requestMatchers("/login").permitAll())
//                .authorizeHttpRequests(ahr -> ahr.requestMatchers("/infos").authenticated())
                
                .oauth2ResourceServer(ors -> ors.jwt(Customizer.withDefaults()))
                .build();
    }

    //Configuration CORS pour permettre l'accès du front au back
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return  source;

    }


}
