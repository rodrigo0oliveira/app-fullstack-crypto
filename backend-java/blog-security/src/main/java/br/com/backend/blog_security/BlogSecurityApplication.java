package br.com.backend.blog_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlogSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSecurityApplication.class, args);
	}

}
