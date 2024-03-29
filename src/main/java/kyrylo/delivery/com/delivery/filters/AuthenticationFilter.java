package kyrylo.delivery.com.delivery.filters;

import kyrylo.delivery.com.delivery.authorization.validator.RouteValidator;
import kyrylo.delivery.com.delivery.authorization.dto.JwtRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    private final WebClient webClient;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    public AuthenticationFilter(RouteValidator validator, WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.validator = validator;
        this.webClient = webClientBuilder.build();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!validator.isSecured.test(exchange.getRequest())) {
                return chain.filter(exchange);
            }

            var authHeaders = exchange.getRequest().getHeaders().getOrEmpty("Authorization");
            if (authHeaders.isEmpty() || !authHeaders.get(0).startsWith("Bearer ")) {
                logger.error("Missing or invalid Authorization header");
                throw new RuntimeException("Missing or invalid Authorization header");
            }

            String token = authHeaders.get(0).substring(7);
            logger.info("Received token: {}", token); // Logging the received token
            JwtRequest jwtRequest = new JwtRequest(token);

            return webClient.post()
                    .uri("http://DELIVERY-USERS-MICROSERVICE/api/auth/validateToken")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(jwtRequest))
                    .retrieve()
                    .toBodilessEntity()
                    .then(chain.filter(exchange))
                    .onErrorResume(e -> {
                        logger.error("Unauthorized access with token: {}", token, e);
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    });
        };
    }

    public static class Config {

    }
}
