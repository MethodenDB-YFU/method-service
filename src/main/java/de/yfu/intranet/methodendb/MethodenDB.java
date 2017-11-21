package de.yfu.intranet.methodendb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class MethodenDB extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new MethodenDB().configure(new SpringApplicationBuilder(MethodenDB.class)).run(args);
	}
}