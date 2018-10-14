package de.yfu.intranet.methods.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Component
@ConfigurationProperties(prefix = "cors")
public class CorsConfig extends CorsConfiguration {
}
