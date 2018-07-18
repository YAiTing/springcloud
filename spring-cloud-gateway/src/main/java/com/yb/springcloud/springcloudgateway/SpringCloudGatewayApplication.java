package com.yb.springcloud.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * https://spring.io/docs/reference
 * spring cloud gateway
 */
@SpringBootApplication
public class SpringCloudGatewayApplication {

	@Bean
	public RouterFunction<ServerResponse> helloWorldRouterFunction() {
		return route(GET("/fallback"), request ->
				ServerResponse.ok().body(Mono.just("fallback..."), String.class)
		);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("gateway", request ->
					request.path("/gateway/hello-world")
						.uri("http://localhost:8080/hello-world"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGatewayApplication.class, args);
	}
}
