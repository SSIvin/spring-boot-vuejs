package SpringBoot.config;

import SpringBoot.security.security.jwt.JwtConfigurer;
import SpringBoot.security.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    private static final String API_DATA_ENDPOINT = "/api/*";

    @Autowired
    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and() //добавляем форму логина
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("username")
//                .permitAll() //форма регистрации разрешены для всех
                .and()
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/auth/**").permitAll()

//                .antMatchers(ADMIN_ENDPOINT).hasAuthority("ROLE_ADMIN")
                .antMatchers("/api/find").hasAuthority("ROLE_ADMIN")
                //.antMatchers(ADMIN_ENDPOINT).permitAll()

//                .antMatchers("/", "/favicon.ico") .permitAll()

//                .antMatchers("/api/find").permitAll()
//                .antMatchers("/api/**").hasAuthority("ROLE_ADMIN")
//                .antMatchers("/getAllPlaceJob").hasAuthority("ROLE_ADMIN")



//                .antMatchers(API_DATA_ENDPOINT).permitAll()
//                .antMatchers(API_DATA_ENDPOINT).hasRole("ADMIN")
//                .antMatchers(API_DATA_ENDPOINT).hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
//
//                .anyRequest().authenticated() //все остальные будут требовать авторизации

//


//                .antMatchers(LOGIN_ENDPOINT).permitAll()
//                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
//                .anyRequest().authenticated()
                //.and()
    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // AuthenticationTokenFilter will ignore the below paths
//        web
//                .ignoring()
//                .antMatchers(
//                        HttpMethod.POST,
//                        "/auth"
//                )
//
//                // allow anonymous resource requests
//                .and()
//                .ignoring()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/"
//                );
//
//                //.and()
//                //.ignoring()
//                //.antMatchers(HttpMethod.POST, "/register")
//
//                // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
////                .and()
////                .ignoring()
////                .antMatchers("/h2-console/**/**");
//    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
