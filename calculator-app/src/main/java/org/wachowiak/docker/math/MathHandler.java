package org.wachowiak.docker.math;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.wachowiak.docker.exception.BadRequestException;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;
import java.util.function.Function;

@Component
class MathHandler {

    private final MathService mathService;

    public MathHandler(MathService mathService) {
        this.mathService = mathService;
    }

    Mono<ServerResponse> add(ServerRequest request){
        return runMath(request, mathService::add);
    }

    Mono<ServerResponse> sub(ServerRequest request){
        return runMath(request, mathService::sub);
    }

    Mono<ServerResponse> mul(ServerRequest request){
        return runMath(request, mathService::mul);    }

    Mono<ServerResponse> div(ServerRequest request){
        return runMath(request, mathService::div);
    }

    private <T> T getParam(ServerRequest request, String param, Function<String, T> clazz) {
        Object value = request.queryParam(param).orElseThrow(()->new BadRequestException("Missing parameter"));
        try {
            return clazz.apply(String.valueOf(value));
        }catch (RuntimeException exc){
            throw new BadRequestException("Could not cast value into long");
        }
    }


    private Mono<ServerResponse> runMath(ServerRequest request, BiFunction<Long, Long, Long> operation) {
        try {
            long a = getParam(request, "a", Long::parseLong);
            long b = getParam(request, "b", Long::parseLong);
            return ServerResponse.ok()
                    .body(BodyInserters.fromObject(operation.apply(a, b)));
        }catch (BadRequestException exc){
            return ServerResponse.badRequest().body(BodyInserters.fromObject(exc.getLocalizedMessage()));
        }catch (AssertionError exc){
            return ServerResponse.badRequest().body(BodyInserters.fromObject("Invalid parameter provided"));
        }
    }

}
