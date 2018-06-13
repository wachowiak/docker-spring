package org.wachowiak.docker;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringJUnitWebConfig
@SpringBootTest(classes = Application.class)
class MathHandlerTest {

    @Test
    void add() {
        MathHandler mathHandler = new MathHandler();
        MathRouter mathRouter = new MathRouter(mathHandler);
        WebTestClient client = WebTestClient
                .bindToRouterFunction(mathRouter.appRoute())
                .build();
        client.get().uri("/").exchange().expectStatus().isOk();
    }
}