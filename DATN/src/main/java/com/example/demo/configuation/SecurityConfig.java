package com.example.demo.configuation;

import com.example.demo.configuation.userdetail.MyUserDetailsService;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.TaiKhoanRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    PasswordEncoderConfig passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider()));
    }

    @Bean
    public UserDetailsService userDetailsService() {

        return new MyUserDetailsService();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            @Autowired
            private TaiKhoanRepository taiKhoanRepository;

            @Override
            protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                TaiKhoan user = taiKhoanRepository.findByTenTaiKhoan(userDetails.getUsername()).orElse(null);

                if (user != null && user.getTrangThai() != null && user.getTrangThai() == 1) {
                    return "/login-error";
                } else {
                    if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                        System.out.println(userDetails.getPassword());
                        return "/ban-hang";
                    } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                        return "/home";
                    } else {
                        return "/login-error";
                    }
                }
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home","muon dat them gi thi dat vao day").permitAll()
                .requestMatchers("/style/", "/static/css/**", "/static/fonts/**", "/static/img/**",
                        "/static/js/**", "/static/scss/**", "/static/vendor/**").permitAll()
                .requestMatchers("/**").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers("/admin/**").permitAll()
                .requestMatchers("/user/**").hasAnyAuthority("ROLE_USER")
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/products/**")
                .authenticated().and()
                .formLogin()
                .loginProcessingUrl("/login")
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())
                .failureUrl("/login-error")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout/true")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and().build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder.passwordEncoder());
        return authenticationProvider;
    }
}
