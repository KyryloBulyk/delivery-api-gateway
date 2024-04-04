package kyrylo.delivery.com.delivery.configuration;

import kyrylo.delivery.com.delivery.filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApiGatewayConfiguration {
    private AuthenticationFilter authenticationFilter;

    @Autowired
    public ApiGatewayConfiguration(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                // Delivery Users Microservice
                .route(p -> p.path("/api/users/**")
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/users/{userId}")
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/users/register")
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/users/delete/{userId}")
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/users/update/{userId}")
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/auth/**")
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/roles/**")
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))

                // Delivery Product Microservice
                .route(p -> p.path("/api/categories")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-PRODUCT-MICROSERVICE"))
                .route(p -> p.path("/api/categories/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-PRODUCT-MICROSERVICE"))
                .route(p -> p.path("/api/products")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-PRODUCT-MICROSERVICE"))
                .route(p -> p.path("/api/products/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-PRODUCT-MICROSERVICE"))
                .route(p -> p.path("/api/orders")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-PRODUCT-MICROSERVICE"))
                .route(p -> p.path("/api/orders/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-PRODUCT-MICROSERVICE"))
                .build();
    }
}

