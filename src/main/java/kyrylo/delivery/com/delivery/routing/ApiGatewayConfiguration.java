package kyrylo.delivery.com.delivery.routing;

import kyrylo.delivery.com.delivery.authorization.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
//                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .build();
    }
}

