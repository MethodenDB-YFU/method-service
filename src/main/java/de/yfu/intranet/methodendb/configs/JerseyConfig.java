package de.yfu.intranet.methodendb.configs;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import de.yfu.intranet.methodendb.endpoints.MethodEndpoint;
import de.yfu.intranet.methodendb.endpoints.SeminarEndpoint;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		register(SeminarEndpoint.class);
		register(MethodEndpoint.class);
	}
}