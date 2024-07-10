package WEBTIMVIEC.example.DoAnLapTrinhJava.utils;


import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.CustomUserDetailService;
import WEBTIMVIEC.example.DoAnLapTrinhJava.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserService userService;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private SuccessHandle successHandle;
    @Autowired
    private FailureHandle failureHandle;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public UserDetailsService UserDetailsService() {

        return customUserDetailService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**","/images/**", "/", "/user/registerNTD","/user/registerUngVien", "/error").permitAll()
                        .requestMatchers("/baiTuyenDungs").hasAnyAuthority("NTD")
                        .requestMatchers("/donUngTuyens/**").permitAll()
                        .requestMatchers("/roles").hasAuthority("ADMIN")
                        .anyRequest().permitAll()

                ).formLogin(AbstractConfiguredSecurityBuilder
                        ->AbstractConfiguredSecurityBuilder.loginPage("/login")
                        .successHandler(successHandle)
                        .failureHandler(failureHandle)
                        .permitAll()

                ).logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/users/login")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .permitAll()

                ).rememberMe(rememberMe -> rememberMe
                        .key("hutech")
                        .rememberMeCookieName("hutech")
                        .tokenValiditySeconds(24 * 60 * 60)
                        .userDetailsService(customUserDetailService)
                )
                .build();
    }



}

