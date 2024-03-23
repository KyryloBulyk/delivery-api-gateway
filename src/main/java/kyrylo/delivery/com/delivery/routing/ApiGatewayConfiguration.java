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
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/users/{userId}")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/users/register")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/users/delete/{userId}")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/users/update/{userId}")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .route(p -> p.path("/api/auth/**")
                        .filters(f -> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                        .uri("lb://DELIVERY-USERS-MICROSERVICE"))
                .build();
    }
}

