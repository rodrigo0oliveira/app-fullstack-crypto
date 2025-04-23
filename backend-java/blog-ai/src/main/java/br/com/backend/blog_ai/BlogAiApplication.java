package br.com.backend.blog_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlogAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAiApplication.class, args);
	}

}
