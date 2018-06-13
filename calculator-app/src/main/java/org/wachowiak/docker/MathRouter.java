package org.wachowiak.docker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
class MathRouter {

    private MathHandler mathHandler;

    public MathRouter(MathHandler mathHandler) {
        this.mathHandler = mathHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> appRoute() {

        return RouterFunctions
                .nest(path("/math"), mathRoute()
                );
    }

    @Bean
    public RouterFunction<ServerResponse> mathRoute() {
        return                         RouterFunctions.
                route(GET("/add"), mathHandler::add)
                .andRoute(GET("/sub"), mathHandler::add)
                .andRoute(GET("/mul"), mathHandler::add)
                .andRoute(GET("/div"), mathHandler::add);
    }

}
