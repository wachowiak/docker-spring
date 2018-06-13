package org.wachowiak.docker;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
class MathHandler {

    private MathService mathService;

    public MathHandler(MathService mathService) {
        this.mathService = mathService;
    }

    Mono<ServerResponse> add(ServerRequest request){

        try {
            long a = getParam(request, "a", Long::parseLong);
            long b = getParam(request, "b", Long::parseLong);
            return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                    .body(BodyInserters.fromObject(mathService.add(a, b)));
        }catch (BadRequestException exc){
            return ServerResponse.badRequest().body(BodyInserters.fromObject(exc.getLocalizedMessage()));
        }
    }

    private <T> T getParam(ServerRequest request, String param, Function<String, T> clazz) {
        Object value = request.queryParam(param).orElseThrow(()->new BadRequestException("Missing parameter"));
        try {
            return clazz.apply(String.valueOf(value));
        }catch (RuntimeException exc){
            throw new BadRequestException("Could not cast value into long");
        }
    }
}
