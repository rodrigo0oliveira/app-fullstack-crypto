package br.com.backend.blog_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class BlogGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(r-> r.path("/article/**","/article").uri("lb://msarticle"))
				.build();
	}

}
