package kr.soulware.crudsoulware.config;

import kr.soulware.crudsoulware.exception.ErrorCode;
import kr.soulware.crudsoulware.security.jwt.JwtAuthenticationFilter;
import kr.soulware.crudsoulware.security.jwt.JwtExceptionHandlerFilter;
import kr.soulware.crudsoulware.util.ServletErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtExceptionHandlerFilter jwtExceptionHandlerFilter;

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**")
                        .permitAll()
                        .requestMatchers("/", "/error", "/favicon.ico")
                        .permitAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/profile")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/posts/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users", "/api/v1/users/sign-in")
                        .permitAll()
                        .anyRequest().authenticated())
                .exceptionHandling(a -> a
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            log.error("403: {}", accessDeniedException.getMessage(), accessDeniedException);
                            ServletErrorResponse.setErrorResponse(response, ErrorCode.FORBIDDEN_REQUEST_EXCEPTION);
                        })
                        .authenticationEntryPoint(((request, response, authException) -> {
                            log.error("401: {}", authException.getMessage(), authException);
                            ServletErrorResponse.setErrorResponse(response, ErrorCode.INSUFFICIENT_AUTHENTICATION_EXCEPTION);
                        }))
                );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionHandlerFilter, JwtAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
