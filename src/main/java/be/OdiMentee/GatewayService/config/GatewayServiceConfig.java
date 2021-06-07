package be.OdiMentee.GatewayService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableHystrix
public class GatewayServiceConfig {

    @Autowired
    AuthenticationFilter filter;


    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("profielen-service", r -> r.path("/profielen/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://PROFIELEN-SERVICE"))

                .route("domein-service", r -> r.path("/domein/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://DOMEIN-SERVICE"))

                .route("auth-service", r -> r.path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://AUTH-SERVICE"))
                .route("mentorships-service", r -> r.path("/mentorships/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://MENTORSHIPS-SERVICE"))
                .build();
    }



    }



