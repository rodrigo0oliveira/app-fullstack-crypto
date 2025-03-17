package br.com.backend.blog_eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BlogEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogEurekaserverApplication.class, args);
	}

}
