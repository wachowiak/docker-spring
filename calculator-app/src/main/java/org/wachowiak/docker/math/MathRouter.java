package org.wachowiak.docker.math;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Configuration
class MathRouter {

    private final MathHandler mathHandler;

    public MathRouter(MathHandler mathHandler) {
        this.mathHandler = mathHandler;
    }

    @Bean(name="mathRoute")
    public RouterFunction<ServerResponse> appRoute() {

        return RouterFunctions
                .nest(path("/math"), mathRoute()
                );
    }

    private RouterFunction<ServerResponse> mathRoute() {
        return                         RouterFunctions.
                route(GET("/add"), mathHandler::add)
                .andRoute(GET("/sub"), mathHandler::sub)
                .andRoute(GET("/mul"), mathHandler::mul)
                .andRoute(GET("/div"), mathHandler::div);
    }

}
