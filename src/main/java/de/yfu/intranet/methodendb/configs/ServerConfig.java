package de.yfu.intranet.methodendb.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import de.yfu.intranet.methodendb.configs.SecurityConfig.CorsConfig;

@Configuration
@EnableResourceServer
public class ServerConfig extends ResourceServerConfigurerAdapter {

    private final CorsConfig corsConfig;

    public ServerConfig(final CorsConfig corsConfig) {
        corsConfig.setAllowedMethods(corsConfig.getAllowedMethods());
        this.corsConfig = corsConfig;

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    public void configure(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**")
                .permitAll();
    }
}
